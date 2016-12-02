import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RSA {

	//Euclidean algorithm
	public static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	public static double multiplicative_inverse(double e, double phi) {
		double d = 0, x = 0, y = 0, x1 = 0, x2 = 1, y1 = 1, temp_phi = phi, temp1 = 0, temp2 = 0;

		while (e > 0) {
			temp1 = temp_phi / e;
			temp2 = temp_phi - temp1 * e;
			temp_phi = e;
			e = temp2;

			x = x2 - temp1 * x1;
			y = d - temp1 * y1;

			x2 = x1;
			x1 = x;
			d = y1;
			y1 = y;
		}



		if (temp_phi == 1) {
			return d + phi;
		} else {
			return d = 3;
		}
	}

	public static int cipher(int M, int e, int n){
		int PM = 0, exp = 0;
		exp = (int) Math.pow(M, e);
		PM = exp % n;

		/*
		System.out.println("exp: " + exp);
		System.out.println("C: " + M);
		System.out.println("n: " + n);
		System.out.println("d: " + e);
		*/

		return PM;
	}

	public static long decipher(int C, int d, int n){
		long SC = 0, exp = 0;
		exp = (long) Math.pow(C, d);
		SC = exp % n;

		/*
		System.out.println("exp: " + exp);
		System.out.println("C: " + C);
		System.out.println("n: " + n);
		System.out.println("d: " + d);
		*/

		return SC;
	}

	public static void main(String[] args) {
		/*Declaration of variables */
		int p = 0, q = 0, n = 0, e = 0, phi = 0, option = 0, result = 0, C = 0, M = 0, min = 0, max = 0, d = 0;
		double g = 0;
		long result_decipher = 0;

		//Keyboard
		Scanner keyboard = new Scanner(System.in);

		/* Ask for prime numbers */
		System.out.println("Give me p");
		p = keyboard.nextInt();
		System.out.println("Give me q");
		q = keyboard.nextInt();

		//Get n
		n = p * q;

		//Get phi
		phi = (p - 1) * (q - 1);

		//Range for e variable
		max = phi;

		//Generate a random int
		e = ThreadLocalRandom.current().nextInt(min, max + 1);

		e = 7;

		//Check if e is a prime number
		g = gcd(e, phi);

		//Until get a prime number
		while (g != 1) {
			e = ThreadLocalRandom.current().nextInt(min, max + 1);
			g = gcd(e, phi);
		}

		d = (int) multiplicative_inverse(e, phi);

		System.out.println(d);


		System.out.println("phi: " + phi);
		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("d: " + d);
		System.out.println("e: " + e);
		System.out.println("n: " + n);


		while (option != 3) {
			System.out.println("Menu\n1. Cipher a message.\n2. Decipher a message.\n3. Exit.\nOption?");
			option = keyboard.nextInt();

			switch (option) {
			case 1:
				System.out.println("Give the message to cipher");
				M = keyboard.nextInt();
				result = cipher(M, e, n);
				System.out.println(result);
				break;

			case 2:
				System.out.println("Give the message to decipher");
				C = keyboard.nextInt();
				result_decipher = decipher(C, d, n);
				System.out.println(result_decipher);
				break;

			case 3:
				System.out.println("Bye :)");

			default:
				System.out.println("Choose a valid option");
				break;
			}
		}
	}
}
