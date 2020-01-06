package example.spring.mq.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

// Enabling this prevents messages being placed on the backout queue.
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
}
