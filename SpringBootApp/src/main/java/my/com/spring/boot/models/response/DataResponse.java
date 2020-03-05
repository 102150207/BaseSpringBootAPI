package my.com.spring.boot.models.response;

public class DataResponse {
    private Boolean success;

    private int status;

    private String message;

    private Object object;

    public DataResponse() {
        super();
    }

    public DataResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public DataResponse(Boolean success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    public DataResponse(Boolean success, int status, String message, Object object) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
