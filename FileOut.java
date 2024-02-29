import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FileOut {

    //salvare date în Date/Date.csv după citire
    public static void writeDate(List<Antrenament> antrenamente){
        //copiere date inițiale -> suprasciere
        try {
            File file = new File("Date/Date.csv");
            if (file.createNewFile()) 
              System.out.println("File created: " + file.getName());
            else 
              System.out.println("File already exists.");   
          }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        try {
            FileWriter myWriter = new FileWriter("Date/Date.csv", true);
            myWriter.write("\"Adaugare date: " +LocalDateTime.now().toString()+"-----------------------------------------------\"\r\n");
            for(Antrenament antrenament : antrenamente){
                if(ChronoUnit.SECONDS.between(WorkloadManagement.ultimaData,antrenament.getData())>0)
                    myWriter.write(antrenament.toString());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //statistici .csv pentru fiecare jucator
    public static void writeStatistici(List<Jucator> jucatori, boolean overview, int saptamana){
        if(overview){
            try {
                File file = new File("Statistici/0.csv");
                if (file.createNewFile()) 
                System.out.println("File created: " + file.getName());
                else 
                System.out.println("File already exists.");   
            }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("Statistici/0.csv",true);
                myWriter.write("\"Nume\",\"A:CWL\",\"RPE Load Saptamanal\",\"Schimbari RPE Load Saptamanal\",\"Monotonia\",\"Solicitarea\"\r\n");
                for (Jucator jucator : jucatori){
                    myWriter.write("\""+jucator.toString()+"\",\""+Math.floor(jucator.getStatistici().getACWL()*100)/100+"\",\""+jucator.getStatistici().getCurrentWeekWorkLoad()+"\",\""+jucator.getStatistici().getWeeklyChangePercent()
                    +"%\",\""+Math.floor(jucator.getStatistici().getMonotony()*100)/100+"\",\""+Math.floor(jucator.getStatistici().getStrain()*100)/100+"\"\r\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        for(Jucator jucator : jucatori){
            //Jucator jucator=jucatori.get(0);
            try {
                File file = new File("Statistici/"+jucator.toString()+".csv");
                if (file.createNewFile()) 
                  System.out.println("File created: " + file.getName());
                else 
                  System.out.println("File already exists.");   
              }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            try {
                FileWriter myWriter = new FileWriter("Statistici/"+jucator.toString()+".csv",true);
                myWriter.write("\"Săptămâna "+saptamana+"\"\r\n");
                myWriter.write("\"Ziua\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+Formule.weekDay(WorkloadManagement.data.minusDays(i))+"\",");
                myWriter.write("\"\"\r\n");

                //Dimineata
                myWriter.write("\"Dimineata\"\r\n");
                myWriter.write("\"RPE\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getRPEVectorE(i,0)+"\",");
                myWriter.write("\"\"\r\n");

                myWriter.write("\"sRPE\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getsRPEVectorE(i,0)+"\",");
                myWriter.write("\"\"\r\n");

                myWriter.write("\"RPE Load\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getLoadVectorE(i,0)+"\",");

                myWriter.write("\"\"\r\n");
                //Seara
                myWriter.write("\"Seara\"\r\n");
                myWriter.write("\"RPE\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getRPEVectorE(i,1)+"\",");
                myWriter.write("\"\"\r\n");

                myWriter.write("\"sRPE\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getsRPEVectorE(i,1)+"\",");
                myWriter.write("\"\"\r\n");

                myWriter.write("\"RPE Load\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getLoadVectorE(i,1)+"\",");
                myWriter.write("\"\"\r\n");

                //Sume
                myWriter.write("\r\n");
                myWriter.write("\"Traditional RPE Load\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getLoadVector(i)+"\",");
                myWriter.write("\"\"\r\n");
                myWriter.write("\"Exponential RPE Load\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+Math.floor(jucator.getStatistici().getExpAcuteLoadVector(i)*100)/100+"\",");
                myWriter.write("\"\"\r\n");

                //Statistici saptamanale
                myWriter.write("\r\n\"RPE Load - Saptamanal\",");
                myWriter.write("\""+jucator.getStatistici().getCurrentWeekWorkLoad()+"\"\r\n");
                myWriter.write("\"RPE Load - Media zilnica\",");
                myWriter.write("\""+Math.floor(jucator.getStatistici().getAverageDailyLoad()*100)/100+"\"\r\n");
                myWriter.write("\"RPE Load - Deviatia Standard\",");
                myWriter.write("\""+Math.floor(jucator.getStatistici().getStandardDeviation()*100)/100+"\"\r\n");
                myWriter.write("\"Monotonia\",");
                myWriter.write("\""+Math.floor(jucator.getStatistici().getMonotony()*100)/100+"\"\r\n");
                myWriter.write("\"Solicitarea\",");
                myWriter.write("\""+Math.floor(jucator.getStatistici().getStrain()*100)/100+"\"\r\n\r\n");

                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void verificare(List<Jucator> jucatori){
        for(Jucator jucator : jucatori){
            //Jucator jucator=jucatori.get(0);
            try {
                File file = new File("Statistici/"+jucator.toString()+".csv");
                if (file.createNewFile()) 
                  System.out.println("File created: " + file.getName());
                else 
                  System.out.println("File already exists.");   
              }catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            try {
                FileWriter myWriter = new FileWriter("Statistici/"+jucator.toString()+".csv",false);
                myWriter.write("\"Ziua\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+Formule.weekDay(WorkloadManagement.data.minusDays(i))+"\",");
                myWriter.write("\"\"\r\n");

                //Dimineata
                myWriter.write("\"Dimineata\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getRPEVectorE(i,0)+"\",");
                myWriter.write("\"\"\r\n");

                //Seara
                myWriter.write("\"Seara\",");
                for(int i=6; i>=0; i--)
                    myWriter.write("\""+jucator.getStatistici().getRPEVectorE(i,1)+"\",");
                myWriter.write("\"\"\r\n");

                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void raspunsuri(List<Jucator> jucatori){
        for(Jucator jucator : jucatori){
            //Jucator jucator=jucatori.get(0);
            try {
                File file = new File("Statistici/"+jucator.toString()+".csv");
                if (file.createNewFile()) 
                    System.out.println("File created: " + file.getName());
                else 
                    System.out.println("File already exists.");   
                }catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                try {
                    FileWriter myWriter = new FileWriter("Statistici/"+jucator.toString()+".csv",true);
                    myWriter.write("\"Data\",\"Nume\",\"Numar\",\"Dificultate\",\"Durata\"\r\n");
                    for(Antrenament antrenament : jucator.getAntrenamente()){
                        int perioada=(int) ChronoUnit.DAYS.between(antrenament.getData(),Formule.azi);
                        int perioada2=(int) ChronoUnit.DAYS.between(antrenament.getData(), Formule.aziMaine);
                        if(perioada<Statistici.expDays && perioada>=0 && perioada2>=0)
                            myWriter.write("\""+antrenament.getData().getDayOfMonth()+"-"+antrenament.getData().getMonthValue()+"-"+antrenament.getData().getYear()+"\",\""
                            +antrenament.getNume()+"\",\""
                            +antrenament.getNumar()+"\",\""
                            +antrenament.getDificultate()+"\",\""
                            +antrenament.getDurata()+"\"\r\n");
                    }
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
    }
}
