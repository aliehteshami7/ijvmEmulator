import parts.Computer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("What do you want?" +
                "1. Assemble and load" +
                "2. reset" +
                "3. run one clock" +
                "4. run until HLT" +
                "Please enter the number!");
        Scanner scanner = new Scanner(System.in);
        int inp = scanner.nextInt();

        Computer computer = Computer.getInstance();



        switch (inp){
            case 1:
                byte[] mem = Assembler.assemble();
                computer.getMemory().setData(mem);
                break;
            case 2:
                computer.reset();
                break;
            case 3:
                computer.calculateNextClockValue();
                computer.applyNextClockValue();
                break;
            case 4:
                while (!computer.isHlt()){
                    computer.calculateNextClockValue();
                    computer.applyNextClockValue();
                }
                break;
        }



    }
}
