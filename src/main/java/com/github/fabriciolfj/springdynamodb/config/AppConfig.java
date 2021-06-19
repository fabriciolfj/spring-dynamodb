package com.github.fabriciolfj.springdynamodb.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.github.fabriciolfj.springdynamodb.repositories")
public class AppConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        var credentials = new ProfileCredentialsProvider("default");
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(credentials)
                .build();
    }
}
