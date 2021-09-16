/**
 * 
 */
package com.ftanzi.cognito.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParameterStoreConfig {

	@Value("${aws.endpoint}")
	private String awsEndpoint;
	@Value(value = "${aws.access-key}")
	private String accessKey;
	@Value(value = "${aws.access-secret}")
	private String secretKey;
	@Value(value = "${aws.region}")
	private String awsRegion;

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

}