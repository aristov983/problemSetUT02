package clases;

public class NodoAritmetico {
    private String dato;
    private NodoAritmetico izquierda;
    private NodoAritmetico derecha;

    private static final String operadores = "+-/*";
    private static final String numeros = "0123456789";

    public NodoAritmetico(String dato){
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
    }

    public static boolean esOperador(String dato){
        return operadores.contains(dato);  
    }

    public static boolean esNumero(String dato){
        try {
            Double.parseDouble(dato);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Si no es operador ni numero entonces tiene que ser variable.
    public static boolean esVariable(String dato){
        return (!operadores.contains(dato) && !numeros.contains(dato));
    }

    // Getters y Setters
    public String getDato(){
        return dato;
    }

    public void setDato(String dato){
        this.dato = dato;
    }

    public NodoAritmetico getIzquierda(){
        return izquierda;
    }

    public void setIzquierda(NodoAritmetico izquierda){
        this.izquierda = izquierda;
    }

    public NodoAritmetico getDerecha(){
        return derecha;
    }

    public void setDerecha(NodoAritmetico derecha){
        this.derecha = derecha;
    }
}
