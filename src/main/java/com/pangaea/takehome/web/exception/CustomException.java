package com.pangaea.takehome.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author moabdu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = 6008437565530199228L;

	private final int errorCode;
	
	public CustomException (String message, int errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	
}
