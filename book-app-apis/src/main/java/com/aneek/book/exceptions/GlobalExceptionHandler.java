// 9th

package com.aneek.book.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aneek.book.payloads.ApiResponse;

@RestControllerAdvice // used for global exception handling
public class GlobalExceptionHandler {
	 
	@ExceptionHandler(ResourceNotFoundException.class) // telling exception handler which class exception it need to handel
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp = new HashMap<>(); // to store all the exceptions to put data in key value pairs
		
		ex.getBindingResult().getAllErrors().forEach((error)->{ // Storing all the errors on map using foreach lamda expression
//getBindingResult().getAllErrors() in Spring Boot retrieves all validation errors
//associated with the object that was submitted to the server.
			
			//type casting object errors to field errors to get the getField method
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
