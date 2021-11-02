/**
 * Class is responsible for retrieving data regarding cryptocurrency prices from internet.
 */
public class CryptoDataRetriever {
    SupportedCrypto cryptoName; //selected crypto which is present in SupportedCrypto enum

    /**
     * Constructor takes just name of selected crypto (one from enum SupportedCrypto).
     * @param cryptoName pick crypto from enum SupportedCrypto
     */
    public CryptoDataRetriever(SupportedCrypto cryptoName){
        this.cryptoName = cryptoName;
    }


}
