package com.zzus.springbook.mqtest;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wangwei
 */
public class SendMQ {

    private final static String QUEUE_NAME = "test";

    public static ConnectionFactory connect(ConnectionFactory factory){
        factory.setHost("94.191.114.165");
        factory.setVirtualHost("/");
        factory.setUsername("wang");
        factory.setPassword("123");
        factory.setPort(5672);
        return  factory;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = connect(new ConnectionFactory());
        //创建连接
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息到队列中
        String message = "Hello,Mr.rabbit";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes("UTF-8"));
        System.out.println("发送： '"+message+"' ");
        //关闭通道和连接
        channel.close();
        connection.close();



    }
}

