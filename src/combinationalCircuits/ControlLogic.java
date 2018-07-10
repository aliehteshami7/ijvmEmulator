package combinationalCircuits;

import parts.Computer;

public class ControlLogic extends CombinationalCircuit {
    private boolean read;
    private boolean write;
    private boolean scClear;
    private boolean scHold;
    private boolean marLoad;
    private boolean mdrLoad;
    private boolean mbrLoad;
    private int busSelect;
    private boolean pcLoad;
    private int pcInc;
    private boolean spPush;
    private boolean spPop;
    private boolean hLoad;
    private int aluControl;
    private boolean aluShift;

    public boolean isRead() {
        return read;
    }

    public boolean isScHold() {
        return scHold;
    }

    public boolean isWrite() {
        return write;
    }

    public boolean isScClear() {
        return scClear;
    }

    public boolean isMarLoad() {
        return marLoad;
    }

    public boolean isMdrLoad() {
        return mdrLoad;
    }

    public boolean isMbrLoad() {
        return mbrLoad;
    }

    public int getBusSelect() {
        return busSelect;
    }

    public boolean isPcLoad() {
        return pcLoad;
    }

    public int getPcInc() {
        return pcInc;
    }

    public boolean isSpPush() {
        return spPush;
    }

    public boolean isSpPop() {
        return spPop;
    }

    public boolean ishLoad() {
        return hLoad;
    }

    public int getAluControl() {
        return aluControl;
    }

    public boolean isAluShift() {
        return aluShift;
    }

    @Override
    public void calculateOutput() {
        reset();
        byte opcode = Computer.getInstance().getDp().getMbr().getOpcode();
        int time = Computer.getInstance().getCu().getSc().getData();
        if (time > 2) {
            switch (opcode) {
                case 0x00:
                    pcInc = 1;
                    scClear = true;
                    break;
                case 0x01:
                    pcInc = 1;
                    scClear = true;
                    Computer.getInstance().setHlt(true);
                    break;
                case 0x10:
                    bipush(time);
                    break;
                case (byte) 0xa7:
                    goTo(time);
                    break;
                case 0x60:

                    break;
                case (byte) 0x99:

                    break;
                case (byte) 0x9b:

                    break;
                case (byte) 0x9f:

                    break;
                case (byte) 0x84:

                    break;
                case 0x15:

                    break;
                case 0x36:

                    break;
                case 0x64:

                    break;
                default:
                    System.out.println("Opcode Unavailable");

            }
        } else {
            switch (time) {
                case 0:
                    marLoad = true;
                    busSelect = 4;
                    aluControl = 1;
                    read = true;
                    break;
                case 1:
                    if (!Computer.getInstance().getMemory().isReady()) {
                        scHold = true;
                        read = true;
                    }
                    break;
                case 2:
                    mbrLoad = true;
                    break;
            }
        }
    }

    private void goTo(int time) {
        switch (time) {
            case 3:
                hLoad = true;
                aluControl = 0;
                busSelect = 3;
                pcInc = 3;
                break;
            case 4:
                scClear = true;
                aluControl = 2;
                busSelect = 4;
                pcLoad = true;
        }
    }

    private void reset() {
        read = false;
        write = false;
        scClear = false;
        scHold = false;
        marLoad = false;
        mdrLoad = false;
        mbrLoad = false;
        busSelect = 0;
        pcLoad = false;
        pcInc = 0;
        spPush = false;
        spPop = false;
        hLoad = false;
        aluControl = 0;
        aluShift = false;
    }
}
