package bell.renting.cars.rentingcars.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {
    @JsonProperty("status")
    private Integer status ;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("detail")
    private String detail = null;

    @JsonProperty("time")
    private String time = null;

    @JsonProperty("exception")
    private String exception = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
