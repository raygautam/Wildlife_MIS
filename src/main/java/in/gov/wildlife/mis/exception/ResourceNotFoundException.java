package in.gov.wildlife.mis.exception;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -3415290940263416214L;

    private List<Error> details;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, List<Error> details) {
        super(message);
        this.details = details;
    }

    public List<Error> getDetails() {
        return details;
    }
}
