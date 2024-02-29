import java.util.ArrayList;
import java.util.List;

public class Jucator {
    private String nume;
    private int numar;
    private List<Antrenament> antrenamente=new ArrayList<Antrenament>();
    private Statistici statistici=new Statistici();

    // Constructor
    public Jucator(String nume, int numar) {
        this.nume = nume;
        this.numar = numar;
    }

    // Getters
    public String getNume() {
        return nume;
    }

    public int getNumar() {
        return numar;
    }

    public List<Antrenament> getAntrenamente() {
        return antrenamente;
    }

    public Statistici getStatistici() {
        return statistici;
    }

    // Setters
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public void setAntrenamente(List<Antrenament> antrenamente) {
        this.antrenamente = antrenamente;
    }

    public void setStatistici(Statistici statistici) {
        this.statistici = statistici;
    }

    @Override

    public String toString(){
        return nume;
    }
}
