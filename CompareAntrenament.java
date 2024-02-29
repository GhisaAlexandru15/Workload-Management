import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class CompareAntrenament implements Comparator<Antrenament> {
    @Override
    public int compare(Antrenament antrenament1,Antrenament antrenament2) {
        return (int) ChronoUnit.DAYS.between(antrenament2.getData(), antrenament1.getData());
    }

    @Override
    public Comparator<Antrenament> reversed() {
        return Comparator.super.reversed();
    }
}