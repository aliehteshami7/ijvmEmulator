package registers;

import parts.Computer;

public class MDR extends Register {
    private boolean load;
    private boolean read;
    private boolean write;
    private int data;

    private int dataMemShadow;

    public boolean isLoad() {
        return load;
    }

    public boolean isRead() {
        return read;
    }

    public boolean isWrite() {
        return write;
    }

    public int getData() {
        return data;
    }

    @Override
    public void reset() {
        load = false;
        read = false;
        write = false;
        data = 0;
        dataMemShadow = 0;
    }

    @Override
    public void applyNextClockValue() {
        if (load)
            data = Computer.getInstance().getDp().getAlu().getOut();
        if (read)
            data = dataMemShadow;
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMdrLoad();
        read = Computer.getInstance().getCu().getControlLogic().isRead();
        write = Computer.getInstance().getCu().getControlLogic().isWrite();
        if (read)
            dataMemShadow = Computer.getInstance().getMemory().getOut();
    }
}
