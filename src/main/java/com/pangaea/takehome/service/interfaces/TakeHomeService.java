package com.pangaea.takehome.service.interfaces;

import com.pangaea.takehome.dto.ExpectedPublishResponseDto;
import com.pangaea.takehome.dto.ExpectedSubscribeDto;
import com.pangaea.takehome.dto.ExpectedSubscribeResponseDto;

/**
 * @author moabdu
 */
public interface TakeHomeService {
    /**
     * fetch data by rule id
     *
     * @param topic topic
     * @param dto url
     * @return Result<ExpectedSubscribeResponseDto.class>
     */
    ExpectedSubscribeResponseDto saveSubscriptionsData(String topic, ExpectedSubscribeDto dto);

    /**
     * fetch data by rule id
     *
     * @param topic topic
     * @return Result<ExpectedPublishResponseDto.class>
     */
    ExpectedPublishResponseDto publishEvent(String topic);
}
