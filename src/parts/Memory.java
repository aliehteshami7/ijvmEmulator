package parts;

import interfaces.Clockable;
import interfaces.Resetable;

public class Memory implements Clockable, Resetable {
    private int out;
    private boolean ready;

    private byte[] data = new byte[1024];
    private boolean firstClk;
    private int counter;

    private int address;
    private int dataIn;
    private boolean rwn;
    private boolean start;

    public int getOut() {
        return out;
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public void reset() {
        data = new byte[1024];
        address = 0;
        ready = true;
        start = false;
        rwn = false;
    }

    @Override
    public void applyNextClockValue() {
        if (!firstClk) {
            if (counter == 0) {
                if (rwn)
                    out = data[address];
                else
                    writeData(address, dataIn);
                ready = true;
            } else {
                counter--;
            }
        } else {
            firstClk = false;
            ready = false;
        }
    }

    private void writeData(int address, int dataIn) {
        //todo
    }

    @Override
    public void calculateNextClockValue() {
        if (!start) {
            start = Computer.getInstance().getCu().getRead().isData() ||
                    Computer.getInstance().getCu().getWrite().isData();
            if (start) {
                firstClk = true;
                rwn = Computer.getInstance().getCu().getRead().isData();
                address = Computer.getInstance().getDp().getMar().getData();
                counter = address % 4;
                dataIn = Computer.getInstance().getDp().getMdr().getData();
            }
        }
    }
}
