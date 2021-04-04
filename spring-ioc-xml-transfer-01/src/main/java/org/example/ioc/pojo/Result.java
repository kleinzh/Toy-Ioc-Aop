package org.example.ioc.pojo;

/**
 * @author Klein
 * @Classname Result
 * @Description TODO
 * @Date 2021-04-03 12:29
 * @Created by Klein
 */
public class Result {

    private Boolean success;

    private String message;

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    private String data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
