package registers;

import parts.Computer;

import java.nio.ByteBuffer;

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
            byte[] array = ByteBuffer.allocate(4).putInt(dataShadow).array();
            oprb = array[2];
            opra = array[1];
            opcode = array[0];
            byte[] array2 = new byte[2];
            array2[0] = array[1];
            array2[1] = array[2];
            oprab = ByteBuffer.wrap(array2).getInt();
        }
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMbrLoad();
        if (load)
            dataShadow = Computer.getInstance().getMemory().getOut();
    }
}
