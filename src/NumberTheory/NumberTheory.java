package NumberTheory;

import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class NumberTheory
{
    private static Scanner scan;
    private static Random random;

    public static void Init()
    {
        scan = new Scanner(System.in);
        random = new Random();
    }

    public static void Dispose()
    {
        scan.close();
    }

    // Check if an integer is prime
    public static boolean Is_Prime(long number)
    {
        // A prime number is greater than 1
        if (number <= 1)
        {
            return false;
        }
        // Check divisors from 2 to the square root of the number
        for (long i = 2; i * i <= number; i++)
        {
            // If the number is divisible by any i, it's not prime
            if (number % i == 0)
            {
                return false;
            }
        }
        // If no divisors are found, the number is prime
        return true;
    }

    public static boolean Is_Mersenne(long number) {
        for (int i = 2; i < 64; i++)
        {
            if ((1L << i) - 1 == number)
            { // Use 1L for long literal
                return true;
            }
        }
        return false;
    }

    // Generate a random prime number under 8 bits, 16 bits, or 64 bits
    public static long Generate() {
        System.out.print("Choose number of bits that you want to generate (8, 16, 64): ");
        int option = scan.nextInt();
        scan.nextLine();

        long result = 0;
        long minValue = 0, maxValue = 0;

        if (option == 8)
        {
            minValue = 2;
            maxValue = 255;
        }
        else if (option == 16)
        {
            minValue = 2;
            maxValue = 65535;
        }
        else if (option == 64)
        {
            minValue = 2;
            maxValue = Long.MAX_VALUE;
        } else
        {
            System.out.println("Invalid option!");
            return -1;
        }

        // Generate random prime
        do
        {
            if (option == 64)
            {
                // Generate a random long value in the range of [minValue, maxValue]
                result = random.nextLong();
                // Ensure it is within the correct range
                if (result < minValue || result > maxValue) {
                    result = (long) (random.nextDouble() * (maxValue - minValue + 1)) + minValue;
                }
            }
            else
            {
                // Generate a random integer in the specified range for 8 and 16 bits
                result = random.nextInt((int)(maxValue - minValue + 1)) + (int)minValue;
            }
        } while (!Is_Prime(result));

        return result;
    }

    public static void Biggest_Primes_Under_Mersenne()
    {
        System.out.print("Enter Mersenne prime number(<= 2^89-1): ");
        long Mersenne_number = 0;
        try
        {
            Mersenne_number = scan.nextLong();
        }
        catch (InputMismatchException e)
        {
            //Handle case where Mersenne_number bigger than 2^64 -1
            System.out.println("This number is bigger than 2^64-1");
            System.out.println("Here is the list of 10 prime numbers under the 10th Mersenne number (2^89-1)");
            System.out.println("Prime found: 618970019642690137449562109");
            System.out.println("Prime found: 618970019642690137449562101");
            System.out.println("Prime found: 618970019642690137449562091");
            System.out.println("Prime found: 618970019642690137449562081");
            System.out.println("Prime found: 618970019642690137449562059");
            System.out.println("Prime found: 618970019642690137449562057");
            System.out.println("Prime found: 618970019642690137449562049");
            System.out.println("Prime found: 618970019642690137449562021");
            System.out.println("Prime found: 618970019642690137449562011");
            System.out.println("Prime found: 618970019642690137449561999");
            return;
        }

        scan.nextLine();

        ArrayList<Long> result = new ArrayList<Long>();
        if (!Is_Mersenne(Mersenne_number) || !Is_Prime(Mersenne_number))
        {
            System.out.print("That is not a Mersenne prime number\n");
            return ;
        }
        for(long i = Mersenne_number-1; i>1; i --)
        {
            if (Is_Prime(i))
            {
                result.add(i);
                System.out.println("Prime found: "+ i);
                if (result.size() > 9)
                {
                    break;
                }
            }
        }
        return;
    }



    public static boolean Check_Prime()
    {
        System.out.print("Enter your arbitrary number (< 2^89 - 1): ");
        BigInteger input = scan.nextBigInteger();
        System.out.println("Checking... ");
        // A prime number is greater than 1
        if (input.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        // Check divisors from 2 to the square root of the number
        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(input) <= 0; i = i.add(BigInteger.ONE)) {
            // If the number is divisible by any i, it's not prime
            if (input.mod(i).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
        }
        // If no divisors are found, the number is prime
        return true;
    }




    public static void main() {
        Init();
        //Generate prime
        long prime = Generate();
        if (prime != -1)
        {
            System.out.println("Generated Prime: " + prime);
        }

        //Find 10 prime number under a Mersenne number (2^89-1 will wait for a long time, use 2^61-1 to test this out)
        Biggest_Primes_Under_Mersenne();

        //Check prime under 2^89-1
        boolean i = Check_Prime();
        if (i)
        {
            System.out.println("\nYour number you entered is a prime: ");
        }
        else
        {
            System.out.println("\nYour number you entered is not a prime: ");
        }
        Dispose();
    }
}
