package com.galvanize.assessment6.wiremock.demoassessment6wiremock.controller;

import com.galvanize.assessment6.wiremock.demoassessment6wiremock.service.RestCallToStripAPI;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(value = StripAPICallController.class, secure = false)
public class StripAPICallControllerTest {

    @Autowired
    StripAPICallController stripAPICallController;

    @MockBean
    RestCallToStripAPI restCallToStripAPI;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void restAPITest() {
        String expected = " Strip api call was successful. ";
        when(restCallToStripAPI.restCall()).thenReturn(expected);
        stubFor(get(urlEqualTo("/stripapi/call"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expected)));

        ResponseEntity<String> result = stripAPICallController.callRESTAPI();

        assertTrue(result.getStatusCodeValue() == 200);
        verify(getRequestedFor(urlMatching("/stripapi/call"))
                .withRequestBody(matching(expected))
                .withHeader("Content-Type", matching("application/json")));
    }
}
