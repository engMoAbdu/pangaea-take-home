package com.pangaea.takehome.web.advice;

import java.io.Serializable;

import com.pangaea.takehome.web.customresponse.BaseResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author moabdu
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DetailedErrorResponse<H extends Serializable , D extends Object> extends BaseResponse{

	private H errorHeader ;
	private D errorDetails  ;
	
	public DetailedErrorResponse(Boolean success, D errorMsgs) {
		super(success);
		this.errorDetails = errorMsgs ;
	}

	public DetailedErrorResponse(Boolean success, H errorTitle,D errorMsgs) {
		this(success , errorMsgs);
		this.errorHeader = errorTitle ;
	}

	public H getErrorHeader() {
		return errorHeader;
	}

	public D getErrorDetails() {
		return errorDetails;
	}

	public void setErrorHeader(H errorHeader) {
		this.errorHeader = errorHeader;
	}

	public void setErrorDetails(D errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	
}
