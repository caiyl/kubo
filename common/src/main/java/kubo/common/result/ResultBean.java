package kubo.common.result;

/**
 * Created by Administrator on 2016/11/27.
 */
public class ResultBean {

    private boolean isSuccess;
    private String status;
    private Object data;



    public static ResultBean getNewResultBean(){
        return new ResultBean();
    }



    public boolean isSuccess() {
        return isSuccess;
    }

    public ResultBean setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ResultBean setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultBean setData(Object data) {
        this.data = data;
        return this;
    }


}
