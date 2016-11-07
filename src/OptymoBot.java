import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Created by abillon on 07/11/16.
 */
public class OptymoBot extends TelegramLongPollingBot {


    public class BotConfig {
        public static final String BOT_USERNAME = "OPTYMObot";
        public static final String BOT_TOKEN = "280119426:AAFEu-ZdAeFPrt4S55ReQZ1MXkCCA5XUUUs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        //check if the update has a message
        if(update.hasMessage()){
            Message message = update.getMessage();

            //check if the message has text. it could also contain for example a location ( message.hasLocation() )
            if(message.hasText()){
                //create an object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
                sendMessageRequest.setText("you said: " + message.getText());
                try {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    //do some error handling
                }
            }
        }
    }

    @Override
    public java.lang.String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public java.lang.String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new OptymoBot());
        } catch (TelegramApiException e) {
            BotLogger.error("aaa", e);
        }
    }
}
