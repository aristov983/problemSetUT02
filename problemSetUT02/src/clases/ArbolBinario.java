package clases;

public class ArbolBinario<T> {

    private Nodo<T> raiz;

    // Constructor
    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    // Recorrido PREORDEN
    public void preorden(Nodo<T> nodo) {

        if (nodo != null) {

            System.out.print(nodo.getDato() + " ");

            preorden(nodo.getIzquierda());

            preorden(nodo.getDerecha());
        }
    }

    // Recorrido INORDEN
    public void inorden(Nodo<T> nodo) {

        if (nodo != null) {

            inorden(nodo.getIzquierda());

            System.out.print(nodo.getDato() + " ");

            inorden(nodo.getDerecha());
        }
    }

    // Recorrido POSTORDEN
    public void postorden(Nodo<T> nodo) {

        if (nodo != null) {

            postorden(nodo.getIzquierda());

            postorden(nodo.getDerecha());

            System.out.print(nodo.getDato() + " ");
        }
    }

    public int altura(Nodo<T> nodo) {

        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = altura(nodo.getIzquierda());
        int alturaDerecha = altura(nodo.getDerecha());

        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public int tamano(Nodo<T> nodo) {
        if (nodo==null){
            return 0;
        }

        return 1 + tamano(nodo.getIzquierda()) + tamano(nodo.getDerecha());
    }

    public int cantidadHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            return 1;
        }

        return cantidadHojas(nodo.getIzquierda()) + cantidadHojas(nodo.getDerecha());
    }

    public int nodoInterno(Nodo<T> nodo){
        if (nodo==null){
            return 0;
        }

        if (nodo.getIzquierda()==null && nodo.getDerecha() == null){
            return 0;
        }

        return 1 + nodoInterno(nodo.getIzquierda()) + nodoInterno(nodo.getDerecha());
    }

    //Metodo complementario para completos.
    public TDALista<Nodo<T>> completosRec(Nodo<T> nodo, TDALista<Nodo<T>> lista){
        if (nodo==null){
            return lista;
        }

        if (nodo.getIzquierda() != null && nodo.getDerecha() != null){
            lista.agregar(nodo);
        }

        completosRec(nodo.getIzquierda(), lista);
        completosRec(nodo.getDerecha(), lista);

        return lista;
    }

    public TDALista<Nodo<T>> completos(Nodo<T> nodo){

        TDALista<Nodo<T>> ListaNodos = new TDALista<>();

        completosRec(nodo, ListaNodos);

        return ListaNodos;

    }

    //Metodo complementario para nivel.
    public TDALista<Nodo<T>> nivelRec(Nodo<T> nodo, TDALista<Nodo<T>> lista, int nivelBuscado, int nivelActual){
        if (nodo==null || nivelActual > nivelBuscado){
            return lista;
        }

        if (nivelBuscado == nivelActual){
            lista.agregar(nodo);
        }

        nivelRec(nodo.getIzquierda(), lista, nivelBuscado,nivelActual+1);
        nivelRec(nodo.getDerecha(), lista, nivelBuscado,nivelActual+1);

        return lista;
    }

    public TDALista<Nodo<T>> nivel(Nodo<T> nodo, int nivelBuscado){

        TDALista<Nodo<T>> ListaNodos = new TDALista<>();

        nivelRec(nodo, ListaNodos, nivelBuscado, 0);

        return ListaNodos;

    }

    // ==== EJERCICIO 7 ====
    public void sustituirVariable(NodoAritmetico nodo, String variable, String numero){
        if (nodo != null){
            if (nodo.getDato().equals(variable)){
                nodo.setDato(numero);
            }

            sustituirVariable(nodo.getIzquierda(), variable, numero);
            sustituirVariable(nodo.getDerecha(), variable, numero);
        }
    }

    //Se asume que no hay variables en el arbol.
    public double evaluar(NodoAritmetico nodo){
        if (nodo == null){
            return 0;
        }

        if (nodo.getIzquierda() == null && nodo.getDerecha() == null){
            return Double.parseDouble(nodo.getDato());
        }

        if (nodo.esOperador(nodo.getDato())){
            switch (nodo.getDato()) {
                case "+":
                    return evaluar(nodo.getIzquierda()) + evaluar(nodo.getDerecha());
                case "-":
                    return evaluar(nodo.getIzquierda()) - evaluar(nodo.getDerecha());
                case "*":
                    return evaluar(nodo.getIzquierda()) * evaluar(nodo.getDerecha());
                case "/":
                    return evaluar(nodo.getIzquierda()) / evaluar(nodo.getDerecha());
                default:
                    //nunca debería entrar acá por el if
                    throw new AssertionError();
            }
        }

        return Double.parseDouble(nodo.getDato());
    }
}


