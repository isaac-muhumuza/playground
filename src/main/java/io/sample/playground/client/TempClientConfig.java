package io.sample.playground.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class TempClientConfig {

    private final ObjectMapper mapper;

    @Value("${temp.url:http://localhost:8080/}")
    private String url;

    public TempClientConfig(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    // create client
    public TempClient getAPIClient() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createCLient())
                .build()
                .create(TempClient.class);
    }

    // create interceptor client
    public OkHttpClient createCLient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.addInterceptor(chain -> {
            Request originalRequest = chain.request();

            Request chainRequest = originalRequest.newBuilder()
                    //.addHeader("Authorization", "Bearer " + "token")
                    .addHeader("add-header-option-here", "9e271bda-8aa7-4a1b-9481-5ec538449d06")
                    .method(originalRequest.method(), originalRequest.body())
                    .build();

            return chain.proceed(chainRequest);
        });

        return client.build();
    }
}
