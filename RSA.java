import java.util.Scanner;

public class RSA {

public static int cipher(int M, int e, int n){
  int PM = 0, exp = 0;
  exp = (int) Math.pow(M, e);
  PM = exp % n;
  System.out.println("\tVariables into cipher function");
  System.out.println("\texp: " + exp);
  System.out.println("\tC: " + M);
  System.out.println("\tn: " + n);
  System.out.println("\td: " + e);
  return PM;
}

public static long decipher(int C, int d, int n){
  long SC = 0, exp = 0;
  exp = (long) Math.pow(C, d);
  SC = exp % n;
  System.out.println("\tVariables into cipher function");
  System.out.println("\texp: " + exp);
  System.out.println("\tC: " + C);
  System.out.println("\tn: " + n);
  System.out.println("\td: " + d);
  return SC;
}

public static void main(String[] args) {
  int p = 0, q = 0, n = 0, e = 0, tetha = 0, option = 0, result = 0, C = 0, M = 0;
  double d = 0, modulo = 0;
  long result_decipher = 0;

  Scanner keyboard = new Scanner(System.in);

  System.out.println("Give me p");
  p = keyboard.nextInt();

  System.out.println("Give me q");
  q = keyboard.nextInt();

  n = p * q;
  tetha = (p - 1) * (q - 1);

  System.out.println("Give me e");
  e = keyboard.nextInt();

  modulo = e % tetha;

  d = 1 / modulo;

  System.out.println("tetha: " + tetha);
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
        System.out.println("Give the message to cipher\n");
        M = keyboard.nextInt();
        result = cipher(M, e, n);
        System.out.println("Message ciphered: " + result + "\n");
      break;

      case 2:
        System.out.println("Give the message to decipher\n");
        C = keyboard.nextInt();
        result_decipher = decipher(C, e, n);
        System.out.println(result_decipher);
      break;

      case 3:
        System.out.println("Bye\n");
      break;

      default:
        System.out.println("Choose a valid option\n");
      break;
      }
    }
  }
}
