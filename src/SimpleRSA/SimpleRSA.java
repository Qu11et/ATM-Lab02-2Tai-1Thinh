package SimpleRSA;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.Scanner;
import java.security.SecureRandom;

public class SimpleRSA {

    public static void main(String[] args) throws Exception {
        System.out.println("Simple RSA Encryption");
        System.out.println("*********************");
        System.out.println("This is the message to be encrypted: Hello RSA!");
        System.out.println("*********************");
        System.out.println("The public key is generated randomly or you can input them yourself.");
        System.out.println("*********************");
        Scanner scanner = new Scanner(System.in);
        String choose;
        do {
            //Let the user choose if he/she wants to randomize a key or input it himself/herself
            System.out.print("So what do you want? Input it yourself or Random? (type 'i' for input, 'r' for random) ");
            choose = scanner.nextLine();
        } while (!choose.equals("i") && !choose.equals("r"));

        BigInteger p, q, e;
        if (choose.equals("i")) {
            // Read p, q, and e from user input
            do {
                System.out.print("Enter prime number p (must be a prime number & have at least 128 digits): ");
                p = new BigInteger(scanner.nextLine());
                if (!p.isProbablePrime(10) || p.compareTo(new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) <= 0) {
                    System.out.println("p is not a prime number, or it not large enough. Please enter p again.");
                }
            } while (!p.isProbablePrime(10) || p.compareTo(new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) <= 0);

            do {
                System.out.print("Enter prime number q (must be a prime number & have at least 128 digits): ");
                q = new BigInteger(scanner.nextLine());
                if (!q.isProbablePrime(10) || p.compareTo(new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) <= 0) {
                    System.out.println("q is not a prime number, or it not large enough. Please enter q again.");
                }
            } while (!q.isProbablePrime(10) || p.compareTo(new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) <= 0);

            do {
                System.out.print("Enter public exponent e (must be a prime number & have at least 6 digits): ");
                e = new BigInteger(scanner.nextLine());
                if (!e.isProbablePrime(10) || p.compareTo(new BigInteger("100000")) <= 0) {
                    System.out.println("e is not a prime number, or it not large enough. Please enter e again.");
                }
            } while (!e.isProbablePrime(10) || p.compareTo(new BigInteger("100000")) <= 0);
        }
        else {
            // Given prime numbers and public exponent
            p = generateRandomPrime();
            q = generateRandomPrime();
            e = generateRandomPrimeExponent();
        }

        // Generate key pair
        KeyPair keyPair = generateKeyPair(p, q, e);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Output public and private keys
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

        System.out.println("*********************");
        System.out.println("Public Key: ");
        System.out.println("Modulus: " + publicKeySpec.getModulus());
        System.out.println("Exponent: " + publicKeySpec.getPublicExponent());

        System.out.println("*********************");

        System.out.println("Private Key: ");
        System.out.println("Modulus: " + privateKeySpec.getModulus());
        System.out.println("Exponent: " + privateKeySpec.getPrivateExponent());
        System.out.println("*********************");

        // Message to be encrypted
        String message = "Hello RSA!";

        // Encrypt the message
        byte[] encryptedMessage = encrypt(message, publicKey);
        // Encode the encrypted message to Base64
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted Message: " + encodedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, privateKey);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }

    public static KeyPair generateKeyPair(BigInteger p, BigInteger q, BigInteger e) throws Exception {
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(n, d);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes);
    }

    public static BigInteger generateRandomPrime() {
        SecureRandom random = new SecureRandom();
        BigInteger prime;
        do {
            prime = new BigInteger(1024, random);
        } while (!prime.isProbablePrime(10) || prime.compareTo(new BigInteger("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")) <= 0);
        return prime;
    }

    public static BigInteger generateRandomPrimeExponent() {
        SecureRandom random = new SecureRandom();
        BigInteger exponent;
        do {
            exponent = new BigInteger(128, random);
        } while (!exponent.isProbablePrime(10) || exponent.compareTo(new BigInteger("100000")) <= 0);
        return exponent;
    }
}