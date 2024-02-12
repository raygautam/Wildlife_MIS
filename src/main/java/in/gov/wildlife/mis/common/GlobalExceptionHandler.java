//package in.gov.wildlife.mis.common;
//
//import in.gov.wildlife.mis.exception.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//@EnableWebMvc
//@RestController
//public class GlobalExceptionHandler {
//    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    private MessageByLocale messageByLocale;
//
//    @Autowired
//    public GlobalExceptionHandler(MessageByLocale messageByLocale) {
//        this.messageByLocale = messageByLocale;
//    }
//
//    // For setting the trace id
////    private void setTraceId(APIResponse apiResponse){
////        apiResponse.setTraceId(requestMeta.getRequestId());
////    }
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException badRequestException) {
////        LOG.warn("{}", keyValue("message", "Caught BadRequestException in GlobalExceptionHandler"), badRequestException);
//
////        ApiResponse responseBody = new ApiResponse();
////        Error error = new Error(ErrorCode.BAD_REQUEST.name(), badRequestException.getMessage());
////        List<Error> errorDetails = badRequestException.getDetails();;
////
////        if (errorDetails != null && !errorDetails.isEmpty()) {
////            error.setDetails(errorDetails);
////        }
////
////        responseBody.setStatus(ErrorCode.BAD_REQUEST.value());
////        responseBody.setError(error);
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.BAD_REQUEST.name(), badRequestException.getMessage());
//        List<java.lang.Error> errorDetails = badRequestException.getDetails();
//
//        if (errorDetails != null && !errorDetails.isEmpty()) {
//            List<String> errorMessages = errorDetails.stream()
//                    .map(java.lang.Error::getMessage) // Assuming getMessage returns the error message
//                    .collect(Collectors.toList());
//            error.setDetails(errorMessages);
//        }
//
//        responseBody.setStatus(ErrorCode.BAD_REQUEST.value());
//        responseBody.setError(error);
//
//
//
//        return ResponseEntity.status(ErrorCode.BAD_REQUEST.statusCode()).body(responseBody);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
//
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.ACCESS_DENIED.name(), accessDeniedException.getMessage());
//
//        if (!CollectionUtils.isEmpty(accessDeniedException.getDetails())) {
//            error.setDetails(accessDeniedException.getDetails());
//        }
//
//        responseBody.setStatus(ErrorCode.ACCESS_DENIED.value());
//        responseBody.setError(error);
//
//        return ResponseEntity.status(ErrorCode.ACCESS_DENIED.statusCode()).body(responseBody);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleConflictException(ConflictException exception) {
//
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.CONFLICT.name(), exception.getMessage());
//        List<java.lang.Error> errorDetails = exception.getDetails();
//
//        if (errorDetails != null && !errorDetails.isEmpty()) {
//            error.setDetails(errorDetails);
//        }
//
//        responseBody.setStatus(ErrorCode.CONFLICT.value());
//        responseBody.setError(error);
//
//        return ResponseEntity.status(ErrorCode.CONFLICT.statusCode()).body(responseBody);
//
//
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleInvalidCaptchaException(InvalidCaptchaException exception) {
//
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.UNVERIFIED.name(), exception.getMessage());
//        List<java.lang.Error> errorDetails = exception.getDetails();
//
//        if (errorDetails != null && !errorDetails.isEmpty()) {
//            error.setDetails(errorDetails);
//        }
//
//        responseBody.setStatus(ErrorCode.UNVERIFIED.value());
//        responseBody.setError(error);
//
//        return ResponseEntity.status(ErrorCode.UNVERIFIED.statusCode()).body(responseBody);
//
//    }
//
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
////        LOG.warn("{}", keyValue("message", "Caught ResourceNotFoundException in GlobalExceptionHandler"), resourceNotFoundException);
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.NOT_FOUND.name(), resourceNotFoundException.getMessage());
//
////        if (StringUtils.isNotEmpty(resourceNotFoundException.getTarget())) {
////            error.setTarget(resourceNotFoundException.getTarget());
////        }
//
//        responseBody.setStatus(ErrorCode.NOT_FOUND.value());
//        responseBody.setError(error);
////        setTraceId(responseBody);
//
//        return ResponseEntity.status(ErrorCode.NOT_FOUND.statusCode()).body(responseBody);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleUserNameNotFoundException(UserNameNotFoundException userNameNotFoundException) {
////        LOG.warn("{}", keyValue("message", "Caught ResourceNotFoundException in GlobalExceptionHandler"), resourceNotFoundException);
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.NOT_FOUND.name(), userNameNotFoundException.getMessage());
//
////        if (StringUtils.isNotEmpty(resourceNotFoundException.getTarget())) {
////            error.setTarget(resourceNotFoundException.getTarget());
////        }
//
//        responseBody.setStatus(ErrorCode.NOT_FOUND.value());
//        responseBody.setError(error);
////        setTraceId(responseBody);
//
//        return ResponseEntity.status(ErrorCode.NOT_FOUND.statusCode()).body(responseBody);
//    }
//    @ExceptionHandler
//    public ResponseEntity<ApiResponse> handleUnauthourizedException(UnauthourizedException unauthourizedException) {
////        LOG.warn("{}", keyValue("message", "Caught ResourceNotFoundException in GlobalExceptionHandler"), resourceNotFoundException);
//
//        ApiResponse responseBody = new ApiResponse();
//        java.lang.Error error = new java.lang.Error(ErrorCode.UNAUTHORIZED.name(), unauthourizedException.getMessage());
//
////        if (StringUtils.isNotEmpty(resourceNotFoundException.getTarget())) {
////            error.setTarget(resourceNotFoundException.getTarget());
////        }
//
//        responseBody.setStatus(ErrorCode.UNAUTHORIZED.value());
//        responseBody.setError(error);
////        setTraceId(responseBody);
//
//        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.statusCode()).body(responseBody);
//    }
//
//}
