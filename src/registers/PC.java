package registers;

public class PC extends Register {
    private boolean load;
    private boolean[] inc = new boolean[3];
    private int data;
}
