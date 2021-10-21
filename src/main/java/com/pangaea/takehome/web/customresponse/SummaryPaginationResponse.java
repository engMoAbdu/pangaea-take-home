package com.pangaea.takehome.web.customresponse;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author moabdu
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SummaryPaginationResponse<T> extends BaseResponse{

	private Long totalElements;
	private List<T> data ;
	private int numberOfPages;
	private Integer pageNumber;
	private Integer size;

	public SummaryPaginationResponse(List<T> data) {
		super(Boolean.TRUE);
		this.data = data ;
	}

	public SummaryPaginationResponse(boolean errorStatus, String errorMsg, Integer errorCode, List<T> data, Long totalElements, int numberOfPages) {
		super(errorStatus, errorMsg, errorCode);
		this.data = data;
		this.totalElements = totalElements;
		this.numberOfPages = numberOfPages;
	}

	public SummaryPaginationResponse(String message, Integer msgCode, List<T> data, Long totalElements, int numberOfPages) {
		super(message, msgCode);
		this.data = data;
		this.totalElements = totalElements;
		this.numberOfPages = numberOfPages;
	}

	public SummaryPaginationResponse(String message, Integer msgCode, List<T> data, Long totalElements, int numberOfPages, Integer pageNumber, Integer size) {
		super(message, msgCode);
		this.totalElements = totalElements;
		this.data = data;
		this.numberOfPages = numberOfPages;
		this.pageNumber = pageNumber;
		this.size = size;
	}
}
