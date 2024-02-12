package in.gov.wildlife.mis.exception;

import java.util.List;

public class AccessDeniedException extends RuntimeException{

    private static final long serialVersionUID = -4413580510361232868L;

    private List<Error> details;

    public  AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, List<Error> details) {
        super(message);
        this.details = details;
    }

    public List<Error> getDetails() {
        return details;
    }
}
