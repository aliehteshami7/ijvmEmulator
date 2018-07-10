package registers;

public class LV extends Register {
    private int data = 508;

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
        data = 508;
    }

    @Override
    public String toString() {
        return "LV: " + data + "\n";
    }
}
