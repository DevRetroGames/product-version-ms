package com.productversion.productversionms.config;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.productversion.productversionms.exceptions.CustomNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {
	private static final String CAUSE = "cause" ;
	
	@ExceptionHandler({ 
	  MethodArgumentNotValidException.class 
	})
	public ResponseEntity< Map< String , Object > > valid( 
	    MethodArgumentNotValidException ex ) {
		
		Map< String , Object > resp = new HashMap<>() ;
		
		ex
		  .getBindingResult()
		  .getAllErrors()
		  .forEach( 
		    error -> {
			
    			String fieldName = ( (FieldError) error ).getField() ;
    			String message = error.getDefaultMessage() ;
    			
    			resp.put( fieldName , message ) ;
    			resp.put( "code" , HttpStatus.BAD_REQUEST.value() ) ;
			
		    }) 
		  ;
		
		return new ResponseEntity<>( resp , HttpStatus.BAD_REQUEST ) ;
		
	}
	
	
	
	@ExceptionHandler({ 
		MissingServletRequestParameterException.class ,
	    UnsatisfiedServletRequestParameterException.class ,
	    HttpRequestMethodNotSupportedException.class ,
	    ServletRequestBindingException.class ,
	    HttpMessageNotReadableException.class ,
	    MissingServletRequestPartException.class ,
	    MultipartException.class
	})
	@ResponseStatus( value = HttpStatus.BAD_REQUEST )
	public @ResponseBody Map<String, Object> handleRequestException( Exception ex ) {
		
	    Map< String , Object >  map = new HashMap<>() ;
	    
	    map.put( CAUSE , ex.getMessage() ) ;
	    map.put( "code" , HttpStatus.BAD_REQUEST.value() ) ;
	    
	    return map ;
	    
	}
	
	@ExceptionHandler({ 
	  FileNotFoundException.class ,
	  CustomNotFoundException.class 
  })
  @ResponseStatus( value = HttpStatus.NOT_FOUND )
  public @ResponseBody Map< String , Object > handleFileNotFoundException( Exception e ) {
    
      Map< String , Object >  map = new HashMap<>() ;
      
      map.put( CAUSE , e.getMessage() ) ;
      map.put( "code" , HttpStatus.NOT_FOUND.value() ) ;
      
      return map ;
      
  }
	
	@ExceptionHandler({
	    DuplicateKeyException.class
	})
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody Map<String, Object> handleConflict(Exception ex) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("cause", ex.getMessage());
	    map.put("code", HttpStatus.CONFLICT.value());
	    return map;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleGenericException(Exception ex) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("cause", ex.getMessage());
	    map.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
	    return map;
	}
	
}
