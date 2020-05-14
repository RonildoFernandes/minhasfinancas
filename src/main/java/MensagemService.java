import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
@Component
public class MensagemService {
	@Value("${application.name}")
	private String appName;
}
