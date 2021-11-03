import com.google.gson.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Class is responsible for retrieving data regarding cryptocurrency prices from internet.
 */
public class CryptoDataRetriever {
    /* constants - START */
    private static final int PAGE_DOWN_TIMEOUT = 12000; //timeout in millis
    private static final String PAGE_TO_DOWN = "https://api.coingecko.com/api/v3/coins/CRYPTO_PLACEHOLDER/market_chart?vs_currency=CURRENCY_PLACEHOLDER&days=max"; //base url for crypto data
    /* constants - END */

    /* constructor values - START */
    private SupportedCrypto cryptoName; //selected crypto which is present in SupportedCrypto enum
    private SupportedCurrency currencyName; //selected currency from SupportedCurrency enum
    /* constructor values - END */

    private ArrayList<Long> dates; //contains unix timestamps (index corresponds prices)
    private ArrayList<Double> prices; //contains prices (index corresponds with dates)

    /**
     * Constructor takes just name of selected crypto (one from enum SupportedCrypto).
     * @param cryptoName pick crypto from enum SupportedCrypto
     * @param currencyName pick currency from enum SupportedCurrency
     */
    public CryptoDataRetriever(SupportedCrypto cryptoName, SupportedCurrency currencyName){
        this.cryptoName = cryptoName;
        this.currencyName = currencyName;
    }

    /**
     * Retrieves / parses historical data for given cryptocurrency. Uses two Lists - one for time, second for price at given time.
     */
    public void refreshHistoricalData(){
        //init ArrayLists with data / remove previous data
        this.dates = new ArrayList<>();
        this.prices = new ArrayList<>();

        String cryptoDataUrl = PAGE_TO_DOWN.replace("CRYPTO_PLACEHOLDER", cryptoName.toString()).replace("CURRENCY_PLACEHOLDER", currencyName.toString());
        Document cryptoDataDOM = downloadCryptoData(cryptoDataUrl);
        parseCryptoData(cryptoDataDOM);
    }

    /**
     * Downloads data regarding to historical prices from web and returns page DOM.
     * @param urlToDown url which is used as source for crypto data
     * @return source page DOM
     */
    private Document downloadCryptoData(String urlToDown){
        Document pageDOM = null; /* DOM of web page (contains info regarding cryptocurrency values), which will be later parsed */

        try{
            pageDOM = Jsoup.connect(urlToDown).timeout(PAGE_DOWN_TIMEOUT).ignoreContentType(true).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("Got: " + pageDOM.toString());
        return pageDOM;
    }

    /**
     * Parses given DOM with crypto data - ie. gets dates / prices from it. ArrayList<Long> dates + ArrayList<Double> prices are filled with respective data.
     * @param pageDOM source page DOM
     */
    private void parseCryptoData(Document pageDOM){
        /* jsoup library added html tags to json -> side effect, remove it */
        String[] jsonSplit1 = pageDOM.toString().split("<body>");
        String[] jsonSplit2 = jsonSplit1[1].split("</body>");

        String pureJson = jsonSplit2[0].trim();

        JsonParser parser = new JsonParser();
        JsonElement rootEl = parser.parse(pureJson);
        JsonObject rootObj = rootEl.getAsJsonObject();
        JsonArray data = rootObj.get("prices").getAsJsonArray();

        for(JsonElement priceTag : data){
            JsonArray datePrice = priceTag.getAsJsonArray(); //arr 0 = unix timestamp, date ; 1 = price at given day
            long date = datePrice.get(0).getAsLong();
            double price = datePrice.get(1).getAsDouble();

            this.dates.add(date);
            this.prices.add(price);
        }
    }

    /**
     * Getter for ArrayList with unix timestamps, ie. dates of prices.
     * @return ArrayList<Long> with times of prices
     */
    public ArrayList<Long> getDates(){
        return this.dates;
    }

    /**
     * Getter for Arraylist with crypto prices.
     * @return ArrayList<Double> with prices
     */
    public ArrayList<Double> getPrices(){
        return this.prices;
    }
}
