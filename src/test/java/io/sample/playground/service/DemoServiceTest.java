package io.sample.playground.service;

import io.sample.playground.client.TempClient;
import io.sample.playground.client.TempClientConfig;
import io.sample.playground.dto.MQMessage;
import io.sample.playground.dto.MongoMessageDTO;
import io.sample.playground.dto.PostgresMessageDTO;
import io.sample.playground.mq.DemoQueue;
import io.sample.playground.repository.MongoMessageRepository;
import io.sample.playground.repository.PostgresMessageRepository;
import io.sample.playground.patterns.spring_event_driven.Interviewee;
import io.sample.playground.patterns.spring_event_driven.JobApplication;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class DemoServiceTest {
    @Mock
    private DemoQueue queue;
    @Mock
    private MongoMessageRepository mongoMessageRepository;
    @Mock
    private PostgresMessageRepository postgresMessageRepository;
    @Mock
    private TempClientConfig tempClientConfig;
    @Mock
    private TempClient tempClient;
    @Mock
    private JobApplication jobApplication;
    @InjectMocks
    private DemoService demoService;
    private Call<ResponseBody> call;

    @BeforeEach
    void setUp() {
        call = mock(Call.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_sending_a_message_then_verify_it_is_persisted_and_published() {
        MQMessage message = new MQMessage();
        message.setName("name");
        message.setDetails("details");

        demoService.sendMessage(message);


        verify(postgresMessageRepository, times(1)).save(any(PostgresMessageDTO.class));
        verify(mongoMessageRepository, times(1)).save(any(MongoMessageDTO.class));
        verify(queue, times(1)).publish(any(MQMessage.class));
    }
    @Test
    void when_testing_API_get_execution_success_then_return_execution() throws IOException {
        String executionName = "test-execution";
        String executionId = "executionId";

        ResponseBody body = ResponseBody.create(executionName, MediaType.get("application/json"));
        Response<ResponseBody> response = Response.success(body);

        when(tempClientConfig.getAPIClient()).thenReturn(tempClient);
        when(tempClient.getResponseObject(executionId)).thenReturn(call);
        when(call.execute()).thenReturn(response);

        String result = demoService.getResponseObject(executionId);

        assertEquals(executionName, result);
    }

    @Test
    void when_testing_API_get_execution_failure_then_return_null() throws IOException {
        String executionName = "test-execution";
        String executionId = "executionId";

        ResponseBody body = ResponseBody.create("Execution not found", MediaType.get("application/json"));
        Response<ResponseBody> response = Response.error(404,body);

        when(tempClientConfig.getAPIClient()).thenReturn(tempClient);
        when(tempClient.getResponseObject(executionId)).thenReturn(call);
        when(call.execute()).thenReturn(response);

        String result = demoService.getResponseObject(executionId);

        assertNull(result);
    }

    @Test
    void testSubmitApplication() {
        Interviewee test = new Interviewee();
        test.setNameOfApplicant("John Doe");

        demoService.submitApplication(test);

        verify(jobApplication, times(1)).applyForJob(test);
    }
}