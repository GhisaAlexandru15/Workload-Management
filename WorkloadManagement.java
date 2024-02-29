import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WorkloadManagement {

    public static int ultimaDurata;
    public static LocalDateTime ultimaData;
    public static LocalDateTime data;
    private static LocalDateTime azi=LocalDateTime.now();
    private static String dataString;
    public static int saptamana;
    public static boolean saptamanaCurenta;
    public static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //setare data generare statistici
        dataString=args[0];
        System.out.println(dataString);
        saptamana=Integer.parseInt(args[1]);
        saptamanaCurenta=Boolean.parseBoolean(args[2]);
        if(dataString.length()<5){
            data=LocalDateTime.of(azi.getYear(),azi.getMonthValue(),azi.getDayOfMonth(),23,59,59);
            System.out.println(data);
        }
        else{
            String parts[]=dataString.replace(" ", "").split("-");
            data=LocalDateTime.of(Integer.parseInt(parts[2]),Integer.parseInt(parts[1]),Integer.parseInt(parts[0]),23,59,59);
            System.out.println(data);
        }
        //citire liste antrenamente
        List<Antrenament> antrenamente = new ArrayList<Antrenament>();
        FileIn.readDate(antrenamente);
        FileIn.readForms(antrenamente);

        //citire jucatori
        List<Jucator> jucatori = new ArrayList<Jucator>();
        HashMap<Integer,Integer> jucHashMap = new HashMap<Integer, Integer>();
        FileIn.readJucatori(jucatori, jucHashMap);

        //sortare dupa data
        antrenamente.sort(new CompareAntrenament());
        int i=1;
        for(Antrenament antrenament : antrenamente){
            i++;
            try{
                jucatori.get(jucHashMap.get(antrenament.getNumar())).getAntrenamente().add(antrenament);
            }catch (Exception e) {
                System.err.println(antrenament.toString());
            }
        }
        String c= keyboard.nextLine();
        //adaugare antrenamente baza de date
        FileOut.writeDate(antrenamente);


        //Calcul statistici
        for(Jucator jucator : jucatori){
            jucator.getStatistici().resetStatistici();
            Formule.setVectors(jucator);
            jucator.getStatistici().setCurrentWeekWorkLoad(Formule.weeklyWorkLoad(jucator));
            jucator.getStatistici().setAverageDailyLoad(Formule.averageDailyLoad(jucator));
            jucator.getStatistici().setStandardDeviation(Formule.standardDeviation(jucator));
            jucator.getStatistici().setMonotony(Formule.monotony(jucator));
            jucator.getStatistici().setStrain(Formule.strain(jucator));
            jucator.getStatistici().setWeeklyChangePercent(Formule.weeklyChangePercent(jucator));
            Formule.setExpLoadVectors(jucator);
            jucator.getStatistici().setACWL(Formule.ACWL(jucator));
        }
        FileOut.writeStatistici(jucatori,saptamanaCurenta,saptamana);
        if(saptamanaCurenta)
            FileOut.raspunsuri(jucatori);
        //FileOut.verificare(jucatori);  
    }   
}
