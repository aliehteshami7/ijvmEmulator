package registers;

import parts.Computer;

public class MAR extends Register {
    private boolean load;
    private int data;

    @Override
    public String toString() {
        return "MAR: " + data + "\n" + "mar_load" + load + "\n";
    }

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
        System.out.println(toString());
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
