package clases;

public class TDALista<T> {
    private NodoLista<T> inicio;

    /* public void agregar(NodoLista<T> nuevo){

        if (inicio==null){
            inicio = nuevo;
        } else {
            NodoLista<T> actual = inicio;

            while (actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
        }
    } */

    public void agregar(T dato){

    NodoLista<T> nuevo = new NodoLista<>(dato);

    if (inicio == null){
        inicio = nuevo;
    } else {

        NodoLista<T> actual = inicio;

        while (actual.getSiguiente() != null){
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);
    }
}

    public void mostrar() {
        NodoLista<T> actual = inicio;

        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public TDALista() {
        inicio=null;
    }

}
