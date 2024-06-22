package com.pangaea.takehome.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author moabdu
 */
@Data
@Builder
public class ExpectedSubscribeDto {
    private String url;
}
