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



    //==== PONIENDO A PRUEBA LOS METODOS | ESTO DE UTILIZARÁ EN MAIN ====
    public static void ejercicio6(){
        Core.headerMessage(6);
        System.out.println("Sea el siguiente árbol binario:");
        System.out.println("        A");
        System.out.println("       / \\");
        System.out.println("      B   C");
        System.out.println("     / \\   \\");
        System.out.println("    D   E   F");
        System.out.println();

        Nodo<String> A = new Nodo<>("A");
        Nodo<String> B = new Nodo<>("B");
        Nodo<String> C = new Nodo<>("C");
        Nodo<String> D = new Nodo<>("D");
        Nodo<String> E = new Nodo<>("E");
        Nodo<String> F = new Nodo<>("F");

        A.setIzquierda(B);
        A.setDerecha(C);

        B.setIzquierda(D);
        B.setDerecha(E);

        C.setDerecha(F);

        //Creo el arbol y asigno raíz
        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.setRaiz(A);

        System.out.println("Su altura es: "+arbol.altura(arbol.getRaiz()));
        System.out.println();
        System.out.println("Su tamaño es: "+arbol.tamano(arbol.getRaiz()));
        System.out.println();
        System.out.println("La cantidad de hojas es: "+arbol.cantidadHojas(arbol.getRaiz()));
        System.out.println();
        System.out.println("La cantidad de nodos internos es: "+arbol.nodoInterno(arbol.getRaiz()));
        System.out.println();
        System.out.println("Los nodos completos (ambos hijos no nulos) son: ");
        arbol.completos(arbol.getRaiz()).mostrar();
        System.out.println();
        System.out.println("Los nodos del nivel 2 son: ");
        arbol.nivel(arbol.getRaiz(), 2).mostrar();

        Core.footerMessage(6);
    }

    public static void ejercicio7(){
        Core.headerMessage(7);
        Core.headerMessage(6);
        System.out.println("Sea el siguiente árbol binario:");
        System.out.println("        *");
        System.out.println("       / \\");
        System.out.println("      +   2");
        System.out.println("     / \\");
        System.out.println("    x   3");
        System.out.println();

        NodoAritmetico mult = new NodoAritmetico("*");
        NodoAritmetico suma = new NodoAritmetico("+");
        NodoAritmetico x = new NodoAritmetico("x");
        NodoAritmetico tres = new NodoAritmetico("3");
        NodoAritmetico dos = new NodoAritmetico("2");

        mult.setIzquierda(suma);
        mult.setDerecha(dos);
        suma.setIzquierda(x);
        suma.setDerecha(tres);

        ArbolBinario<String> arbol = new ArbolBinario<>();
        System.out.println("Sustituimos la x por 4 en el arbol binario.");
        arbol.sustituirVariable(mult, "x", "4");
        System.out.println();
        System.out.println("Resultado: " + arbol.evaluar(mult));

        Core.footerMessage(7);
    }
}


