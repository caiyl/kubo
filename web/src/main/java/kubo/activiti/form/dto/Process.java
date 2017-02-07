package kubo.activiti.form.dto;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Process {

    private String id;
    private String deploymentId;
    private String name;
    private String key;
    private String version;
    private String resourceName;
    private String diagramResourceName;
    private String suspended;



    public Process(String id, String deploymentId, String name, String key, String version, String resourceName, String diagramResourceName, String suspended) {
        this.id = id;
        this.deploymentId = deploymentId;
        this.name = name;
        this.key = key;
        this.version = version;
        this.resourceName = resourceName;
        this.diagramResourceName = diagramResourceName;
        this.suspended = suspended;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

}
