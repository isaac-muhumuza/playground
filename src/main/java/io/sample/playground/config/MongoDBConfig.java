package io.sample.playground.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@Configuration
@EnableMongoRepositories(basePackages = "io.sample.playground.repository")
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Value("${mongodb.uri}")
    private String uri;

    @Value("${mongodb.host:localhost}")
    private String host;

    @Value("${mongodb.port:27017}")
    private int port;

    @Value("${mongodb.database}")
    private String dbName;

    @Value("${mongodb.user}")
    private String user;

    @Value("${mongodb.password:#{null}}")
    private Optional<String> pass;


    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    private String getUri() {
        //return uri;
        return "mongodb://%s:%d/%s".formatted(host, port, dbName);
    }

    @Override
    public MongoClient mongoClient() {
        final MongoCredential credential = MongoCredential.createCredential(user, dbName, pass.orElse("").toCharArray());
        final ConnectionString connectionString = new ConnectionString(getUri());

        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
}
