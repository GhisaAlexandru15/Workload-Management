import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class FileIn {
    //date salvate - pot fi moficate
    static DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("yyyy/MM/dd h:mm:ss a z");
    static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static void readDate(List<Antrenament> antrenamente) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("Date/Date.csv"))) {
            String line, parts[];
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                parts=line.split(",");
                if(parts[0].contains("Adaugare date: "));
                else{
                    parts[0]=parts[0].replace("p.m.", "PM");
                    parts[0]=parts[0].replace("a.m.", "AM");
                    for(int i=0; i<5; i++)
                        parts[i]=parts[i].replace("\"", "");
                    WorkloadManagement.ultimaData=LocalDateTime.parse(parts[0], formatterD);
                    WorkloadManagement.ultimaDurata=Integer.parseInt(parts[4].replace(" ",""));
                    antrenamente.add(new Antrenament(LocalDateTime.parse(parts[0], formatterD), parts[1], 
                    Integer.parseInt(parts[2].replace(" ","")), Integer.parseInt(parts[3].replace(" ","")), Integer.parseInt(parts[4].replace(" ",""))));
                }
            }
        }    
    }

    //date Google Form
    public static void readForms(List<Antrenament> antrenamente) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("Date/Workload Management.csv"))) {
            String line="...", parts[]=new String[]{"2024/01/19 11:02:05 AM EET","","",""};
            int durata=WorkloadManagement.ultimaDurata;
            br.readLine(); //cap tabel
            //neglijare date deja salvate
            while(ChronoUnit.SECONDS.between(WorkloadManagement.ultimaData,LocalDateTime.parse(parts[0], formatterF))<0 && 
                                                                                        (line = br.readLine()) != null){
                parts=line.split(",");
                parts[0]=parts[0].replace("p.m.", "PM");
                parts[0]=parts[0].replace("a.m.", "AM");
                parts[0]=parts[0].replace("\"", "");
            }
            //citire date noi
            while ((line = br.readLine()) != null) {
                parts=line.split(",");
                if(parts[1].contains("Durat")||parts[1].contains("durat")||parts[1].contains("urată"))
                    durata=(int)Float.parseFloat(parts[2].replace(" ", "").replace("\"", ""));
                else{
                    parts[0]=parts[0].replace("p.m.", "PM");
                    parts[0]=parts[0].replace("a.m.", "AM");
                    for(int i=0; i<4; i++)
                        parts[i]=parts[i].replace("\"", "");
                    antrenamente.add(new Antrenament(LocalDateTime.parse(parts[0], formatterF), parts[1], 
                    Integer.parseInt(parts[2].replace(" ","")), Integer.parseInt(parts[3].replace(" ","")), durata));
                }
            }
        }    
    }

    public static void readJucatori(List<Jucator> jucatori, HashMap<Integer,Integer> jucHashMap) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("jucatori.txt"))) {      
            int index=0;
            String line, parts[];
            while ((line = br.readLine()) != null) {
                parts=line.split(",");
                jucatori.add(new Jucator(parts[1], Integer.parseInt(parts[0].replace(" ", ""))));
                jucHashMap.put(Integer.parseInt(parts[0].replace(" ", "")),index++);
            }
        }
    }   
}
