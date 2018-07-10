package registers;

public class LV extends Register {
    private int data = 512;

    public int getData() {
        return data;
    }

    @Override
    public void applyNextClockValue() {
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
    }

    @Override
    public void reset() {
        data = 512;
    }

    @Override
    public String toString() {
        return "LV: " + data + "\n";
    }
}
