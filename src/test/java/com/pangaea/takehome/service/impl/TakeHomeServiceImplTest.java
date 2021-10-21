package com.pangaea.takehome.service.impl;

import com.pangaea.takehome.dto.ExpectedPublishResponseDto;
import com.pangaea.takehome.dto.ExpectedSubscribeDto;
import com.pangaea.takehome.dto.ExpectedSubscribeResponseDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TakeHomeServiceImplTest {

    @Mock
    private KafkaTemplate<String, List<String>> mockKafkaTemplate;

    @InjectMocks
    private TakeHomeServiceImpl takeHomeServiceImplUnderTest;

    @Test
    public void testSaveSubscriptionsData() {
        ExpectedSubscribeDto dto = ExpectedSubscribeDto.builder().build();
        ExpectedSubscribeResponseDto expectedResult = ExpectedSubscribeResponseDto.builder().topic("topic").build();

        ExpectedSubscribeResponseDto result = takeHomeServiceImplUnderTest.saveSubscriptionsData("topic", dto);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testPublishEvent() {
        ExpectedPublishResponseDto expectedResult = ExpectedPublishResponseDto.builder().topic("topic").build();

        SendResult<String, List<String>> stringListSendResult = new SendResult<>(new ProducerRecord<>("topic", List.of("value")), new RecordMetadata(new TopicPartition("topic", 0), 0L, 0L, 0L, 0L, 0, 0));
        SettableListenableFuture<SendResult<String, List<String>>> sendResultListenableFuture = new SettableListenableFuture<>();
        sendResultListenableFuture.set(stringListSendResult);
        when(mockKafkaTemplate.send(Mockito.anyString(), Mockito.anyList())).thenReturn(sendResultListenableFuture);

        ExpectedPublishResponseDto result = takeHomeServiceImplUnderTest.publishEvent("topic");

        assertEquals(expectedResult, result);
        verify(mockKafkaTemplate).send(Mockito.anyString(), Mockito.anyList());
    }

    @Test
    public void testPublishEvent_KafkaTemplateReturnsFailure() {
        ExpectedPublishResponseDto expectedResult = ExpectedPublishResponseDto.builder().topic("topic").build();

        SettableListenableFuture<SendResult<String, List<String>>> sendResultListenableFuture = new SettableListenableFuture<>();
        sendResultListenableFuture.setException(new Exception("message"));
        when(mockKafkaTemplate.send(Mockito.anyString(), Mockito.anyList())).thenReturn(sendResultListenableFuture);

        ExpectedPublishResponseDto result = takeHomeServiceImplUnderTest.publishEvent("topic");

        assertEquals(expectedResult.getTopic(), result.getTopic());
        verify(mockKafkaTemplate).send(Mockito.anyString(), Mockito.anyList());
    }
}
