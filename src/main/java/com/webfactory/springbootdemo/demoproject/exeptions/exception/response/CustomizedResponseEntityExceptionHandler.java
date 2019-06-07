package com.webfactory.springbootdemo.demoproject.exeptions.exception.response;

        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostMissingParameterException;
        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostNotFoundException;
        import com.webfactory.springbootdemo.demoproject.exeptions.post.exceptions.PostParameterOutOfBoundException;
        import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.*;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.context.request.WebRequest;
        import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

        import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse errorDetails = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserMissingParameterException.class, PostMissingParameterException.class, PasswordNotValidException.class, LocationMissingParameterException.class,
            EmailNotValidException.class, LocationParameterOutOfBoundException.class, NicknameNotValidException.class, PasswordNotValidException.class, UserExistsException.class,
            UserParameterOutOfBoundException.class, PostParameterOutOfBoundException.class})
    public final ResponseEntity<ExceptionResponse> hadleUserMissingParameterException(UserMissingParameterException ex, WebRequest request) {
        ExceptionResponse errorDetails = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
