package com.stackroute.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.demo.domain.Activities;
import com.stackroute.demo.domain.Objects;
import com.stackroute.succour.newsapiadapter.adapter.NewsAPIAdapter;
import com.stackroute.succour.newsapiadapter.exceptions.EmptyAPIQueryURIException;
import com.stackroute.succour.newsapiadapter.exceptions.EmptyQueryParamsException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {


    @Value("${kafka.topic.json}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Activities> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(DataService.class);

    private NewsAPIAdapter newsAPIAdapter;
    private ObjectMapper objectMapper;

    public DataService() {
        try {
            newsAPIAdapter = new NewsAPIAdapter();
            objectMapper = new ObjectMapper();
        } catch (IOException e) {
//            Todo Handle exception
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        newsAPIAdapter.addQueryParam("India");
        newsAPIAdapter.getArticleSubject().subscribe(
                article -> {
                    System.out.println(article);
                    Activities activities = objectMapper.readValue(article.toString(), Activities.class);
                    kafkaTemplate.send(jsonTopic,activities);
                },
                e -> logger.error(e.getLocalizedMessage())
        );
        try {
            newsAPIAdapter.startNewsStream();
        } catch (EmptyQueryParamsException e) {
            //TODO Handle exception
            e.printStackTrace();
        } catch (EmptyAPIQueryURIException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
//        Objects objects=new Objects();
//        objects.setActor("abc");
//        objects.setVerb("hello");
//        kafkaTemplate.send(jsonTopic, objects);
    }




}
