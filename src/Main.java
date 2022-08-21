import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        BotCore bot = new BotCore();
        String initializeMessageJson = bot.sendMessage("-1001653717756", Config.DEFAULT_TEXT_FORMAT);
        String[] parsedInitializedMessage = parseTelegramJson(initializeMessageJson);;

        while (true) {
            try {
                Thread.sleep(50000);
                String priceJson = bot.getNewPrice();
                String message = "\uD83D\uDEA8Crypto Price\uD83D\uDEA8\n...\n";

                List<CoinModel> arrayList = parseCoinJson(priceJson);

                for (int i = 0; i < arrayList.size(); i++) {
                    message += (i+1 + " - " + arrayList.get(i).getName() + ": " + arrayList.get(i).getPrice() + "\uD83D\uDCB0\n");
                }
                bot.editMessage(parsedInitializedMessage[0], parsedInitializedMessage[1], message);
                System.out.println(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static List<CoinModel> parseCoinJson(String json) {
        ArrayList<CoinModel> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(new CoinModel(jsonArray.getJSONObject(i).getString("name"), jsonArray.getJSONObject(i).getJSONObject("quote").getJSONObject("USD").getFloat("price")));
        }
        return list;
    }

    private static String[] parseTelegramJson(String json) {
        String[] ids = new String[2];
        final JSONObject obj = new JSONObject(json);
        JSONObject result = obj.getJSONObject("result");
        ids[0] = result.getJSONObject("chat").get("id").toString();
        ids[1] = result.get("message_id").toString();
        return ids;
    }
}