package app.event.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class QueueConfigurer {

    /**
     *
     * Topic - Published message goes to all the subscribers who are interested
     * Queue - A single message will be received by exactly one consumer
     *
     */
    @Bean
    public Topic testTopic() {
        return new ActiveMQTopic("test-topic");
    }

    @Bean
    public Queue testQueue() {
        return new ActiveMQQueue("test-queue");
    }
}
