import NumberTheory.NumberTheory;
import NumberTheory2.NumberTheory2;
import NumberTheory3.NumberTheory3;

import SimpleRSA.SimpleRSA;
import java.util.Scanner;

public class Menu {

    private static Scanner sc;

    public static void Init() {
        sc = new Scanner(System.in);
    }

    public static void PrintMENU() {

        System.out.println("==============================================================================");
        System.out.println("\t\tLAB2");
        System.out.println("0\tExit");
        System.out.println("1\tC1.1.1. Generate a random Prime Number");
        System.out.println(" \t        Determine the 10 largest prime under 10 first Mersenne prime numbers");
        System.out.println(" \t        Check if an abitrary integer is less than 2^89 or not");
        System.out.println("2\tC1.1.2. Determine the gcd of 2 abitrary large integer ");
        System.out.println("3\tC1.1.3. Compute large exponents");
        System.out.println("4\tC2.2.   Simple RSA");
        System.out.println("==============================================================================");
        System.out.println("Select your wanted task (Enter the number): ");
    }

    /**
     * 
     */
    public static void Start() {
        String choice;
        do{
            PrintMENU();
            choice = sc.nextLine(); // Read input as a string

            switch (choice) {
                case "0":
                    System.out.println("Exiting...");
                    return;
                case "1":
                    NumberTheory.main();
                    break;
                case "2":
                    NumberTheory2.main();
                    break;
                case "3":
                    NumberTheory3.main();
                    break;
                case "4":
                    try {
                        SimpleRSA.main(); // Call the SimpleRSA main method
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                        e.printStackTrace(); // Optional: Print the stack trace for debugging
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        } while(sc.hasNextLine());

    }

    public static void main(String[] args) {
        Init();

        Start();

        sc.close();
    }
}
