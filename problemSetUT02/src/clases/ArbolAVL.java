package clases;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public NodoAVL getRaiz() { return raiz; }

    //utilidades de altura y balance

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private void actualizarAltura(NodoAVL nodo) {
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
    }

    // factor de balance: izq - der. positivo = cargado a izquierda
    private int factorBalance(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    //rotaciones

    // rotación simple derecha (caso LL)
    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierda();
        NodoAVL T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // rotación simple izquierda (caso RR)
    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecha();
        NodoAVL T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // balancea el nodo si es necesartio 
    private NodoAVL balancear(NodoAVL nodo) {
        actualizarAltura(nodo);
        int fb = factorBalance(nodo);

        // caso LL
        if (fb > 1 && factorBalance(nodo.getIzquierda()) >= 0)
            return rotarDerecha(nodo);

        // caso LR
        if (fb > 1 && factorBalance(nodo.getIzquierda()) < 0) {
            nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
            return rotarDerecha(nodo);
        }

        // caso RR
        if (fb < -1 && factorBalance(nodo.getDerecha()) <= 0)
            return rotarIzquierda(nodo);

        // caso RL
        if (fb < -1 && factorBalance(nodo.getDerecha()) > 0) {
            nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
            return rotarIzquierda(nodo);
        }

        return nodo; // ya estaba balanceado
    }

    

    public void insertar(Nave nave) {
        raiz = insertarRec(raiz, nave);
    }

    private NodoAVL insertarRec(NodoAVL nodo, Nave nave) {
        // base case 
        if (nodo == null) return new NodoAVL(nave);

        int clave = nave.getCodigo();

        if (clave < nodo.getDato().getCodigo()) {
            nodo.setIzquierda(insertarRec(nodo.getIzquierda(), nave));
        } else if (clave > nodo.getDato().getCodigo()) {
            nodo.setDerecha(insertarRec(nodo.getDerecha(), nave));
        } else {
            // clave duplicada, no insertamos
            return nodo;
        }

        return balancear(nodo);
    }

    //recorridos

    public void inorden(NodoAVL nodo) {
        if (nodo == null) return;
        inorden(nodo.getIzquierda());
        System.out.print(nodo.getDato().getCodigo() + " ");
        inorden(nodo.getDerecha());
    }

    public void preorden(NodoAVL nodo) {
        if (nodo == null) return;
        System.out.print(nodo.getDato().getCodigo() + " ");
        preorden(nodo.getIzquierda());
        preorden(nodo.getDerecha());
    }

    //operaciones del ejercicio 13

    // retorna lista con los códigos de todas las naves exploradoras
    // recorre todo el árbol buscando clase == "Explorador"
    public TDALista<Integer> identificarExploradoras() {
        TDALista<Integer> lista = new TDALista<>();
        exploradoresRec(raiz, lista);
        return lista;
    }

    private void exploradoresRec(NodoAVL nodo, TDALista<Integer> lista) {
        if (nodo == null) return;
        if (nodo.getDato().getClase().equals("Explorador")) {
            lista.agregar(nodo.getDato().getCodigo());
        }
        exploradoresRec(nodo.getIzquierda(), lista);
        exploradoresRec(nodo.getDerecha(), lista);
    }

    // calcula el promedio de combustible de las naves exploradoras
    // necesita dos pasadas (o una acumulando suma y cantidad)
    public double promedioComustibleExploradoras() {
        int[] acum = {0, 0}; // acum[0] = suma, acum[1] = cantidad
        promedioRec(raiz, acum);
        if (acum[1] == 0) return 0; // ojo la div por cero jiji
        return (double) acum[0] / acum[1];
    }

    private void promedioRec(NodoAVL nodo, int[] acum) {
        if (nodo == null) return;
        if (nodo.getDato().getClase().equals("Explorador")) {
            acum[0] += nodo.getDato().getCombustible();
            acum[1]++;
        }
        promedioRec(nodo.getIzquierda(), acum);
        promedioRec(nodo.getDerecha(), acum);
    }

    // hardcodeando par aprobar cositas

    public static void ejercicio13() {
        Core.headerMessage(13);

        ArbolAVL registro = new ArbolAVL();

        // insertamos las naves en el orden dado por el enunciado
        registro.insertar(new Nave(10, "Explorador", 0));
        registro.insertar(new Nave(20, "Destructor", 90));
        registro.insertar(new Nave(30, "Médica", 100));
        registro.insertar(new Nave(40, "Explorador", 50));
        registro.insertar(new Nave(50, "Carguero", 20));
        registro.insertar(new Nave(60, "Destructor", 28));
        registro.insertar(new Nave(70, "Explorador", 14));
        registro.insertar(new Nave(80, "Médica", 7));
        registro.insertar(new Nave(90, "Carguero", 23));
        registro.insertar(new Nave(100, "Explorador", 26));

        System.out.println("Registro AVL construido. Recorrido inorden (por código):");
        registro.inorden(registro.getRaiz());
        System.out.println();

        System.out.println("\nNaves exploradoras (códigos):");
        registro.identificarExploradoras().mostrar();

        System.out.printf("%nCombustible promedio de exploradoras: %.2f%n",
                registro.promedioComustibleExploradoras());

        Core.footerMessage(13);
    }
}
