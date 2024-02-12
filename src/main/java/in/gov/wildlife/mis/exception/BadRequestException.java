package in.gov.wildlife.mis.exception;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = -4367181649866316234L;
    private List<Error> details;

    public BadRequestException(String message) {
        super(message);
        this.details = new ArrayList<>();
    }

    public BadRequestException(String message, List<Error> details) {
        super(message);
        this.details = details;
    }

    public void setDetails(List<Error> details) {
        this.details = details;
    }

    public void addDetail(Error detail) {
        this.details.add(detail);
    }

    public void addDetails(List<Error> details) {
        this.details.addAll(details);
    }

    public List<Error> getDetails() {
        return details;
    }

}
