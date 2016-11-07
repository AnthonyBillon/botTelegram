import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by abillon on 07/11/16.
 */
public class OptymoBot extends TelegramLongPollingBot {

    private Calendar calendar;
    public OptymoBot()
    {
        Date date = new Date();   // given date
        calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
    }


    public class BotConfig {
        public static final String BOT_USERNAME = "OPTYMObot";
        public static final String BOT_TOKEN = "280119426:AAFEu-ZdAeFPrt4S55ReQZ1MXkCCA5XUUUs";
    }

    @Override
    public void onUpdateReceived(Update update) {
        int hour = calendar.HOUR;
        int minute = calendar.MINUTE;
        String h;
        String m;

        if(hour<10) h = "0"+hour;
        else h = ""+hour;
        if(minute<10) m = "0"+minute;
        else m = ""+hour;


        //check if the update has a message
        if(update.hasMessage()){
            Message message = update.getMessage();

            //check if the message has text. it could also contain for example a location ( message.hasLocation() )
            if(message.hasText()){
                if(message.getText().equals("BUS")) {
                    //create an object that contains the information to send back the message
                    SendMessage sendMessageRequest = new SendMessage();
                    sendMessageRequest.setChatId(message.getChatId().toString()); //who should get from the message the sender that sent it.
                    sendMessageRequest.setText("OPTYMO\nVotre titre de transport SMS est valide à partir du " + Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + Calendar.YEAR + " " + Calendar.HOUR_OF_DAY + "h"+Calendar.MINUTE + " et pour une durée d'1 heure.\nNo AX17-C0FQ-0QQC\nMerci et bon voyage");
                    try {
                        sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                    } catch (TelegramApiException e) {
                        //do some error handling
                    }
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
