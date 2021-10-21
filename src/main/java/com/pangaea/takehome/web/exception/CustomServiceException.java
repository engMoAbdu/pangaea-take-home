package com.pangaea.takehome.web.exception;

/**
 * This exception is for Service 
 *
 * @author moabdu
 */
public class CustomServiceException extends CustomException {

	private static final long serialVersionUID = 7168555867098356326L;

	public CustomServiceException (String message,int errorCode){
		super(message,errorCode);
	}
}
