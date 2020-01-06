package example.spring.mq.message;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MessagingProperties {

    @Value("${messaging.queue}")
    private String queue;
}
