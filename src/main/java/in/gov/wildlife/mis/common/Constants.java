package in.gov.wildlife.mis.common;

public interface Constants {

    String INVALID = "Invalid ";
    String FARMER_DETAILS = "FarmerDetails";
    Long MAXAGESECS = 3600L;

    interface ERROR_MESSAGE {
        String BAD_REQUEST_MESSAGE = "error-message.bad-request";
        String CONFLICT = "error-message.conflict";
        String ACCESS_DENIED = "error-message.access-denied";
        String BAD_REQUEST = "error-message.bad-request-body";
        String NULL_REQUEST = "error-message.null-value";
        String INVALID_CAPTCHA = "error-message.invalid-captcha";
        String ACCOUNT_LOCKED = "Your Account has been locked Please try after 24 hours";
        String RESOURCE_NOT_FOUND=" error-message-resource-not-found";

        String UN_AUTHORIZED = "error-message.unauthorized";


    }
}
