public class Statistici {
    private double currentWeekWorkLoad;
    private double weeklyChangePercent;
    private double averageDailyLoad;
    private double standardDeviation;
    private double monotony;
    private double strain;
    private double ACWL;

    public static int expDays=70;
    private double[][] RPEVector=new double[expDays][2];
    private double[][] sRPEVector=new double[expDays][2];
    private double[][] loadVector=new double[expDays][2];
    private double[] expAcuteLoadVector=new double[expDays];
    private double[] expChronicLoadVector=new double[expDays];

    public void resetStatistici(){
        this.RPEVector=new double[expDays][2];
        this.sRPEVector=new double[expDays][2];
        this.loadVector=new double[expDays][2];
    }

    public void setCurrentWeekWorkLoad(double currentWeekWorkLoad) {
        this.currentWeekWorkLoad = currentWeekWorkLoad;
    }

    public double getCurrentWeekWorkLoad() {
        return currentWeekWorkLoad;
    }

    public void setAverageDailyLoad(double averageDailyLoad) {
        this.averageDailyLoad = averageDailyLoad;
    }

    public double getAverageDailyLoad() {
        return averageDailyLoad;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setMonotony(double monotony) {
        this.monotony = monotony;
    }

    public double getMonotony() {
        return monotony;
    }

    public void setStrain(double strain) {
        this.strain = strain;
    }

    public double getStrain() {
        return strain;
    }

    public void setACWL(double ACWL) {
        this.ACWL = ACWL;
    }

    public double getACWL() {
        return ACWL;
    }

    public void setLoadVector(double valoare, int pozitie, int ora){
        this.loadVector[pozitie][ora]=valoare;
    }

    public double getLoadVector(int pozitie){
        return loadVector[pozitie][0]+loadVector[pozitie][1];
    }

    public double getLoadVectorE(int pozitie, int ora){
        return loadVector[pozitie][ora];
    }

    public void setRPEVector(double valoare, int pozitie, int ora){
        this.RPEVector[pozitie][ora]=valoare;
    }

    public double getRPEVector(int pozitie){
        return RPEVector[pozitie][0]+RPEVector[pozitie][1];
    }

    public double getRPEVectorE(int pozitie, int ora){
        return RPEVector[pozitie][ora];
    }

    public void setsRPEVector(double valoare, int pozitie, int ora){
        this.sRPEVector[pozitie][ora]=valoare;
    }

    public double getsRPEVector(int pozitie){
        return sRPEVector[pozitie][0]+sRPEVector[pozitie][1];
    }

    public double getsRPEVectorE(int pozitie, int ora){
        return sRPEVector[pozitie][ora];
    }

    public void setExpAcuteLoadVector(double valoare, int pozitie){
        this.expAcuteLoadVector[pozitie]=valoare;
    }

    public double getExpAcuteLoadVector(int pozitie){
        return expAcuteLoadVector[pozitie];
    }

    public void setExpChronicLoadVector(double valoare, int pozitie){
        this.expChronicLoadVector[pozitie]=valoare;
    }

    public double getExpChronicLoadVector(int pozitie){
        return expChronicLoadVector[pozitie];
    }

    public void setWeeklyChangePercent(double weeklyChangePercent){
        this.weeklyChangePercent=weeklyChangePercent;
    }

    public double getWeeklyChangePercent(){
        return weeklyChangePercent;
    }

}
