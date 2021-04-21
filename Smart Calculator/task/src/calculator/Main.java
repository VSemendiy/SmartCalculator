package calculator;

import java.util.Scanner;

import static calculator.Calculator.calculate;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {

        String line;

        do {
            line = SC.nextLine();
            switch (line) {
                case "/help":
                    System.out.println("This program supports addition, subtraction, multiplication, division, powers, and calculations in parentheses");
                    break;
                case "/exit":
                    System.out.println("Bye!");
                    break;
                case "":
                    break;
                default:
                    if (line.startsWith("/")) System.out.println("Unknown command");
                    else {
                        String sum=calculate(line);
                        if(sum!=null) System.out.println(sum);
                    }
                    break;
            }
        } while (!line.equals("/exit"));
    }

}