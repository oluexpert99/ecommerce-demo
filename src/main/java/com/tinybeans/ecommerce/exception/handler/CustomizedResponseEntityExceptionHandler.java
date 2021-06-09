package com.tinybeans.ecommerce.exception.handler;



import com.tinybeans.ecommerce.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
      Exception ex, WebRequest request) {
    String errorMessage = ex.getLocalizedMessage();

    if (StringUtils.isBlank(errorMessage)) {
      errorMessage = "Service error , Please contact the admin";
    }

    log.error(errorMessage, ex);
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), errorMessage, request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleTenantNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            ex.getDefaultUserMessage(),
            request.getDescription(false),
            ex.getGlobalisationMessageCode());
    log.error(ex.getDefaultUserMessage(), ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidationException.class)
  public final ResponseEntity<ExceptionResponse> handleValidationException(
      ValidationException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            ex.getDefaultUserMessage(),
            request.getDescription(false),
            ex.getGlobalisationMessageCode());
    log.error(ex.getDefaultUserMessage(), ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnAuthorizedUserException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidUserException(
      UnAuthorizedUserException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            ex.getDefaultUserMessage(),
            request.getDescription(false),
            ex.getGlobalisationMessageCode());
    log.error(ex.getDefaultUserMessage(), ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UnAuthenticatedUserException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidUserException(
      UnAuthenticatedUserException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            ex.getDefaultUserMessage(),
            request.getDescription(false),
            ex.getGlobalisationMessageCode());
    log.error(ex.getDefaultUserMessage(), ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(PlatformServiceException.class)
  public final ResponseEntity<ExceptionResponse> handleInternalServiceException(
      PlatformServiceException ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(
            new Date(),
            ex.getDefaultUserMessage(),
            request.getDescription(false),
            ex.getGlobalisationMessageCode());
    log.error(ex.getDefaultUserMessage(), ex);
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }



  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ExceptionResponse error = new ExceptionResponse(new Date(), "Validation Failed", details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
