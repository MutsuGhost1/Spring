package main.service;

import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class RestServiceCaller {
	
    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<JsonNode> callOtherService() {
        String createTimesoltUrl = "http://localhost:8080/api/users/1/time-slots";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("start_at", "1652578657285");
        personJsonObject.put("end_at", "1652578658285");
        HttpEntity<String> request = 
        	      new HttpEntity<String>(personJsonObject.toString(), headers);
        JsonNode responseObj =  
        	      restTemplate.postForObject(createTimesoltUrl, request, JsonNode.class);        
        return CompletableFuture.completedFuture(responseObj);
    }
}