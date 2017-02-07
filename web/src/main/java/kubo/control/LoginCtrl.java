package kubo.control;

import kubo.common.result.ResultBean;
import kubo.dao.UserMapper;
import kubo.domain.User;
import kubo.service.UserService;
import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/login")
public class LoginCtrl {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;

	public LoginCtrl() {
	}

	@RequestMapping(method=RequestMethod.POST, value="/loginIn", consumes = "application/json")
	@ResponseBody
    public ResultBean login(@RequestBody User user, HttpServletRequest request) {
		List<User> userList = userService.getUserByName(user.getUserName());

		for (User u : userList) {
			if (u.equals(user)) {
				HttpSession session = request.getSession();
				User su = (User) session.getAttribute("user");
				session.setAttribute("user",u);
				return ResultBean.getNewResultBean().setSuccess(true).setData(u);
			}
		}
		return ResultBean.getNewResultBean().setSuccess(false);
    }

	@RequestMapping(value = "/test")
	public String test() {
		User u = userService.getUser(Integer.valueOf(1));
		userMapper.selectByPrimaryKey(1);
		return "12233";
	}

}