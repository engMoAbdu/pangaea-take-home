package com.pangaea.takehome.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author moabdu
 */
@Data
@Builder
public class ExpectedSubscribeResponseDto {
    private String url;
    private String topic;
}
