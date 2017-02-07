package kubo.activiti.form.dto;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Deployment {
    private String deploymentTime;

    public Deployment(String deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(String deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
