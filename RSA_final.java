import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RSA_final{
  //Algoritmo de euclides
  public static int gcd(int x, int y) {
    return y == 0 ? x : gcd(y, x % y);
  }

  //mcd=0,x=0,y=0 en un inicio
  public static int euclidesExtendidoRe(int a, int b,int mcd,int x, int y){
        int x2=0,y2=0,x1=0,y1=0;
        if (b == 0){
            mcd = a;
            x2 = 1;
            y2 = 0;
        }
        else{
            euclidesExtendidoRe(b,a%b,mcd,x,y);
            x1= x2; y1=y2; x2=y1;
            y2=x1- (a/b)*y1;
        }
        return mcd;
   }

   public static int euclidesExtendido(int a, int b){
        int d=0,x=0,y=0;
        if(b==0)
        {
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
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
            d=(int) a;
        }
        x = x2;
        y = y2;
        return d;
    }

   public static int CalcularInverso(int n,int z){
        int mcd = 0;
        int x=0,y = 0;
        if(n >z){
            mcd= euclidesExtendido(n,z);
            //mcd = euclidesExtendidoRe(n,z,0,0,0);
        }
        if(n< z){
            mcd= euclidesExtendido(z,n);
            //mcd = euclidesExtendidoRe(z,n,0,0,0);
        }

        System.out.println("MCD: "+mcd);
        if(mcd >1){
            System.out.println("EL INVERSO NO EXISTE");
            y=0;
        }else{
            y=(int) mcd;
            if(y< 0){
                y=(int) (y+z);
            }
        }
        //System.out.println("Hola y:" +y);
        return y;
    }

    //verificar si un numero es primo o no
    public static boolean esPrimo(int n) {
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

    public static int generaE(int phi){
        int n=0,e=0, maxdiv = 0,min = 1, max = 0;
        max = phi;
        e = ThreadLocalRandom.current().nextInt(min, max + 1);
        maxdiv = gcd(e,phi);
        while(maxdiv == 1){
            e = ThreadLocalRandom.current().nextInt(min, max + 1);
            maxdiv = gcd(e,phi);
        }
        return e;
    }

    private static int primoRelativo(int phi){
        int eTemp = 0, min = 2, maxcdiv = 0;
        int max = phi;
        eTemp = ThreadLocalRandom.current().nextInt(min, max + 1);
        maxcdiv = gcd(eTemp,phi);
        while(maxcdiv != 1){
          eTemp = ThreadLocalRandom.current().nextInt(min, max + 1);
          maxcdiv = gcd(eTemp,phi);
        }
        return eTemp;
    }

    //cifrar
    public static int cifrar(int M, int e, int n){
      int PM = 0, exp = 0;
      exp = (int) Math.pow(M, e);
      PM = exp % n;
      return PM;
    }

    //decifrar
    public static long decifrar(int C, int d, int n){
      long SC = 0, exp = 0;
      exp = (long) Math.pow(C, d);
      SC = exp % n;
      return SC;
    }

    public static void main(String[] args) {
  		/*Declaracion de variables*/
  		int p = 0, q = 0, n = 0, e = 0, phi = 0, result = 0, C = 0, M = 0, min = 2, max = 0, d = 0, count = 0;
  		long g = 0;
  		long result_decipher = 0;
  		//Objeto teclado
  		Scanner keyboard = new Scanner(System.in);
  		/* Pedir numeros*/
  		System.out.println("Dame p:");
  		p = keyboard.nextInt();
  		System.out.println("Dame q:");
  		q = keyboard.nextInt();
      if((esPrimo(p) == true) && (esPrimo(q)) == true){
    		//Obtener n
    		n = p * q;
    		//Obtener phi
    		phi = (p - 1) * (q - 1);
        System.out.println("PHI: "+phi);
        //obtener e
        //e = primoRelativo(phi);
        e = 7;
        //e = generaE(phi);
        System.out.println("E: "+e);
  			//obtener d
        d = CalcularInverso(e,phi);
        System.out.println("D: "+d);
        System.out.println("P = ("+e+","+n+")");
    		System.out.println("Escribe el numero a cifrar");
    		M = keyboard.nextInt();
    		result = cifrar(M, e, n);
    		System.out.println("Mensaje cifrado: " + result);
        System.out.println("S = ("+d+","+n+")");
    		result_decipher = decifrar(result, d, n);
    		System.out.println("Mensaje decifrado: " + result_decipher);
      }else{
          System.out.println("Error! Enter two prime numbers!");
      }
  	}
}
