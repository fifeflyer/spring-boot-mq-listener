package example.spring.mq.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
@Slf4j
public class MessagingErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable throwable) {
        log.info("Failed to receive message [{}]", throwable.getMessage());
    }
}
