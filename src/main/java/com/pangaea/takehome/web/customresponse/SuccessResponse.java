package com.pangaea.takehome.web.customresponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author moabdu
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SuccessResponse<T> extends BaseResponse{

	private T data ;

	public SuccessResponse(T data) {
		super(Boolean.TRUE);
		this.data = data ;
	}
	
	public SuccessResponse(String responseMessage, int errorCode, T model) {
		super(Boolean.TRUE, responseMessage, errorCode);
		this.data = model;
	}

	public SuccessResponse(String responseMessage, int errorCode) {
		super(responseMessage, errorCode);
	}
}
