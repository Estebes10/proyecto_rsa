import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class prueba {

	//Algoritmo de euclides (no se usa)
	public static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	//Algoritmo extendido de euclides (No se usa)
	public static int euclidesExtendido(int a, int b){
        int d=0,x=0,y=0;
        if(b==0){
            d=(int) a;
            x=1;
            y=0;
            return d;
        }
        int x2 = 1, x1 = 0, y2 = 0, y1 = 1;
        int q=0, r=0;
        while(b>0)
        {
            q = (a/b);
            r = a%b;
            x = (int) (x2-q*x1);
            y = (int) (y2-q*y1);
            a = b;
            b =  r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
            d= a;
        }
        x = x2;
        y = y2;
        return d;
	}

  //inverso multiplicativo (No se usa)
  public static int CalcularInverso(int n,int z){
        int mcd = 0;
        int x=0,y = 0;
        if(n >z){
            mcd= euclidesExtendido(n,z);
        }
        if(n< z){
            mcd= euclidesExtendido(z,n);
        }
        System.out.println("MCD: "+mcd);
        if(mcd >1){
            System.out.println("EL INVERSO NO EXISTE");
            y=0;
        }
        else{
            y= mcd;
            if(y< 0)
            {
                y=(y+z);
            }
        }
        //System.out.println("Hola y:" +y);
        return y;
    }

  //cifrar
	public static int cipher(int M, int e, int n){
		int PM = 0, exp = 0;
		exp = (int) Math.pow(M, e);
		PM = exp % n;
		return PM;
	}

  //decifrar
	public static long decipher(int C, int d, int n){
		long SC = 0, exp = 0;
		exp = (long) Math.pow(C, d);
		SC = exp % n;
		return SC;
	}

  //verificar si un numero es primo o no
  public static int isPrime(int n) {
      int iCount;
      iCount = 2;
      //validar si es multiplo de dos
      if (iCount<2){
        return 0;
      }
      //si no, verificar si es multiplo de otros
      while(iCount<=(n/2)){
          if(n%iCount==0)
              return 0;
          iCount++;
      }
      return 1;
  }

  public static int fnFindE(int a){
      int cont;
      cont=2;
      while(cont<a){
          if(a%cont!=0)
              return cont;
          cont++;
      }
      return 0;
  }

  public static int fnFindD(int a,int b){
      int a1,a2,a3,b1,b2,b3,t1,t2,t3,q;
      a1=1;
      a2=0;
      a3=a;
      b1=0;
      b2=1;
      b3=b;
      while(b3!=1){
          q=a3/b3;

          t1=a1-(q*b1);
          t2=a2-(q*b2);
          t3=a3-(q*b3);

          a1=b1;
          a2=b2;
          a3=b3;

          b1=t1;
          b2=t2;
          b3=t3;
      }
      if(b2<0)
          b2=b2+a;
      return b2;
  }

  public int fnFindText(int a,int b,int c){
      int iCount,t;
      iCount=1;
      t=1;
      while(iCount<=b){
          t=t*a;
          t=t%c;
          iCount++;
      }
      return (t%c);
  }

	public static void main(String[] args) {
		/*Declaracion de variables*/
		int p = 0, q = 0, n = 0, e = 0, phi = 0, result = 0, M = 0, min = 2, max = 0, d = 0;
		double g = 0;
		long result_decipher = 0;
		//Objeto teclado
		Scanner keyboard = new Scanner(System.in);

		/* Pedir numeros*/
		System.out.println("Dame p:");
		p = keyboard.nextInt();
		System.out.println("Dame q:");
		q = keyboard.nextInt();
    if((isPrime(p) == 1) && (isPrime(q)) == 1){
  		//Obtener n
  		n = p * q;

  		//Obtener phi
  		phi = (p - 1) * (q - 1);
      //obtener e
      e = fnFindE(phi);
			//obtener d
      d = fnFindD(phi,e);
      System.out.println("P = ("+e+","+n+")");
  		System.out.println("Escribe el numero a cifrar");
  		M = keyboard.nextInt();
  		result = cipher(M, e, n);
  		System.out.println("Mensaje cifrado: " + result);
      System.out.println("S = ("+d+","+n+")");
  		result_decipher = decipher(result, d, n);
  		System.out.println("Mensaje decifrado: " + result_decipher);
    }else{
        System.out.println("Error! Ingresa dos numeros primos!");
    }
	}
}
