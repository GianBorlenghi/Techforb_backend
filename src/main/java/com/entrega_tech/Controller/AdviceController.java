package com.entrega_tech.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.entrega_tech.Exception.BusinessException;
import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Exception.UserNotFoundException;
import com.entrega_tech.Model.ErrorDTO;


import io.jsonwebtoken.ExpiredJwtException;

import java.net.http.HttpHeaders;

@ControllerAdvice

public class AdviceController  extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
		Map<String, Object> body = new HashMap<>();
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x-> x.getDefaultMessage())
				.collect(Collectors.toList());
		body.put("errors dsfdsfdsfdsfdsfdsfdsf", errors);
		System.out.println(errors);
		return new ResponseEntity<Object>("VERY BAD", status);
				}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
		ErrorDTO errorDTO = new ErrorDTO();
	    errorDTO.setTimeStamp(new Date());

	    if (ex instanceof RequestException) {
	        RequestException requestException = (RequestException) ex;
	        errorDTO.setCode(requestException.getCode());
	        errorDTO.setMessage(requestException.getMessage());
	        return new ResponseEntity<>(errorDTO, requestException.getStatus());
	    } else {
	    	
	        errorDTO.setCode("GEN-500");
	        errorDTO.setMessage("Ha ocurrido un error inesperado");
	        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorDTO> businessExceptionHandler(BusinessException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorDTO>(errorDTO, ex.getStatus());
		
	}

	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ErrorDTO> userNotFoundExceptionHandler(UserNotFoundException ex){
		
		ErrorDTO errorDTO = new ErrorDTO();
		
		errorDTO.setCode(ex.getCode());
		errorDTO.setMessage(ex.getMessage());
		errorDTO.setTimeStamp(new Date());
		
		return new ResponseEntity<ErrorDTO>(errorDTO, ex.getStatus());
	}
	
	 @ExceptionHandler(value = {ExpiredJwtException.class})
	 public ResponseEntity<ErrorDTO> handleExpiredJwtException(ExpiredJwtException ex) {
		 ErrorDTO errorDTO = new ErrorDTO();
			
		 	errorDTO.setCode("P-403");
			errorDTO.setMessage("Expired token");
			errorDTO.setTimeStamp(new Date());
			
			return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.UNAUTHORIZED);
		 
	 }
	 
	 @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException ex) {
		 ErrorDTO errorDTO = new ErrorDTO();
		 errorDTO.setCode("P-500");
			errorDTO.setMessage("Credenciales no validas.");
			errorDTO.setTimeStamp(new Date());
	        return new ResponseEntity<ErrorDTO>(errorDTO,HttpStatus.BAD_REQUEST);
	    }
	/* public class GlobalExceptionHandler {

	     @ExceptionHandler(MethodArgumentNotValidException.class)
	     public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
	         List<String> errors = ex.getBindingResult().getAllErrors().stream()
	                                  .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                                  .collect(Collectors.toList());
	         return ResponseEntity.badRequest().body(errors);
	     }
	 }*/
	 
	 

}
