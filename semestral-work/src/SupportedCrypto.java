/**
 * Enum contains names of supported cryptocurrencies.
 */
public enum SupportedCrypto {
    BITCOIN, LITECOIN, ETHEREUM;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
