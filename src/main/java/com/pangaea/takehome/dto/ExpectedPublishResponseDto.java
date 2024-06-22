package com.pangaea.takehome.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author moabdu
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpectedPublishResponseDto {
    private String topic;
    @Builder.Default
    private List<String> urls = new ArrayList<>();
}
