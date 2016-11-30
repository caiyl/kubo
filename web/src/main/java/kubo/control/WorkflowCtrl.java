package kubo.control;

import kubo.activiti.form.dto.Process;
import kubo.common.utils.DateUtils;
import kubo.common.utils.FilenameUtils;
import kubo.common.result.ResultBean;
import kubo.domain.User;
import kubo.service.UserService;
import kubo.utils.WorkflowUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;


@Controller
@RequestMapping("/workflow")
public class WorkflowCtrl {

	protected Logger logger = Logger.getLogger(WorkflowCtrl.class);

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private UserService userService;

	public WorkflowCtrl() {
	}


	/**
	 * 流程定义列表
	 *
	 * @return
	 */
	@RequestMapping(value="/process-list", consumes = "application/json")
	@ResponseBody
	public Map<String,Object> processList() {
    /*
     * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
     */
		List<Object[]> objects = new ArrayList<Object[]>();

		Map<String,Object> ret = new HashMap<String,Object>();

		int[] pageParams = {0,15};

		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(pageParams[0], pageParams[1]);
		for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
			objects.add(new Object[]{new Process( processDefinition.getId(),
					processDefinition.getDeploymentId(),
					processDefinition.getName(),
					processDefinition.getKey(),
					processDefinition.getVersion()+"",
					processDefinition.getResourceName(),
					processDefinition.getDiagramResourceName(),
					processDefinition.isSuspended()?"是":"否"
					), new kubo.activiti.form.dto.Deployment(
					DateUtils.format(deployment.getDeploymentTime(),DateUtils.FORMAT_FULL))});
		}


	/*	Gson gson = new Gson();
		String s = gson.toJson(objects);*/
		ret.put("result",objects);
		logger.info("query success");
		return ret;
	}

	@RequestMapping(value = "/deploy")
	@ResponseBody
	public ResultBean deploy(@RequestParam(value = "file", required = false) MultipartFile file) {
		// TODO: 2016/11/25 org.activiti.engine.ActivitiIllegalArgumentException: unknown type 'users' countersignUsers 
		String exportDir ="/tmp/kft-activiti-demo";
		String fileName = file.getOriginalFilename();

		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;

			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			}

			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

			for (ProcessDefinition processDefinition : list) {
				WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error on deploy process, because of file input stream", e);
		}

		return ResultBean.getNewResultBean().setSuccess(true);
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 *
	 * @param deploymentId 流程部署ID
	 */
	@RequestMapping(value = "/delete", consumes = "application/json")
	@ResponseBody
	public ResultBean delete(@RequestParam("deploymentId") String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
		return ResultBean.getNewResultBean().setSuccess(true);
	}

}