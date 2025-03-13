package io.sample.playground.service;

import io.sample.playground.client.TempClientConfig;
import io.sample.playground.dto.MQMessage;
import io.sample.playground.dto.MongoMessageDTO;
import io.sample.playground.dto.PostgresMessageDTO;
import io.sample.playground.mq.DemoQueue;
import io.sample.playground.repository.MongoMessageRepository;
import io.sample.playground.repository.PostgresMessageRepository;
import io.sample.playground.patterns.spring_event_driven.Interviewee;
import io.sample.playground.patterns.spring_event_driven.JobApplication;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.bson.assertions.Assertions.assertNotNull;

@Slf4j
@Service
public class DemoService {

    private final TempClientConfig tempClientConfig;

    private final DemoQueue queue;

    private final MongoMessageRepository mongoMessageRepository;

    private final PostgresMessageRepository postgresMessageRepository;

    private final JobApplication jobApplication;

    public DemoService(DemoQueue queue, MongoMessageRepository mongoMessageRepository, PostgresMessageRepository postgresMessageRepository, TempClientConfig tempClientConfig, JobApplication jobApplication) {
        this.queue = queue;
        this.postgresMessageRepository = postgresMessageRepository;
        this.mongoMessageRepository = mongoMessageRepository;
        this.tempClientConfig = tempClientConfig;
        this.jobApplication = jobApplication;
    }

    public void sendMessage(MQMessage mqMessage) {
        PostgresMessageDTO postgresMessageDTO = new PostgresMessageDTO();
        postgresMessageDTO.setName(mqMessage.getName());
        postgresMessageDTO.setDetails(mqMessage.getDetails());
        assertNotNull(postgresMessageRepository);
        postgresMessageRepository.save(postgresMessageDTO);

        MongoMessageDTO mongoMessageDTO = new MongoMessageDTO();
        mongoMessageDTO.setName(mqMessage.getName());
        mongoMessageDTO.setParentId(postgresMessageDTO.getId());
        mongoMessageRepository.save(mongoMessageDTO);

        queue.publish(mqMessage);
    }


    public String getResponseObject(String responseObjectId) throws IOException {
        Call<ResponseBody> call = tempClientConfig.getAPIClient().getResponseObject(responseObjectId);
        Response<ResponseBody> response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                try (ResponseBody body = response.body()) {
                    if (body != null) {
                        log.info("Response {} ", body);
                        return body.string();
                    }
                }
            } else {
                try (ResponseBody errorBody = response.body()) {
                    String errorMessage = null;
                    if (errorBody != null) {
                        errorMessage = errorBody.string();
                    }
                    log.error("Failed to get object  {} : {} : {}", errorMessage, response.code(), response.message());
                    return null;
                }
            }
        } catch (IOException e) {
            log.error("Request failed for object: {}", e.getMessage());
            return null;
        }
        return null;
    }

    public void submitApplication(Interviewee interviewee) {
        jobApplication.applyForJob(interviewee);
    }
}
