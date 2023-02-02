package org.jrmthblt.sharedlibrary.catalogservice.web;

import java.util.HashMap;
import java.util.Map;

import org.jrmthblt.sharedlibrary.catalogservice.domain.BookAlreadyExistsException;
import org.jrmthblt.sharedlibrary.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Marks the class as a centralized exception handler
@RestControllerAdvice
public class BookControllerAdvice {

	// Defines the exception for which the handler must be executed
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookNotFoundHandler(BookNotFoundException ex) {
		// The message that will be included in the HTTP response body
		return ex.getMessage();
	}

	@ExceptionHandler(BookAlreadyExistsException.class)
	// Defines the status code for the HTTP response created when the exception is thrown
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String bookAlreadyExistsHandler(BookAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	// Handles the exception thrown when the Book validation fails
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex
			) {
		var errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			// Collects meaningful error messages about which Book fields were invalid instead of returning an empty message
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
