import clases.ArbolAVL;
import clases.ArbolBinario;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        do { 
            System.out.println("=================================================================================");
            System.out.println("Digitá un numero 06,07,12,13 para ejecutar el ejercicio correspondiente o 0 para salir.");
            System.out.println("=================================================================================");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 6:
                    ArbolBinario.ejercicio6();
                    break;
                case 7:
                    ArbolBinario.ejercicio7();
                    break;
                case 12:
                    break;
                case 13:
                    ArbolAVL.ejercicio13();
                    break;
                case 0:
                    sc.close();
                    System.out.println("==========");
                    System.out.println("  ADIÓS  ");
                    System.out.println("==========");
                    return;
            }
        } while (true);
    }
}