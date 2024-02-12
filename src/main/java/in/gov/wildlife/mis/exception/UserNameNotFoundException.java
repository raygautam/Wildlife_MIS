package in.gov.wildlife.mis.exception;

import java.util.List;

public class UserNameNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -4413580510361232868L;

    private List<Error> details;

    public  UserNameNotFoundException(String message) {
        super(message);
    }

    public UserNameNotFoundException(String message, List<Error> details) {
        super(message);
        this.details = details;
    }

    public List<Error> getDetails() {
        return details;
    }
}
