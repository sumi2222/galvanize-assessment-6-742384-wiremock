package com.galvanize.assessment6.wiremock.demoassessment6wiremock.controller;
import com.galvanize.assessment6.wiremock.demoassessment6wiremock.service.RestCallToStripAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripapi")
public class StripAPICallController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StripAPICallController.class);

    public RestCallToStripAPI restCallToStripAPI;

    @Autowired
    private StripAPICallController(RestCallToStripAPI restCallToStripAPI){
        this.restCallToStripAPI = restCallToStripAPI;
    }


    @GetMapping("/call")
    public ResponseEntity<String> callRESTAPI() {
        String response = " Strip api call was successful. ";
        return ResponseEntity.ok(restCallToStripAPI.restCall());
    }

}
