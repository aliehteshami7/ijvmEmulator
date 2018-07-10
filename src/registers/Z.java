package registers;

public class Z extends Register {
    private boolean data;
    private boolean q;

    public void setQ(boolean q) {
        this.q = q;
    }

    public boolean isData() {
        return data;
    }

    public boolean isQ() {
        return q;
    }

    @Override
    public String toString() {
        return "Z: " + data + "\n";
    }

    @Override
    public void applyNextClockValue() {
        data = q;
        System.out.println(toString());
    }

    @Override
    public void calculateNextClockValue() {
    }

    @Override
    public void reset() {
        data = false;
        q = false;
    }
}
