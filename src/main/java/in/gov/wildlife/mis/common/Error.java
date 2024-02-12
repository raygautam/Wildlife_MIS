package in.gov.wildlife.mis.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Custom Error class for the Error in the Api Response
 *
 * */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error implements Serializable {
    public static final long serialVersionUID = -6016799131909796759L;

    private String code;
    private String target;
    private String message;
    private List<Error> details;

    public Error() {

    }

    public Error(String code, String target, String message) {
        super();
        this.code = code;
        this.target = target;
        this.message = message;
    }

    public Error(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Error(int value, String badRequest, String message, Object o) {
    }

    @Override
    public String toString() {
        return "<Error CODE: " + getCode() + ", MESSAGE: " + getMessage() + ", DETAILS: " + getDetails() + ">";
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

        Error otherError = (Error) other;

        return Objects.equals(code, otherError.code)
                && Objects.equals(target, otherError.target)
                && Objects.equals(message, otherError.message)
                && Objects.deepEquals(details, otherError.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, target, message, details);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Error> getDetails() {
        return details;
    }

    public void setDetails(List<Error> details) {
        this.details = details;
    }
}
