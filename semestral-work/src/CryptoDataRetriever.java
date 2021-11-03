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
    SupportedCrypto cryptoName; //selected crypto which is present in SupportedCrypto enum
    SupportedCurrency currencyName; //selected currency from SupportedCurrency enum
    /* constructor values - END */

    ArrayList<Long> dates; //contains unix timestamps (index corresponds prices)
    ArrayList<Double> prices; //contains prices (index corresponds with dates)

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
    }

    public Document downloadCryptoData(String urlToDown){
        Document pageDOM = null; /* DOM of web page (contains info regarding cryptocurrency values), which will be later parsed */

        try{
            pageDOM = Jsoup.connect(urlToDown).timeout(PAGE_DOWN_TIMEOUT).ignoreContentType(true).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Got: " + pageDOM.toString());
        return pageDOM;
    }
}
