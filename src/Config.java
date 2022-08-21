public class Config {
    private static final String TELEGRAM_API_KEY = "5798038604:AAFVwqS9WY1g2FQ6gS5u4f5UuTYxpNJOz48";
    public static final String COIN_MARKET_CAP_API_KEY = "COIN MARKET CAP API";
    private static final String TELEGRAM_URL = "https://api.telegram.org/bot";
    public static final String COIN_MARKET_CAP_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?start=1&limit=30";

    public final static String DEFAULT_TEXT_FORMAT =
            "\uD83D\uDEA8Crypto Price\uD83D\uDEA8\n" +
                    "Fetching data from server...";

    public static String getBaseUrl() {
        return TELEGRAM_URL + TELEGRAM_API_KEY + "/";
    }
}
