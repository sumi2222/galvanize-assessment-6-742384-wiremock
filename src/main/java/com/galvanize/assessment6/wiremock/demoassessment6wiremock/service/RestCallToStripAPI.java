package com.galvanize.assessment6.wiremock.demoassessment6wiremock.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCallToStripAPI {

    @Value("${stripapi.rest.call}")
    private String restapicall;

    public String restCall(){
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = restapicall;
            Object responseObject = restTemplate.getForEntity(url, Object.class);
            return " Strip api call was successful. ";
        } catch (Exception e) {
            String.format(" Rest call Exception :: %s ", e);
        }
        return "Strip api call was not successful.";
    }
}
