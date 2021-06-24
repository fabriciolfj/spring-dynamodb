package com.github.fabriciolfj.springdynamodb.repositories;

import com.github.fabriciolfj.springdynamodb.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;

@Repository
public class OrderRepository {

    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public PageIterable<Order> findOrdersByValue(final String customerId, final double orderValue) {
        var table = getTable();
        var attibuteValue = AttributeValue.builder()
                .n(String.valueOf(orderValue))
                .build();

        var expression = createExpression(attibuteValue, ":value", "orderValue > :value");
        var queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(customerId).build());

        return table.query(r -> r.queryConditional(queryConditional)
                .filterExpression(expression));
    }

    private Expression createExpression(AttributeValue attibuteValue, String attribute, String condition) {
        var expressionValues = new HashMap<String, AttributeValue>();
        expressionValues.put(attribute, attibuteValue);

        return Expression.builder()
                .expression(condition)
                .expressionValues(expressionValues)
                .build();
    }

    public PageIterable<Order> findAll(final String customerId, final String orderId) {
        var table = getTable();
        return table.scan();
    }

    public void save(final Order order) {
        var table = getTable();
        table.putItem(order);
    }

    public Order getOrder(final String customerId, final String orderId) {
        var table = getTable();
        Key key = getKey(customerId, orderId);

        return table.getItem(key);
    }

    public void deleteOrder(final String customerId, final String orderId) {
        var table = getTable();
        var key = getKey(customerId, orderId);
        var deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        table.deleteItem(deleteRequest);
    }

    private DynamoDbTable<Order> getTable() {
        return dynamoDbEnhancedClient
                .table("Order", TableSchema.fromBean(Order.class));
    }

    private Key getKey(String customerId, String orderId) {
        return Key.builder().partitionValue(customerId)
                .sortValue(orderId)
                .build();
    }
}
