package com.pangaea.takehome.web.advice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pangaea.takehome.web.exception.CustomDataException;
import com.pangaea.takehome.web.exception.CustomServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.pangaea.takehome.util.constant.ConstantIntegerKeys;
import com.pangaea.takehome.util.constant.ConstantStringsKeys;
import com.pangaea.takehome.web.customresponse.BaseResponse;
import com.pangaea.takehome.web.customresponse.ErrorResponse;

/**
 * @author moabdu
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(value = CustomServiceException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, CustomServiceException ex) {
		ex.printStackTrace();
		return createResponse(ex.getMessage(),ex.getErrorCode());
	}

	@ExceptionHandler(value = CustomDataException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, CustomDataException ex) {
		ex.printStackTrace();
		String message = ex.getMessage() ;
		return createResponse(message,ex.getErrorCode());
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public @ResponseBody BaseResponse handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return createResponse(ConstantStringsKeys.MAX_SIZE_EXCEPTION, ConstantIntegerKeys.MAX_SIZE_EXCEPTION);
	}

	@ExceptionHandler(value = SQLException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, SQLException ex) {
		ex.printStackTrace();
		return createResponse(ConstantStringsKeys.SQL_EXC, ConstantIntegerKeys.SQL_EXC);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, DataIntegrityViolationException ex) {
		ex.printStackTrace();
		return createResponse(ConstantStringsKeys.DATA_INTEGRITY_VIOLATION, ConstantIntegerKeys.DATA_INTEGRITY_VIOLATION);
	}

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, Exception ex) {
		ex.printStackTrace();
		return createResponse(ConstantStringsKeys.EXC,ConstantIntegerKeys.EXC);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, MethodArgumentNotValidException ex) {

		Map<String,String> errors = getErrorCodes(ex.getBindingResult());

		return new DetailedErrorResponse<>(false, ConstantStringsKeys.NOT_VALID_EXCEPTION ,errors);
	}

	@ExceptionHandler(value = BindException.class)
	public @ResponseBody BaseResponse handle(HttpServletRequest req, BindException ex) {
		Map<String,String> errors = getErrorCodes(ex.getBindingResult());
		return new DetailedErrorResponse<String, Map<String,String>>(false, ConstantStringsKeys.BIND_EXCEPTION, errors);
	}

	private BaseResponse createResponse(String errorMsg , Integer errorCode) {
		StringBuilder finalStatusMessage = new StringBuilder(errorMsg);
		return new ErrorResponse(finalStatusMessage.toString(), errorCode);
	}

	public Map<String, String> getErrorCodes(BindingResult bindingResult) {
		HashMap<String, String> errorCodes = null;
		if(bindingResult != null && bindingResult.hasErrors()) {
			errorCodes = new HashMap<>();
			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for(FieldError fieldError : fieldErrorList) {
				errorCodes.put(fieldError.getField(), fieldError.getCode()) ;
			}
		}
		return errorCodes;
	}

}

