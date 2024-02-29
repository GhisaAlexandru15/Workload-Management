import java.util.List;

public class Test {
    public static void antrenamente(List<Antrenament> antrenamente){
        for(Antrenament antrenament : antrenamente)
            System.out.println(antrenament.toString());
    }
    public static void jucatori(List<Jucator> jucatori){
        for(Jucator jucator : jucatori){
            System.out.println(jucator.toString());
            for(Antrenament antrenament : jucator.getAntrenamente())
                System.out.println(antrenament.toString());
        }
    }

    public static float testNume(String test, String nume){
        int similaritate=0;
        test.replace("ă", "a");
        test.replace("â", "a");
        test.replace("î", "i");
        test.replace("ș", "s");
        test.replace("ț", "t");
        nume.replace("ă", "a");
        nume.replace("â", "a");
        nume.replace("î", "i");
        nume.replace("ș", "s");
        nume.replace("ț", "t");
        test=test.toLowerCase();
        nume=nume.toLowerCase();
        for(char c : test.toCharArray()){
            if(nume.indexOf(c)>=0)
                similaritate+=1;
        }
        return (float)similaritate/nume.length();

    }

}
