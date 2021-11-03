import org.jfree.chart.ChartPanel;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class is responsible for managing GUI and presenting GUI components to user.
 */
public class GUI {
    /* constants regarding to dashboard window - START */
    private static final String DASHBOARD_FRAME_TITLE = "Dashboard"; //title of the dashboard window
    private static final String GRAPH_REAL_PRICE = "real price history"; //title of first displayed chart - price history
    private static final String GRAPH_PREDICT_PRICE = "predicted price"; //title of sec displayed chart - price according to pred model
    private static final int DASHBOARD_SIZE_WIDTH = 1024; //width of the dashboard window
    private static final int DASHBOARD_SIZE_HEIGHT = 768; //height of the dashboard window
    /* constants regarding to dashboard window - END */

    public void showDashboard(){
        /* prepare Jlabels present in dashboard window - START */
        JLabel selCryptoL = new JLabel("cryptocurrency: ");
        JLabel selPredModelL = new JLabel("prediction model: ");
        /* prepare Jlabels present in dashboard window - END */

        /* prepare JComboBoxes present in dashboard - START */
        JComboBox selCryptoCB = new JComboBox(SupportedCrypto.values());
        JComboBox selPredModelCB = new JComboBox(SupportedPredMethods.values());
        /* prepare JComboBoxes present in dashboard - END */

        /* prepare JButtons present in dashboard - START */
        JButton selCryptoSubmit = new JButton("get data & show graph");
        JButton selPredModelSubmit = new JButton("calculate prediction");
        /* prepare JButtons present in dashboard - END */

        /* prepare JPanels containing of previously defined items - START */
        JPanel selCryptoPanel = new JPanel();
        selCryptoPanel.setLayout(new BoxLayout(selCryptoPanel, BoxLayout.LINE_AXIS));
        selCryptoPanel.add(selCryptoL);
        selCryptoPanel.add(selCryptoCB);
        selCryptoPanel.add(selCryptoSubmit);

        JPanel selPredModelPanel = new JPanel();
        selPredModelPanel.setLayout(new BoxLayout(selPredModelPanel, BoxLayout.LINE_AXIS));
        selPredModelPanel.add(selPredModelL);
        selPredModelPanel.add(selPredModelCB);
        selPredModelPanel.add(selPredModelSubmit);
        /* prepare JPanels containing of previously defined items - END */

        /* create main panel which consists of previously defined JPanels - START */
        JPanel mainP = new JPanel();
        mainP.setLayout(new BoxLayout(mainP, BoxLayout.PAGE_AXIS));
        mainP.add(selCryptoPanel); //panel with crypto name selection
        mainP.add(new ChartPanel(null)); //allocate space for graph with real crypto prices
        mainP.add(selPredModelPanel); //panel with crypto pred. model selection
        //mainP.add(new ChartPanel(null)); //allocate space for graph with predicted crypto prices

        CryptoDataRetriever cryptoData = new CryptoDataRetriever(SupportedCrypto.BITCOIN, SupportedCurrency.USD);
        cryptoData.refreshHistoricalData();
        ArrayList<Long> cryptoDates = cryptoData.getDates();
        ArrayList<Double> cryptoPrices = cryptoData.getPrices();

        GraphManager graph = new GraphManager(cryptoDates, cryptoPrices, "grafik");
        mainP.add(new ChartPanel(graph.getGraph())); //allocate space for graph with predicted crypto prices
        /* create main panel which consists of previously defined JPanels - END */

        /* create JFrame which will contain defined items - START */
        JFrame mainFrame = new JFrame(DASHBOARD_FRAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainP);
        mainFrame.setSize(DASHBOARD_SIZE_WIDTH,DASHBOARD_SIZE_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        /* create JFrame which will contain defined items - END */
    }

    private XYDataset createDataset( ) {
        final TimeSeries series = new TimeSeries( "Random Data" );
        Second current = new Second( );
        double value = 100.0;

        for (int i = 0; i < 4000; i++) {

            try {
                value = value + Math.random( ) - 0.5;
                series.add(current, new Double( value ) );
                current = ( Second ) current.next( );
            } catch ( SeriesException e ) {
                System.err.println("Error adding to series");
            }
        }

        return new TimeSeriesCollection(series);
    }
}
