package combinationalCircuits;

import parts.Computer;

public class ALU extends CombinationalCircuit {
    private int out;
    private boolean z;
    private boolean n;

    public boolean isZ() {
        return z;
    }

    public boolean isN() {
        return n;
    }

    public int getOut() {
        return out;
    }

    @Override
    public void calculateOutput() {
        int in1 = Computer.getInstance().getDp().getH().getData();
        int in2 = Computer.getInstance().getDp().getBus().getOut();

        // 0:A, 1:B, 2:A+B, 3:B-A

        switch (Computer.getInstance().getCu().getControlLogic().getAluControl()) {
            case 0:
                out = in1;
                break;
            case 1:
                out = in2;
                break;
            case 2:
                out = in1 + in2;
                break;
            case 3:
                out = in2 - in1;
                break;
            default:
                System.out.println("ALU Control Value Out Of Range");
        }

        if (Computer.getInstance().getCu().getControlLogic().isAluShift())
            out *= 4;
        if (out == 0)
            z = true;
        if (out < 0)
            n = true;
        System.out.println("alu_control: " + Computer.getInstance().getCu().getControlLogic().getAluControl()
                + "\nalu_shift: " + Computer.getInstance().getCu().getControlLogic().isAluShift() + "\n");
    }
}
