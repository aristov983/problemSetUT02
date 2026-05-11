package clases;

public class NodoLista<T> {
    private T dato;
    private NodoLista<T> siguiente;

    public NodoLista(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
}
