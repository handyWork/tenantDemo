package com.example.tenant.rabbitMq.routerMode;

import com.example.tenant.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式
 * 发布消息时  在basicPublish 时指定 路由key，
 * 消费者，在绑定交换机时，需要订阅 routing key    路由的key可以是多个
 */

public class Send {
    private final static String EXCHANGE_NAME = "test_direct_exchange";

    public static void main(String[] argv) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为direct
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 消息内容，
        String message = "注册成功！请短信回复[T]退订";
        // 发送消息，并且指定routing key 为：sms，只有短信服务能接收到消息
        channel.basicPublish(EXCHANGE_NAME, "sms", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}