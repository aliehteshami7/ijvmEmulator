package registers;

import parts.Computer;

public class Write extends Register {
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
        return "Write: " + data + "\n";
    }

    @Override
    public void applyNextClockValue() {
        data = q;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        q = Computer.getInstance().getCu().getControlLogic().isWrite();
    }

    @Override
    public void reset() {
        data = false;
        q = false;
    }
}
