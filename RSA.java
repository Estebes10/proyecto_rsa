import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RSA {

	//Algoritmo de euclides
	public static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	//Algoritmo extendido de euclides
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

  //cifrar
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

  //decifrar
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

  //verificar si un numero es primo o no
  public static boolean isPrime(int n) {
      //validar si es multiplo de dos
      if (n%2==0){
        return false;
      }
      //si no, verificar si es multiplo de otros
      for(int i=3;i*i<=n;i+=2) {
          if(n%i==0){
              return false;
          }
      }
      return true;
  }

  public static int get_e(int p,int q){
      int n=0, phi=0,e=0, maxdiv = 0,min = 1, max = 0;
      n = p*q;
      phi = (p-1)*(q-1);
      if((isPrime(p) != true)&& (isPrime(q) != true)){
          System.out.println("p y q deben ser numeros primos");
      }else if(p == q){
          System.out.println("p y q no pueden ser iguales");
      }
      max = phi;
      e = ThreadLocalRandom.current().nextInt(min, max + 1);
      maxdiv = gcd(e,phi);
      while(maxdiv != 1){
          e = ThreadLocalRandom.current().nextInt(min, max + 1);
          maxdiv = gcd(e,phi);
      }
      return e;
  }

	public static void main(String[] args) {
		/*Declaracion de variables*/
		int p = 0, q = 0, n = 0, e = 0, phi = 0, option = 0, result = 0, C = 0, M = 0, min = 2, max = 0, d = 0, count = 0;
		double g = 0;
		long result_decipher = 0;
		//Objeto teclado
		Scanner keyboard = new Scanner(System.in);

		/* Pedir numeros*/
		System.out.println("Dame p:");
		p = keyboard.nextInt();
		System.out.println("Dame q:");
		q = keyboard.nextInt();

		//Obtener n
		n = p * q;

		//Obtener phi
		phi = (p - 1) * (q - 1);

		//Rango para la variable e
		max = phi;

		//Si el d es negativo el algoritmo euclidiano no trabaja
		while (d <= 0) {

			//hasta que pasa el algoritmo euclidiano
			while (g != 1) {
        //e = get_e(p,q);
        //e = 7;
				e = ThreadLocalRandom.current().nextInt(min, max + 1);
				g = gcd(e, phi);
			}

			d = multiplicative_inverse(e, phi);

			count++;

			if(count > 1000) {
				System.out.println("Error al calcular \"d\", no puede ser negativo, prueba otra ves");
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

    System.out.println("P = ("+e+","+n+")");
		while (option != 3) {
			System.out.println("Menu\n1. Cifrar un numero.\n2. Decifrar numero.\n3. Salir.\nOpcion?");
			option = keyboard.nextInt();

			switch (option) {
  			case 1:
  				System.out.println("Escribe el numero a cifrar");
  				M = keyboard.nextInt();
  				result = cipher(M, e, n);
  				System.out.println("Mensaje cifrado: " + result);
  			break;
  			case 2:
          System.out.println("S = ("+d+","+n+")");
  				System.out.println("Escribe el numero de decifrar");
  				C = keyboard.nextInt();
  				result_decipher = decipher(C, d, n);
  				System.out.println("Mensaje decifrado: " + result_decipher);
  			break;
  			case 3:
  				System.out.println("Adios");
  			break;
  			default:
  				System.out.println("Escoje una opcion valida");
				break;
			}
		}
	}
}
