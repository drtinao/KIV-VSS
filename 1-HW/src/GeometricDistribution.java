public class GeometricDistribution {
    private int numsToGen; //count of numbers which should be generated

    // PARAMS REGARDING TO GEOMETRIC DISTRIB - START
    private double p; //probability of success - value <0, 1>
    // PARAMS REGARDING TO GEOMETRIC DISTRIB - END

    /**
     * Constructor takes params which are characteristic to geometric distribution - p and x.
     * @param p probability of success - value <0, 1>
     */
    public GeometricDistribution(double p){
        this.p = p;
    }

    /**
     * Function calculates expected E ("stredni hodnota"...).
     * @return expected mean (double)
     */
    public double calcExpectMean(){
        double e = (1 - p) / p;
        return e;
    }

    /**
     * Function calculates expected D ("rozptyl"...).
     * @return expected variance (double)
     */
    public double calcExpectVariance(){
        double d = (1 - p) / (p * p);
        return d;
    }
}
