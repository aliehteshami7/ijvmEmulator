package combinationalCircuits;

public class ALU extends CombinationalCircuit {
    private int out;
    private boolean z;
    private boolean n;

    public boolean isZ() {
        return z;
    }

    public boolean isN() {
        return n;
    }

    public int getOut() {
        return out;
    }
}
