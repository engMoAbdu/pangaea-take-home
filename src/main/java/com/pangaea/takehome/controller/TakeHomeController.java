package com.pangaea.takehome.controller;

import com.pangaea.takehome.dto.ExpectedPublishResponseDto;
import com.pangaea.takehome.dto.ExpectedSubscribeDto;
import com.pangaea.takehome.dto.ExpectedSubscribeResponseDto;
import com.pangaea.takehome.service.interfaces.TakeHomeService;
import com.pangaea.takehome.util.constant.ConstantIntegerKeys;
import com.pangaea.takehome.util.constant.ConstantStringsKeys;
import com.pangaea.takehome.web.customresponse.SuccessResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author moabdu
 */
@RestController
@RequestMapping("/api/statistics")
@Tag(name = "TakeHome", description = "Receive topics and store it then publish events based on topic for all urls which attached on it")
public class TakeHomeController {
    @Autowired
    private TakeHomeService takeHomeService;

    @PostMapping(value = "/subscribe/{topic}")
    public ResponseEntity<SuccessResponse<ExpectedSubscribeResponseDto>> subscriptionsTopic(
            @PathVariable(value = "topic") String topic, @RequestBody ExpectedSubscribeDto dto) {
        return new ResponseEntity<>(
                new SuccessResponse<>(ConstantStringsKeys.SUBSCRIBED_SUCCESSFULLY, ConstantIntegerKeys.SUBSCRIBED_SUCCESSFULLY,
                        takeHomeService.saveSubscriptionsData(topic, dto)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/publish/{topic}")
    public ResponseEntity<SuccessResponse<ExpectedPublishResponseDto>> publishEvent(
            @PathVariable(value = "topic") String topic){
        return new ResponseEntity<>(
                new SuccessResponse<>(ConstantStringsKeys.PUBLISHED_SUCCESSFULLY, ConstantIntegerKeys.PUBLISHED_SUCCESSFULLY,
                        takeHomeService.publishEvent(topic)), HttpStatus.CREATED);
    }
}
