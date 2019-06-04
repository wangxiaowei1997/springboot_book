package com.zzus.springbook.mqtest;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

public class RecvMQ {
    private final static String QUEUE_NAME = "test";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建工厂
        ConnectionFactory factory = SendMQ.connect(new ConnectionFactory());
        //建立新连接
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();

        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("正在监听");

        //告诉服务器需要的频道消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body ,"UTF-8");
                System.out.println("收到消息： '"+message+"'");
            }
        };

        //消息确认
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
