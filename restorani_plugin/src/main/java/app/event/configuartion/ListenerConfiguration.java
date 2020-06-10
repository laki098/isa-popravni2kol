package app.event.configuartion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;

@Configuration
public class ListenerConfiguration {
    @Autowired
    ConnectionFactory connectionFactory;

//    Definisanje bean-a za Topic listener fabriku.
    @Bean
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

//    Definisanje bean-a za Queue listener fabriku.
    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

//    Definisanje jms template bean-a za Queue poruke.
//    @Bean
//    public JmsTemplate jmsQueueTemplate() {
//        JmsTemplate template = new JmsTemplate(connectionFactory);
//        template.setPubSubDomain(false);
//        return template;
//    }

//    Definisanje jms template bean-a za Topic poruke.
    @Bean
    public JmsTemplate jmsTopicTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setPubSubDomain(true);
        return template;
    }

//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        ??
//        factory.setTrustedPackages(Arrays.asList("app.dto"));
//        factory.setTrustAllPackages(false);
//        factory.setTrustAllPackages(true);
//        return factory;
//    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();


        factory.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void handleError(Throwable t) {
                        System.err.println("An error has occurred in the transaction");
                    }
                });


        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));

        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
