package com.example.DatabaseRestService;

import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.*;
import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DatabaseRestService {
    private DynamoDbClient dynamoDbClient;

    public DatabaseRestService() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Specify the desired AWS region
                .credentialsProvider(DefaultCredentialsProvider.builder().build()) // Use AWS credentials
                .build();
    }

    public void createTable(String tableName, String primaryKey) {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName(primaryKey)
                                .keyType(KeyType.HASH)
                                .build()
                )
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName(primaryKey)
                                .attributeType(ScalarAttributeType.S)
                                .build()
                )
                .provisionedThroughput(
                        ProvisionedThroughput.builder()
                                .readCapacityUnits(5L)
                                .writeCapacityUnits(5L)
                                .build()
                )
                .build();

        dynamoDbClient.createTable(request);
    }

    public void putItem(String tableName, String primaryKey, String value) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(
                        Map.of(
                                primaryKey, AttributeValue.builder().s(value).build()
                        )
                )
                .build();

        dynamoDbClient.putItem(request);
    }

    public void getItem(String tableName, String primaryKey, String value) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(
                        Map.of(
                                primaryKey, AttributeValue.builder().s(value).build()
                        )
                )
                .build();

        dynamoDbClient.getItem(request);
    }
}
