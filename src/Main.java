import parts.Computer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("What do you want?\n" +
                    "1. Assemble and load\n" +
                    "2. reset\n" +
                    "3. run one clock\n" +
                    "4. run until HLT\n" +
                    "5. print memory\n" +
                    "6. exit\n" +
                    "Please enter the number!\n");
            Scanner scanner = new Scanner(System.in);
            int inp = scanner.nextInt();

            Computer computer = Computer.getInstance();


            switch (inp) {
                case 1:
                    byte[] mem = Assembler.assemble();
                    computer.getMemory().setData(mem);
                    break;
                case 2:
                    computer.reset();
                    break;
                case 3:
                    if (!computer.isHlt()) {
                        computer.calculateNextClockValue();
                        computer.applyNextClockValue();
                    } else
                        System.out.println("Computer Halted");
                    break;
                case 4:
                    while (!computer.isHlt()) {
                        computer.calculateNextClockValue();
                        computer.applyNextClockValue();
                        System.out.println("\n*************\n");
                    }
                    System.out.println("Computer Halted");
                    break;
                case 5:
                    computer.getMemory().print();
                    break;
                case 6:
                    return;
            }
        }
    }
}
