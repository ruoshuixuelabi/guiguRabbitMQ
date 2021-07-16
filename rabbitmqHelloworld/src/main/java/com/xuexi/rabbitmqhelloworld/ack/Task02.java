package com.xuexi.rabbitmqhelloworld.ack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.xuexi.rabbitmqhelloworld.utils.RabbitMqUtils;

import java.util.Scanner;

public class Task02 {

    private static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            boolean durable =true;
            channel.basicQos(1);
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入信息");
            while (sc.hasNext()) {
                String message = sc.nextLine();
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println("生产者发出消息" + message);
            }
        }
    }
}