import Pidrila.WasjaWolk;
import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class WasjaWolkSocialCreditTelegBot extends TelegramLongPollingBot {

  WasjaWolk wasjaWolk = new WasjaWolk();

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
      Message message = update.getMessage();
      String message_text = message.getText();


      boolean isWasjaWolk = isWasjaWolk(message, wasjaWolk);
      boolean hasAnswer = false;

      long chat_id = update.getMessage().getChatId();

      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(chat_id);

      ReplyKeyboardMarkup keyboard = generateKeyBoard();
      sendMessage.setReplyMarkup(keyboard);

      System.out.println(update.getMessage());

      System.out.println(message_text);

      if (message_text.equals("/start")) {
        hasAnswer = true;
        sendMessage.setText("Hello everybody! We change @"
          + wasjaWolk.getUserName() +
          "'s social credits so often, it's easy to wonder how many extra bowls of rice he has. " +
          "Therefore, I was created so that you can precisely follow this indicator and change it :).");
      }

      if (message_text.equals("plus") && !isWasjaWolk) {
        hasAnswer = true;

        wasjaWolk.set_socialCredits(wasjaWolk.get_socialCredits() + 1000);

        sendMessage.setText("@" + wasjaWolk.getUserName() + " My congratulations, your social rating has been increased :)\n" +
          "Now it is:\n" +
          wasjaWolk.get_socialCredits());
      }
      if (message_text.equals("plus") && isWasjaWolk){
        hasAnswer = true;
        sendMessage.setText("Danya, don't think that you can change your social rating");
      }

      if (message_text.equals("minus") && !isWasjaWolk) {
        hasAnswer = true;

        wasjaWolk.set_socialCredits(wasjaWolk.get_socialCredits() - 1000);

        sendMessage.setText("@" + wasjaWolk.getUserName() + " It's a shame, your social credits has been lowered :(\n" +
          "Now it is:\n" +
          wasjaWolk.get_socialCredits());
      }
      if(message_text.equals("minus") && isWasjaWolk){
        hasAnswer = true;
        sendMessage.setText("Danya, don't think that you can change your social rating");
      }

      if (message_text.equals("social credits")) {
        hasAnswer = true;
        sendMessage.setText("@" + wasjaWolk.getUserName() + "\n" +
          "Your social credits\n" +
          wasjaWolk.get_socialCredits());
      }
      if(!hasAnswer){
        sendMessage.setText("Unknown command");
      }


      try {
        execute(sendMessage);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  private ReplyKeyboardMarkup generateKeyBoard() {

    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    List<KeyboardRow> keyboard = new ArrayList<>();
    KeyboardRow row = new KeyboardRow();

    row.add("plus");
    row.add("minus");
    row.add("social credits");

    keyboard.add(row);
    keyboardMarkup.setKeyboard(keyboard);

    return keyboardMarkup;
  }

  private boolean isWasjaWolk(Message message, WasjaWolk wasjaWolk) {
    return message.getChat().getUserName().equals(wasjaWolk.getUserName()) || message.getChat().getId() == wasjaWolk.getId();
  }
}
