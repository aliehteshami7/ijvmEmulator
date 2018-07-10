package parts;

import interfaces.Clockable;
import interfaces.Resetable;

import java.nio.ByteBuffer;

public class Memory implements Clockable, Resetable {
    private int out;
    private boolean ready = true;

    private byte[] data = new byte[1024];
    private int counter;

    private int address;
    private int dataIn;
    private boolean rwn;
    private boolean start;

    @Override
    public String toString() {
        return "Memory:\n" + "mem_address: " + address + "\nmem_data_in: " + dataIn + "\nmem_rwn: " + rwn
                + "\nmem_start: " + start + "\nmem_out: " + out + "\nmem_ready: " + ready + "\n"
                + "\nmem_counter: " + counter + "\n";
    }

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
        if (!ready) {
            if (start) {
                if (counter == 0) {
                    if (rwn)
                        out = readData(address);
                    else
                        writeData(address, dataIn);
                    ready = true;
                    start = false;
                } else {
                    counter--;
                }
            }
        } else {
            if (start)
                ready = false;
        }
        System.out.println(toString());
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private int readData(int address) {
        byte[] bytes = new byte[4];
        bytes[0] = data[address];
        bytes[1] = data[address + 1];
        bytes[2] = data[address + 2];
        bytes[3] = data[address + 3];
        return ByteBuffer.wrap(bytes).getInt();
    }

    private void writeData(int address, int dataIn) {
        byte[] array = ByteBuffer.allocate(4).putInt(dataIn).array();
        data[address] = array[0];
        data[address + 1] = array[1];
        data[address + 2] = array[2];
        data[address + 3] = array[3];
    }

    @Override
    public void calculateNextClockValue() {
        if (ready) {
            start = Computer.getInstance().getCu().getRead().isData() ||
                    Computer.getInstance().getCu().getWrite().isData();
            if (start) {
                rwn = Computer.getInstance().getCu().getRead().isData();
                address = Computer.getInstance().getDp().getMar().getData();
                counter = address % 4;
                dataIn = Computer.getInstance().getDp().getMdr().getData();
            }
        }
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(i + ": " + data[i]);
        }
    }
}
