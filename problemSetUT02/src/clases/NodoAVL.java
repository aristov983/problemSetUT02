package clases;

public class NodoAVL {
    private Nave dato;
    private NodoAVL izquierda;
    private NodoAVL derecha;
    private int altura;

    public NodoAVL(Nave dato) {
        this.dato = dato;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1; // nodo nuevo siempre arranca en altura 1
    }

    public Nave getDato() { return dato; }
    public void setDato(Nave dato) { this.dato = dato; }

    public NodoAVL getIzquierda() { return izquierda; }
    public void setIzquierda(NodoAVL izquierda) { this.izquierda = izquierda; }

    public NodoAVL getDerecha() { return derecha; }
    public void setDerecha(NodoAVL derecha) { this.derecha = derecha; }

    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }

    @Override
    public String toString() {
        return dato.toString();
    }
}
