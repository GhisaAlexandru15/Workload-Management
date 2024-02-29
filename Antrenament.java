import java.time.LocalDateTime;

public class Antrenament {
    private LocalDateTime data;
    private String nume;
    private int numar;
    private int dificultate;
    private int durata;

    // Constructor
    public Antrenament(LocalDateTime data, String nume, int numar, int dificultate, int durata) {
        this.data = data;
        this.nume = nume;
        this.numar = numar;
        this.dificultate = dificultate;
        this.durata = durata;
    }

    // Getters
    public LocalDateTime getData() {
        return data;
    }

    public String getNume() {
        return nume;
    }

    public int getNumar() {
        return numar;
    }

    public int getDificultate() {
        return dificultate;
    }

    public int getDurata() {
        return durata;
    }

    // Setters
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public void setDificultate(int dificultate) {
        this.dificultate = dificultate;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
    @Override 
    public String toString(){
        String[] parts=nume.split(" ");
        nume="";
        for(String part : parts){
            nume=nume+part.charAt(0);
        }
        return "\""+data+"\",\""+nume+"\",\""+numar+"\",\""+dificultate+"\",\""+durata+"\"\r\n"; 
    }
}
