import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RSA {

	//Euclidean algorithm
	public static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	//Extended Euclidean algorithm
	public static int multiplicative_inverse(int e, int phi) {
		int d = 0, x = 0, y = 0, x1 = 0, x2 = 1, y1 = 1, temp_phi = phi, temp1 = 0, temp2 = 0;

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

		/*
		System.out.println("d: " + d);
		System.out.println("phi: " + phi);
		*/

		return d;
	}

  //encripted function
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

  //decripted function
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
		int p = 0, q = 0, n = 0, e = 0, phi = 0, option = 0, result = 0, C = 0, M = 0, min = 2, max = 0, d = 0, count = 0;
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

		//If d is negative the Euclidean algorithm doesn't work
		while (d <= 0) {

			//Until pass Euclidean algorithm
			while (g != 1) {
				e = ThreadLocalRandom.current().nextInt(min, max + 1);
				g = gcd(e, phi);
			}

			d = multiplicative_inverse(e, phi);

			count++;

			if(count > 60) {
				System.out.println("Error with \"d\", is negative! Try again!");
				option = 3;
				break;
			}
		}

		/*
		System.out.println("phi: " + phi);
		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("d: " + d);
		System.out.println("e: " + e);
		System.out.println("n: " + n);
		*/

		while (option != 3) {
			System.out.println("Menu\n1. Cipher a message.\n2. Decipher a message.\n3. Exit.\nOption?");
			option = keyboard.nextInt();

			switch (option) {
			case 1:
				System.out.println("Give the message to cipher");
				M = keyboard.nextInt();
				result = cipher(M, e, n);
				System.out.println("Message ciphered: " + result);
				break;

			case 2:
				System.out.println("Give the message to decipher");
				C = keyboard.nextInt();
				result_decipher = decipher(C, d, n);
				System.out.println("Message deciphered: " + result_decipher);
				break;

			case 3:
				System.out.println("Bye");
				break;

			default:
				System.out.println("Choose a valid option");
				break;
			}
		}
	}
}
