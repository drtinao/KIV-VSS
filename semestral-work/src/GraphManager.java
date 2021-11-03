import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

/**
 * Responsible for graph creations and modifications.
 */
public class GraphManager {
    JFreeChart createdGraph; //chart which was created
    XYDataset dataset; //dataset which should be displayed within the graph
    String graphTitle; //title of the chart

    /**
     * Constructor takes reference to values (time + price) which should be displayed within the chart (XYDataset object) + title of the chart.
     * Also inits chart object which is modified later on.
     * @param dataset data which should be presented in timeseries chart
     * @param graphTitle title of the chart
     */
    public GraphManager(XYDataset dataset, String graphTitle){
        this.dataset = dataset;
        this.graphTitle = graphTitle;
        this.createdGraph = createGraph();
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
