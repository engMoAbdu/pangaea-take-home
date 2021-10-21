package com.pangaea.takehome.service.impl;

import com.pangaea.takehome.dto.ExpectedPublishResponseDto;
import com.pangaea.takehome.dto.ExpectedSubscribeDto;
import com.pangaea.takehome.dto.ExpectedSubscribeResponseDto;
import com.pangaea.takehome.service.interfaces.TakeHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author moabdu
 */
@Service
public class TakeHomeServiceImpl implements TakeHomeService {

    @Autowired
    private KafkaTemplate<String, List<String>> kafkaTemplate;

    private static Map<String, List<String>> subscriptionsData = new HashMap<>();

    @Override
    public ExpectedSubscribeResponseDto saveSubscriptionsData(String topic, ExpectedSubscribeDto dto) {
        List<String> urls;
        if (subscriptionsData.containsKey(topic)) {
            urls = subscriptionsData.get(topic);
            urls.add(dto.getUrl());
        } else {
            urls = new ArrayList<>();
            urls.add(dto.getUrl());
            subscriptionsData.put(topic, urls);
        }
        return ExpectedSubscribeResponseDto.builder().url(dto.getUrl()).topic(topic).build();
    }

    @Override
    public ExpectedPublishResponseDto publishEvent(String topic){
        if (subscriptionsData.containsKey(topic)) {
            kafkaTemplate.send(topic, subscriptionsData.get(topic));
            return ExpectedPublishResponseDto.builder().topic(topic).urls(subscriptionsData.get(topic)).build();
        }else{
            kafkaTemplate.send(topic,new ArrayList<>());
            return ExpectedPublishResponseDto.builder().topic(topic).build();
        }
    }
}
