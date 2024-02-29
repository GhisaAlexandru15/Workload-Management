import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Formule {
    public static LocalDateTime azi = WorkloadManagement.data;
    public static LocalDateTime aziMaine = LocalDateTime.of(azi.getYear(),azi.getMonthValue(),azi.getDayOfMonth(),0,0,1);
    
    public static void setVectors(Jucator jucator){
        for(Antrenament antrenament : jucator.getAntrenamente()){
            int perioada=(int) ChronoUnit.DAYS.between(antrenament.getData(),azi);
            int perioada2=(int) ChronoUnit.DAYS.between(antrenament.getData(), aziMaine);
            if(perioada<Statistici.expDays && perioada>=0 && perioada2>=0)
            {
                if(antrenament.getData().getHour()<=15){
                    jucator.getStatistici().setRPEVector(antrenament.getDificultate(), perioada, 0);
                    jucator.getStatistici().setsRPEVector(antrenament.getDurata(), perioada, 0);
                    jucator.getStatistici().setLoadVector(antrenament.getDificultate()*antrenament.getDurata(), perioada, 0);
                }
                else{
                    jucator.getStatistici().setRPEVector(antrenament.getDificultate(), perioada, 1);
                    jucator.getStatistici().setsRPEVector(antrenament.getDurata(), perioada, 1);
                    jucator.getStatistici().setLoadVector(antrenament.getDificultate()*antrenament.getDurata(), perioada, 1);
                }
            }
        }
    }

    public static double weeklyWorkLoad(Jucator jucator){
        double sum=0;
        for(int i=0; i<7; i++)
            sum=sum+jucator.getStatistici().getLoadVector(i);
        return sum;
    }

    public static double weeklyChange(Jucator jucator){
        double sum=0;
        for(int i=7; i<14; i++)
            sum=sum+jucator.getStatistici().getLoadVector(i);
        return jucator.getStatistici().getCurrentWeekWorkLoad()-sum;
    }

    public static double weeklyChangePercent(Jucator jucator){
        double sum=0;
        for(int i=7; i<14; i++)
            sum=sum+jucator.getStatistici().getLoadVector(i);
        return (jucator.getStatistici().getCurrentWeekWorkLoad()-sum)/sum*100;
    }

    public static double averageDailyLoad(Jucator jucator){
        double sum=0;
        for(int i=0; i<7; i++)
            sum=sum+jucator.getStatistici().getLoadVector(i);
        return sum/7;
    }

    public static double averageDailyLoadPrevious(Jucator jucator){
        double sum=0;
        for(int i=7; i<14; i++)
            sum=sum+jucator.getStatistici().getLoadVector(i);
        return sum/7;
    }

    public static double standardDeviation(Jucator jucator){
        double sum=0;
        for(int i=0; i<7; i++)
            sum=sum+Math.pow((jucator.getStatistici().getLoadVector(i)-jucator.getStatistici().getAverageDailyLoad()),2);
        return Math.sqrt(sum/7);
    }

    public static double standardDeviationChange(Jucator jucator){
        double avg=0;
        for(int i=7; i<14; i++)
            avg=avg+jucator.getStatistici().getLoadVector(i);
        avg=avg/7;
        double sum=0;
        for(int i=7; i<14; i++)
            sum=sum+Math.pow((jucator.getStatistici().getLoadVector(i)-avg),2);
        return Math.sqrt(sum/7)-jucator.getStatistici().getStandardDeviation()-jucator.getStatistici().getStandardDeviation();
    }

    public static double monotony(Jucator jucator){
        return jucator.getStatistici().getAverageDailyLoad()/jucator.getStatistici().getStandardDeviation();
    }

    public static double strain(Jucator jucator){
        return jucator.getStatistici().getAverageDailyLoad()*jucator.getStatistici().getMonotony();
    }

    public static void setExpLoadVectors(Jucator jucator){
        for(int i=68; i>=0; i--)
        {
            jucator.getStatistici().setExpAcuteLoadVector(jucator.getStatistici().getLoadVector(i)*2/8+
                                                        jucator.getStatistici().getExpAcuteLoadVector(i+1)*6/8,i);
            jucator.getStatistici().setExpChronicLoadVector(jucator.getStatistici().getLoadVector(i)*2/29+
                                                           jucator.getStatistici().getExpChronicLoadVector(i+1)*27/29,i);
        }
    }

    public static double ACWL(Jucator jucator){
        return jucator.getStatistici().getExpAcuteLoadVector(0)/jucator.getStatistici().getExpChronicLoadVector(0);
    }

    public static String weekDay(LocalDateTime data){
        LocalDateTime ziua0=LocalDateTime.of(2024,1,14,0,0,1);
        int perioada=(int) ChronoUnit.DAYS.between(ziua0, data)%7;
        switch (perioada) {
            case 0:
                return "Dumininca";
            case 1:
                return "Luni";
            case 2:
                return "Marti";
            case 3:
                return "Miercuri";
            case 4:
                return "Joi";
            case 5:
                return "Vineri";
            case 6:
                return "Sambata";
            default:
                return "Error";
        }
    }

}
