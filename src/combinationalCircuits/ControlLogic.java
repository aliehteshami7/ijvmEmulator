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
                //todo
            }
        }else {
            switch (time){
                case 0:
                    marLoad = true;
                    busSelect = 4;
                    aluControl = 1;
                    read = true;
                    break;
                case 1:
                    if (!Computer.getInstance().getMemory().isReady()){
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
