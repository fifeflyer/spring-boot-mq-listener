package example.spring.mq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventType", visible = true )
@JsonSubTypes({
    @JsonSubTypes.Type(value = CreditCard.class, name = "CREDIT_CARD_CHANGE_PAYMENT_METHOD_EVENT"),
    @JsonSubTypes.Type(value = DirectDebit.class, name = "DIRECT_DEBIT_CHANGE_PAYMENT_METHOD_EVENT"),
    @JsonSubTypes.Type(value = Invoice.class, name = "INVOICE_CHANGE_PAYMENT_METHOD_EVENT") }
)
public class Event {

    private String eventType;
}
