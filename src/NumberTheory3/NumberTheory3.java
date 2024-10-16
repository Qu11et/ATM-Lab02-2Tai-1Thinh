package NumberTheory3;

import java.math.BigInteger;
import java.util.Scanner;

public class NumberTheory3 {
    public static void main() {
        Scanner scan = new Scanner(System.in);

        // Input base
        System.out.print("Enter the base (a): ");
        BigInteger a = new BigInteger(scan.nextLine());

        // Input exponent
        System.out.print("Enter the exponent (x): ");
        BigInteger x = new BigInteger(scan.nextLine());

        // Input modulus
        System.out.print("Enter the modulus (p): ");
        BigInteger p = new BigInteger(scan.nextLine());

        // Calculate a^x mod p
        BigInteger result = a.modPow(x, p);

        // Display the result
        System.out.println(a + "^" + x + " mod " + p + " = " + result);

        scan.close();
    }
}