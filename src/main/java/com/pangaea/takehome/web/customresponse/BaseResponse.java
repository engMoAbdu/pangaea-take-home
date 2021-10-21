package com.pangaea.takehome.web.customresponse;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author moabdu
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class BaseResponse {

	private Boolean success;
	private String message;
	private Integer msgCode;

	public BaseResponse(Boolean success) {
		super();
		this.success = success;
	}

	public BaseResponse(Boolean success, String errorMsg, Integer errorCode) {
		super();
		this.success = success;
		this.message = errorMsg;
		this.msgCode = errorCode;
	}

	public BaseResponse(String errorMsg, Integer errorCode) {
		super();
		this.message = errorMsg;
		this.msgCode = errorCode;
	}
}
