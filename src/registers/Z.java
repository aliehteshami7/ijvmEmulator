package registers;

import parts.Computer;

public class Z extends Register {
    private boolean data;
    private boolean q;

    public boolean isData() {
        return data;
    }

    public boolean isQ() {
        return q;
    }

    @Override
    public String toString() {
        return "Z: " + data + "\n";
    }

    @Override
    public void applyNextClockValue() {
        data = q;
    }

    @Override
    public void calculateNextClockValue() {
        q = Computer.getInstance().getDp().getAlu().isZ();
        System.out.println(toString());
    }

    @Override
    public void reset() {
        data = false;
        q = false;
    }
}
