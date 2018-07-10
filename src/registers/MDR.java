package registers;

import parts.Computer;

public class MDR extends Register {
    private boolean load;
    private boolean read;
    private int data;

    private int dataMemShadow;

    @Override
    public String toString() {
        return "MDR: " + data + "\nmdr_load" + load + "\nmdr_read: " + read + "\n";
    }

    public boolean isLoad() {
        return load;
    }

    public boolean isRead() {
        return read;
    }


    public int getData() {
        return data;
    }

    @Override
    public void reset() {
        load = false;
        read = false;
        data = 0;
        dataMemShadow = 0;
    }

    @Override
    public void applyNextClockValue() {
        if (load)
            data = Computer.getInstance().getDp().getAlu().getOut();
        if (read)
            data = dataMemShadow;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMdrLoad();
        read = Computer.getInstance().getCu().getControlLogic().isRead();
        if (read)
            dataMemShadow = Computer.getInstance().getMemory().getOut();
    }
}
