package registers;

import parts.Computer;

public class MBR extends Register {
    private boolean load;
    private int opcode;
    private int opra;
    private int oprb;
    private int oprab;

    private int dataShadow;

    public boolean isLoad() {
        return load;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getOpra() {
        return opra;
    }

    public int getOprb() {
        return oprb;
    }

    public int getOprab() {
        return oprab;
    }

    @Override
    public void reset() {
        load = false;
        opcode = 0;
        opra = 0;
        oprb = 0;
        oprab = 0;
    }

    @Override
    public void applyNextClockValue() {
        if (load){
            oprb = dataShadow % 256;
            oprab = dataShadow % 65536;
            dataShadow /= 256;
            opra = dataShadow % 256;
            dataShadow /= 256;
            opcode = dataShadow % 256;
        }
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMbrLoad();
        if (load)
            dataShadow = Computer.getInstance().getMemory().getOut() / 256;
    }
}
