package example.spring.mq.config;

import com.ibm.mq.jms.MQQueue;
import example.spring.mq.message.EventListener;
import example.spring.mq.message.MessagingErrorHandler;
import example.spring.mq.message.MessagingProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class MessagingConfiguration {

// Enabling this prevents messages being placed on the backout queue (needs sessionTransacted set to true).
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
//        ConnectionFactory connectionFactory,
//        MessagingErrorHandler errorHandler) {
//
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setErrorHandler(errorHandler);
//        return factory;
//    }

    // Enabling this allows a listener implementing SessionAwareMessageListener<TextMessage>
    // to be picked up by Spring Boot.  However, such listeners don't place failed messages
    // onto the backout queue unless transacted = true!
    //
    // MS Client will put poison messages to the backout queue defined on the queue's BackoutRequeueQueue
    // (BOQNAME) after the number of roll backs exceeds that defined in the BackoutThreshold (BOTHRESH).
    // This means that the user running the JMS application not only needs access to the application queue,
    // it also needs access to put to the named backout queue too.
    //
    // If the backout queue cannot be accessed or is not defined, the queue manager's dead letter queue
    // (DEADQ attribute) will attempt to be used.
    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(
        ConnectionFactory connectionFactory,
        EventListener eventListener,
        MessagingErrorHandler errorHandler,
        MessagingProperties messagingProperties) throws JMSException {

        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setMessageListener(eventListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestination(new MQQueue(messagingProperties.getQueue()));
        container.setSessionTransacted(true); // Required for backout to work!
        container.setErrorHandler(errorHandler);
        container.setTaskExecutor(new SimpleAsyncTaskExecutor("events")); // Rename thread!
        return container;
    }
}
