package com.pangaea.takehome.controller;

import com.pangaea.takehome.dto.ExpectedPublishResponseDto;
import com.pangaea.takehome.dto.ExpectedSubscribeDto;
import com.pangaea.takehome.dto.ExpectedSubscribeResponseDto;
import com.pangaea.takehome.service.interfaces.TakeHomeService;
import com.pangaea.takehome.web.customresponse.SuccessResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TakeHomeControllerTest {

    @InjectMocks
    private TakeHomeController takeHomeController;

    @Mock
    private TakeHomeService takeHomeService;

    @Test
    public void subscriptionsTopicTest() {
        Mockito.when(takeHomeService.saveSubscriptionsData(Mockito.anyString(), Mockito.any())).thenReturn(ExpectedSubscribeResponseDto.builder().build());
        ResponseEntity<SuccessResponse<ExpectedSubscribeResponseDto>> responseEntity = takeHomeController.subscriptionsTopic("mo", ExpectedSubscribeDto.builder().url("mo@example.com").build());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void publishEventTest() {
        Mockito.when(takeHomeService.publishEvent(Mockito.anyString())).thenReturn(ExpectedPublishResponseDto.builder().build());
        ResponseEntity<SuccessResponse<ExpectedPublishResponseDto>> responseEntity = takeHomeController.publishEvent("mo");
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }
}
