package com.pangaea.takehome.web.customresponse;

public final class ErrorResponse extends BaseResponse {

	public ErrorResponse(String errorMsg , Integer errorCode) {
		super(Boolean.FALSE, errorMsg , errorCode);
	}
}
