import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;

/**
 * Responsible for graph creations and modifications.
 */
public class GraphManager {
    private JFreeChart createdGraph; //chart which was created
    private XYSeriesCollection dataset; //dataset which should be displayed within the graph

    /* constructor values - START */
    private String graphTitle; //title of the chart
    private ArrayList<Long> dates; //contains unix timestamps (index corresponds prices)
    private ArrayList<Double> prices; //contains prices (index corresponds with dates)
    /* constructor values - END */

    /**
     * Constructor takes reference to values (time + price) which should be displayed within the chart (XYDataset object) + title of the chart.
     * Also inits chart object which is modified later on.
     * @param dates unix timestamps (index corresponds prices)
     * @param prices prices (index corresponds with dates)
     * @param graphTitle title of the chart
     */
    public GraphManager(ArrayList<Long> dates, ArrayList<Double> prices, String graphTitle){
        this.graphTitle = graphTitle;
        this.dates = dates;
        this.prices = prices;

        prepDataset();
        this.createdGraph = createGraph(); //prepare chart
    }

    /**
     * Prepares XYDataset dataset from values contained in ArrayList<Long> dates + ArrayList<Double> prices. Dataset can be used in chart later on.
     * @return dataset with dates + prices values
     */
    private void prepDataset(){
        dataset = new XYSeriesCollection();
        XYSeries datePriceSeries = new XYSeries("Data");

        for(int i = 0; i < dates.size(); i++){
            datePriceSeries.add(dates.get(i), prices.get(i));
        }

        dataset.addSeries(datePriceSeries);
    }

    /**
     * Creates chart with data given in constructor (can be modified later on).
     * @return JFreeChart chart
     */
    private JFreeChart createGraph(){
        return ChartFactory.createTimeSeriesChart(
                graphTitle,
                null,
                null,
                dataset,
                false,
                false,
                false);
    }

    /**
     * Returns initialized JFreeChart object which represents chart, which can be modified (add values etc.)
     * @return chart (JFreeChart object)
     */
    public JFreeChart getGraph(){
        return createdGraph;
    }
}
