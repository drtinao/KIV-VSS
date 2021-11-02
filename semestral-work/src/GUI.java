import javax.swing.*;
import java.awt.*;

/**
 * Class is responsible for managing GUI and presenting GUI components to user.
 */
public class GUI {
    /* constants regarding to dashboard window - START */
    private static final String DASHBOARD_FRAME_TITLE = "Dashboard"; //title of the dashboard window
    private static final int DASHBOARD_SIZE_WIDTH = 1024; //width of the dashboard window
    private static final int DASHBOARD_SIZE_HEIGHT = 768; //height of the dashboard window
    /* constants regarding to dashboard window - END */

    public void showDashboard(){
        /* prepare Jlabels present in dashboard window - START */
        JLabel selCryptoL = new JLabel("cryptocurrency: ");
        JLabel realPriceGraphL = new JLabel("real price graph: ");
        JLabel selPredModelL = new JLabel("prediction model: ");
        JLabel predPriceGraphL = new JLabel("predicted price graph: ");
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
        mainP.add(realPriceGraphL);
        mainP.add(selPredModelPanel); //panel with crypto pred. model selection
        mainP.add(predPriceGraphL);
        /* create main panel which consists of previously defined JPanels - END */

        /* create JFrame which will contain defined items - START */
        JFrame zadaniHodnotFrame = new JFrame(DASHBOARD_FRAME_TITLE);
        zadaniHodnotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zadaniHodnotFrame.add(mainP);
        zadaniHodnotFrame.setSize(DASHBOARD_SIZE_WIDTH,DASHBOARD_SIZE_HEIGHT);
        zadaniHodnotFrame.setResizable(false);
        zadaniHodnotFrame.setLocationRelativeTo(null);
        zadaniHodnotFrame.setVisible(true);
        /* create JFrame which will contain defined items - END */
    }
}
