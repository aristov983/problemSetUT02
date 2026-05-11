package clases;

public class Nodo<T> {

    private T dato;
    private Nodo<T> izquierda;
    private Nodo<T> derecha;

    // Constructor
    public Nodo(T dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
    }

    @Override
    public String toString() {
        return String.valueOf(dato);
    }

    // Getters y Setters
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo<T> izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo<T> getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo<T> derecha) {
        this.derecha = derecha;
    }
}