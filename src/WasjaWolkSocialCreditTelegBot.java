import Pidrila.WasjaWolk;
import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
      long wasjaWolkID = wasjaWolk.getId();
      Message message = update.getMessage();
      String userName = message.getChat().getUserName();
      long id = message.getChat().getId();
      long chat_id = update.getMessage().getChatId();

      System.out.println(id);

      System.out.println(userName);

      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chat_id);

      System.out.println(update.getMessage());

      if (userName.equals(wasjaWolk.getUserName()) || id == wasjaWolkID){

        sendMessage.setText("Danya, don't think that you can change your social rating");
      } else {
        sendMessage.setText("Test");
      }



      try {
        execute(sendMessage);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }
}
