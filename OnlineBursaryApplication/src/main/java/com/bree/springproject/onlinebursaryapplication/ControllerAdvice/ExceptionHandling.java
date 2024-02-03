package com.bree.springproject.onlinebursaryapplication.ControllerAdvice;

import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.InvalidPhoneNumberException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserDoesNotExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.UserExistException;
import com.bree.springproject.onlinebursaryapplication.CustomeExceptions.WeakPasswordException;
import com.bree.springproject.onlinebursaryapplication.models.ExceptionModel;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Setter
public class ExceptionHandling {

    @ExceptionHandler(value = UserDoesNotExistException.class)
   public ResponseEntity<ExceptionModel> handleUserNonExistence(UserDoesNotExistException userDoesNotExistException)
    {

        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(userDoesNotExistException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleJSONValidation(MethodArgumentNotValidException e)
    {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errorMap.put(field, message);
    });


        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WeakPasswordException.class)
    public ResponseEntity<ExceptionModel> handleWeakPasswordException(WeakPasswordException passwordException)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionModel.setMessage(passwordException.getMessage());
        exceptionModel.setDate(new Date());

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserExistException.class)
    public ResponseEntity<ExceptionModel> handleUserExistException(UserExistException existException)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(existException.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidPhoneNumberException.class)
    public ResponseEntity<ExceptionModel> handleInvalidPhoneNumberException(InvalidPhoneNumberException e)
    {
        ExceptionModel exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(e.getMessage());
        exceptionModel.setDate(new Date());
        exceptionModel.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(exceptionModel, HttpStatus.BAD_REQUEST);
    }

}
