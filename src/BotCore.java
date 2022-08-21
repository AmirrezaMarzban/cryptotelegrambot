import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class BotCore {
    //chat id = -1001653717756
    private final String SEND_MESSAGE = "sendMessage?";
    private final String EDIT_MESSAGE = "editMessageText?";
    private final String T_BASE_URL = Config.getBaseUrl();
    private final String COIN_BASE_URL = Config.COIN_MARKET_CAP_URL;

    public String sendMessage(String chat_id, String text) throws UnsupportedEncodingException {
        return makeHttpRequest(T_BASE_URL + SEND_MESSAGE + "chat_id="+chat_id+"&text="+ URLEncoder.encode(text, "UTF-8"));
    }

    public String editMessage(String chat_id, String message_id, String text) throws UnsupportedEncodingException {
        return makeHttpRequest(T_BASE_URL + EDIT_MESSAGE + "chat_id="+chat_id+"&message_id="+message_id+"&text="+ URLEncoder.encode(text, "UTF-8"));
    }

    public String getNewPrice() {
        return makeHttpRequest(COIN_BASE_URL);
    }

    private String makeHttpRequest(String url) {
        StringBuilder message = new StringBuilder();
        try {
            URLConnection connection = new URL(url).openConnection();
            if(!url.contains("telegram"))
                connection.setRequestProperty("X-CMC_PRO_API_KEY", Config.COIN_MARKET_CAP_API_KEY);
            InputStream response = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            String line = "";
            while ((line = reader.readLine()) != null)
                message.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message.toString();
    }
}
