package example.spring.mq.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.spring.mq.model.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
//public class EventListener {
//
//    private final ObjectMapper objectMapper;
//
//    @JmsListener(destination = "ORGANISATION.EVENTS.BOS.A")
//    public void onMessage(Message message) {
//        if (message instanceof TextMessage) {
//            try {
//                String msg = ((TextMessage) message).getText();
//                Event event = objectMapper.readValue(msg, Event.class);
//                log.info("Message type [{}]", event.getEventType());
//            } catch (JMSException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            throw new IllegalArgumentException("Message Error");
//        }
//    }
//}
public class EventListener implements SessionAwareMessageListener<TextMessage> {

    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(TextMessage textMessage, Session session) {
        try {
                String msg = textMessage.getText();
                Event event = objectMapper.readValue(msg, Event.class);
                log.info("Message type [{}]", event.getEventType());
            } catch (JMSException | IOException e) {
                throw new RuntimeException(e);
            }
    }
}
