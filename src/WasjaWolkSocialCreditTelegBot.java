import Pidrila.WasjaWolk;
import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WasjaWolkSocialCreditTelegBot extends TelegramLongPollingBot {
  @Override
  public String getBotUsername() {
    Dotenv dotenv = Dotenv.configure().load();
    return dotenv.get("BOT_USERNAME");
  }

  @Override
  public String getBotToken() {
    Dotenv dotenv = Dotenv.configure().load();
    return dotenv.get("BOT_TOKEN");
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {

      WasjaWolk wasjaWolk = new WasjaWolk();

      Message message = update.getMessage();
      String message_text = message.getText();

      String userName = message.getChat().getUserName();
      long id = message.getChat().getId();
      long chat_id = update.getMessage().getChatId();


      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chat_id);

      System.out.println(update.getMessage());

      if (userName.equals(wasjaWolk.getUserName()) || id == wasjaWolk.getId()) {
        sendMessage.setText("Danya, don't think that you can change your social rating");
      } else {
        if (message_text.equals("/start")) {
          sendMessage.setText("Hello everybody! We change @"
            + wasjaWolk.getUserName() +
            "'s social credits so often, it's easy to wonder how many extra bowls of rice he has. " +
            "Therefore, I was created so that you can precisely follow this indicator and change it :).");
        }
        if (message_text.equals("/plus")) {
          sendMessage.setText("@" + wasjaWolk.getUserName() + " My congratulations, your social rating has been increased :)\n" +
            "Now it is:\n" +
            wasjaWolk.get_socialCredits());
        }
      }

      if (message_text.equals("/minus")) {
        sendMessage.setText("@" + wasjaWolk.getUserName() + " It's a shame, your social credits has been lowered :(\n" +
          "Now it is:\n" +
          wasjaWolk.get_socialCredits());
      }

      if (message_text.equals("/credits")) {
        sendMessage.setText("@" + wasjaWolk.getUserName() + "\n" +
          "Your social credits\n" +
          wasjaWolk.get_socialCredits());
      }


      try {
        execute(sendMessage);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }
}
