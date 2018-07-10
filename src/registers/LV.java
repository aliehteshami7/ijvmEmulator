package registers;

public class LV extends Register {
    private int data = 512;

    public int getData() {
        return data;
    }

    @Override
    public void applyNextClockValue() {
    }

    @Override
    public void calculateNextClockValue() {
    }

    @Override
    public void reset() {
        data = 512;
    }
}
