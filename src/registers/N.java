package registers;

import parts.Computer;

public class N extends Register {
    private boolean data;
    private boolean q;

    @Override
    public String toString() {
        return "N: " + data + "\n";
    }

    public boolean isData() {
        return data;
    }

    public boolean isQ() {
        return q;
    }

    @Override
    public void applyNextClockValue() {
        data = q;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        q = Computer.getInstance().getDp().getAlu().isN();
    }

    @Override
    public void reset() {
        data = false;
        q = false;
    }
}
