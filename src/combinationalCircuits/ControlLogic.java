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
        if (time > 3) {
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
                    addXsubn(time, true);
                    break;
                case (byte) 0x99:
                    ifeqXifltn(time, true);
                    break;
                case (byte) 0x9b:
                    ifeqXifltn(time, false);
                    break;
                case (byte) 0x9f:
                    if_icmpeq(time);
                    break;
                case (byte) 0x84:
                    iinc(time);
                    break;
                case 0x15:
                    iload(time);
                    break;
                case 0x36:
                    istore(time);
                    break;
                case 0x64:
                    addXsubn(time, false);
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
                    break;
                case 1:
                    read = true;
                    break;
                case 2:
                    if (!Computer.getInstance().getMemory().isReady()) {
                        scHold = true;
                        read = true;
                    }
                    break;
                case 3:
                    mbrLoad = true;
                    break;
            }
        }
    }

    private void ifeqXifltn(int time, boolean ifeq) {
        switch (time) {
            case 4:
                pcInc = 3;
                spPop = true;
                break;
            case 5:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()) {
                    scHold = true;
                    read = true;
                }
                break;
            case 8:
                hLoad = true;
                aluControl = 1;
                busSelect = 0;
                break;
            case 9:
                boolean z = Computer.getInstance().getCu().getZ().isData();
                boolean n = Computer.getInstance().getCu().getN().isData();
                if ((ifeq & z) || (!ifeq & n)) {
                    hLoad = true;
                    aluControl = 1;
                    busSelect = 3;
                } else {
                    scClear = true;
                }
                break;
            case 10:
                scClear = true;
                pcLoad = true;
                aluControl = 2;
                busSelect = 4;
                break;
        }
    }

    private void addXsubn(int time, boolean add) {
        switch (time) {
            case 4:
                spPop = true;
                pcInc = 1;
                break;
            case 5:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                spPop = true;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()) {
                    read = true;
                    scHold = true;
                }
                break;
            case 8:
                hLoad = true;
                aluControl = 1;
                busSelect = 0;
                break;
            case 9:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                break;
            case 10:
                read = true;
                break;
            case 11:
                if (!Computer.getInstance().getMemory().isReady()) {
                    read = true;
                    scHold = true;
                }
                break;
            case 12:
                if (add)
                    aluControl = 2;
                else
                    aluControl = 3;
                mdrLoad = true;
                busSelect = 0;
                break;
            case 13:
                spPush = true;
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                break;
            case 14:
                write = true;
                break;
            case 15:
                if (!Computer.getInstance().getMemory().isReady()) {
                    write = true;
                    scHold = true;
                }
                break;
            case 16:
                scClear = true;
        }
    }

    private void bipush(int time) {
        switch (time) {
            case 4:
                marLoad = true;
                busSelect = 4;
                aluControl = 1;
                spPush = true;
                pcInc = 2;
                break;
            case 5:
                mdrLoad = true;
                aluControl = 1;
                busSelect = 1;
                break;
            case 6:
                write = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()) {
                    scHold = true;
                    write = true;
                }
                break;
            case 8:
                scClear = true;
                break;

        }
    }

    private void if_icmpeq(int time) {
        switch (time){
            case 4:
                spPop = true;
                pcInc = 3;
                break;
            case 5:
                marLoad = true;
                busSelect = 5;
                aluControl = 1;
                spPop = true;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    read = true;
                }
                break;
            case 8:
                hLoad = true;
                aluControl = 1;
                busSelect = 0;
                break;
            case 9:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                break;
            case 10:
                read = true;
                break;
            case 11:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    read = true;
                }
                break;
            case 12:
                hLoad = true;
                aluControl = 3;
                busSelect = 0;
                break;
            case 13:
                if (!Computer.getInstance().getCu().getZ().isData()){
                    scClear = true;
                }
                else {
                    hLoad = true;
                    aluControl = 1;
                    busSelect = 3;
                }
                break;
            case 14:
                pcLoad = true;
                aluControl = 2;
                busSelect = 4;
                scClear = true;
                break;
        }
    }

    private void iinc(int time) {
        switch (time){
            case 4:
                hLoad = true;
                aluControl = 1;
                busSelect = 1;
                aluShift = true;
                pcInc = 3;
                break;
            case 5:
                marLoad = true;
                aluControl = 2;
                busSelect = 6;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    read = true;
                }
                break;
            case 8:
                hLoad = true;
                aluControl = 1;
                busSelect = 2;
                break;
            case 9:
                mdrLoad = true;
                aluControl = 2;
                busSelect = 0;
                break;
            case 10:
                write = true;
                break;
            case 11:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    write = true;
                }
                break;
            case 12:
                scClear = true;
                break;

        }
    }

    private void istore(int time) {
        switch (time){
            case 4:
                spPop = true;
                pcInc = 2;
                break;
            case 5:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    read = true;
                }
                break;
            case 8:
                hLoad = true;
                aluControl = 1;
                busSelect = 1;
                aluShift = true;
                break;
            case 9:
                marLoad = true;
                aluControl = 2;
                busSelect = 6;
                break;
            case 10:
                write = true;
                break;
            case 11:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    write = true;
                }
                break;
            case 12:
                scClear = true;
                break;
        }
    }

    private void iload(int time) {
        switch (time){
            case 4:
                hLoad = true;
                aluControl = 1;
                busSelect = 1;
                aluShift = true;
                pcInc = 2;
                break;
            case 5:
                marLoad = true;
                aluControl = 2;
                busSelect = 6;
                break;
            case 6:
                read = true;
                break;
            case 7:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    read = true;
                }
                break;
            case 8:
                marLoad = true;
                aluControl = 1;
                busSelect = 5;
                spPush = true;
                break;
            case 9:
                write = true;
                break;
            case 10:
                if (!Computer.getInstance().getMemory().isReady()){
                    scHold = true;
                    write = true;
                }
                break;
            case 11:
                scClear = true;
                break;
        }
    }

    private void goTo(int time) {
        switch (time) {
            case 4:
                hLoad = true;
                aluControl = 1;
                busSelect = 3;
                pcInc = 3;
                break;
            case 5:
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
