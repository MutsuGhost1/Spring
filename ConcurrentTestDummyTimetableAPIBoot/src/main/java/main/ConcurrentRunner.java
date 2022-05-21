package main;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.JsonNode;

import main.service.RestServiceCaller;

@Component
public class ConcurrentRunner implements CommandLineRunner {
	private static final Logger logger = LogManager.getLogger(ConcurrentRunner.class.getName());

	private final int MAX_NUM = 5;
	
    @Autowired
    RestServiceCaller restServiceCaller;

	@Override
	public void run(String... args) throws Exception {
        Instant start = Instant.now();

        int okCnt=0;
        List<CompletableFuture<JsonNode>> allFutures = new ArrayList<>();
        for (int i = 0; i < MAX_NUM; i++) {
        	try {
        		logger.error(String.format("[%3d] Before - callOtherService", i));
                allFutures.add(restServiceCaller.callOtherService());
                okCnt++;
        	} catch (HttpClientErrorException e) {
        		logger.error(e.toString());
        	} finally {
        		logger.error(String.format("[%3d] After - callOtherService", i));
        	}
        }
        CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();
        for (int i = 0; i < okCnt; i++) {
            System.out.println("response: " + allFutures.get(i).get().toString());
        }

        System.out.println("Total time: " + Duration.between(start, Instant.now()).getSeconds());
	}
}