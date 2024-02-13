package in.gov.wildlife.mis.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

@Getter
public class ApiResponse implements Serializable {
    public static final long serialVersionUID = -612811614311755079L;
    private Integer status;
    private java.lang.Error error;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String traceId;

    public ApiResponse() {
        this.status = ErrorCode.OK.value();
        this.error = new java.lang.Error();
        this.data = new HashMap<String, Object>();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        ApiResponse otherApiResponse = (ApiResponse) other;

        return Objects.equals(status, otherApiResponse.status)
                && Objects.equals(error, otherApiResponse.error)
                && Objects.equals(data, otherApiResponse.data)
                && Objects.equals(traceId, otherApiResponse.traceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, error, data, traceId);
    }

    @Override
    public String toString() {
        return "<APIResponse STATUS: " + getStatus() + ", ERROR: " + getError() + ", DATA: " + getData() +", TRACEID: "
                + getTraceId() + ">";
    }

    public ApiResponse setStatus(Integer status) {
        this.status = status;

        return this;
    }

    public ApiResponse setError(java.lang.Error error) {
        this.error = error;

        return this;
    }

    public ApiResponse setData(Object data) {
        this.data = data;

        return this;
    }


    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
