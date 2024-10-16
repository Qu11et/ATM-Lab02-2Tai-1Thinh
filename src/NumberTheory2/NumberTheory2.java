package NumberTheory2;

import java.math.BigInteger;
import java.util.Scanner;

public class NumberTheory2
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Input first large integer
        System.out.print("Enter the first large integer: ");
        BigInteger num1 = new BigInteger(scan.nextLine());

        // Input second large integer
        System.out.print("Enter the second large integer: ");
        BigInteger num2 = new BigInteger(scan.nextLine());

        // Calculate GCD
        BigInteger gcd = num1.gcd(num2);

        // Display the result
        System.out.println("The GCD of " + num1 + " and " + num2 + " is: " + gcd);

        scan.close();
    }
}