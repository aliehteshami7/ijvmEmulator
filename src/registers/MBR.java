package registers;

import parts.Computer;

import java.nio.ByteBuffer;

public class MBR extends Register {
    private boolean load;
    private byte opcode;
    private int opra;
    private int oprb;
    private int oprab;

    @Override
    public String toString() {
        return "MBR: (opcode:" + opcode + ", opra:" + opra + ", oprb:" + oprb + ", oprab:" + oprab + ")\n"
                + "mbr_load: " + load + "\n";
    }

    private int dataShadow;

    public boolean isLoad() {
        return load;
    }

    public byte getOpcode() {
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
        if (load) {
            byte[] array = ByteBuffer.allocate(4).putInt(dataShadow).array();
            oprb = array[2];
            opra = array[1];
            opcode = array[0];
            byte[] array2 = new byte[4];
            array2[2] = array[1];
            array2[3] = array[2];
            oprab = ByteBuffer.wrap(array2).getInt();
        }
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
        load = Computer.getInstance().getCu().getControlLogic().isMbrLoad();
        if (load)
            dataShadow = Computer.getInstance().getMemory().getOut();
    }
}
