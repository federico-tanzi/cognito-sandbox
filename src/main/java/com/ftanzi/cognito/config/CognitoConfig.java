package com.ftanzi.cognito.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersResult;
import com.amazonaws.services.simplesystemsmanagement.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CognitoConfig {

    @Value("${aws.cognito.endpoint}")
    private String awsEndpoint;
    @Value(value = "${aws.access-key}")
    private String accessKey;
    @Value(value = "${aws.access-secret}")
    private String secretKey;
    @Value(value = "${aws.region}")
    private String awsRegion;
    @Value(value = "${aws.region}")
    private String stage;

    @Autowired
    private AWSSimpleSystemsManagement simpleSystemsManagement;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    @Bean
    public AWSSimpleSystemsManagement simpleSystemsManagement(){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return AWSSimpleSystemsManagementClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    @Bean
    public PoolConfig poolConfig(){
        String userPoolIdConfigName = "/cognito/userPool/id/" + stage;
        String clientIdConfigName = "/cognito/client/id/" + stage;
        GetParametersRequest parameterRequest = new GetParametersRequest().withNames("/cognito/userPool/id/${var.stage}");
        GetParametersResult parameterResponse = simpleSystemsManagement.getParameters(parameterRequest);
        Map<String, String> configs = parameterResponse.getParameters().stream().collect(Collectors.toMap(Parameter::getName, Parameter::getValue));
        return new PoolConfig(configs.get(userPoolIdConfigName),configs.get(clientIdConfigName));
    }

}
