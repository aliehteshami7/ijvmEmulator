import interfaces.Clockable;
import interfaces.Resetable;

public class Computer implements Clockable, Resetable {
    private static Computer ourINstance = new Computer();
    private DP dp;
    private CU cu;
    private Memory memory;

    public static void main(String[] args) {

    }

    private static Computer getInstance() {
        return ourINstance;
    }

    private Computer() {
        dp = new DP();
        cu = new CU();
        memory = new Memory();
    }
}
