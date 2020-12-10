package guru.springframework.msscbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// this will catch all exception handler
@ControllerAdvice
public class MvcExceptionHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

		e.getConstraintViolations().forEach(constrainViolation -> errors
				.add(constrainViolation.getPropertyPath() + " : " + constrainViolation.getMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<ObjectError>> handleBindException(BindException ex) {
		return new ResponseEntity<>(ex.getAllErrors(), HttpStatus.BAD_REQUEST);
	}
}
