import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WasjaWolkSocialCreditTelegBot extends TelegramLongPollingBot {
  @Override
  public String getBotUsername() {
    return null;
  }

  @Override
  public String getBotToken() {
    Dotenv dotenv = Dotenv.configure().load();
    return dotenv.get("BOT_TOKKEN");
  }

  @Override
  public void onUpdateReceived(Update update) {

  }
}
