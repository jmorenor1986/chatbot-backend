package co.com.santander.chatbot.backend.web.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppProperties {
    @Value("${param.mesesExtracto}")
    private Long mesesExtracto;

}
