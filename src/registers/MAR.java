package registers;

import parts.Computer;

public class MAR extends Register {
    private boolean load;
    private int data;

    public boolean isLoad() {
        return load;
    }

    public int getData() {
        return data;
    }

    @Override
    public void applyNextClockValue() {
        if (load)
            data = Computer.getInstance().getDp().getAlu().getOut();
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMarLoad();
    }

    @Override
    public void reset() {
        load = false;
        data = 0;
    }
}
