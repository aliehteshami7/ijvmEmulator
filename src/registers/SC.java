package registers;

import parts.Computer;

public class SC extends Register {
    private int data;
    private boolean hold;
    private boolean clear;

    @Override
    public String toString() {
        return "SC: " + data + "\nsc_hold: " + hold + "\nsc_clear: " + clear + "\n";
    }

    public int getData() {
        return data;
    }

    public boolean isHold() {
        return hold;
    }

    public boolean isClear() {
        return clear;
    }

    @Override
    public void applyNextClockValue() {
        if (clear)
            data = 0;
        else if (!hold)
            data++;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        clear = Computer.getInstance().getCu().getControlLogic().isScClear();
        hold = Computer.getInstance().getCu().getControlLogic().isScHold();
    }

    @Override
    public void reset() {
        data = 0;
        hold = false;
        clear = false;
    }
}
