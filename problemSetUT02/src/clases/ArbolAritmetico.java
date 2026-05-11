package clases;

public class ArbolAritmetico {

    private NodoAritmetico raiz;

    public ArbolAritmetico() {
        this.raiz = null;
    }

    public ArbolAritmetico(NodoAritmetico raiz) {
        this.raiz = raiz;
    }

    public NodoAritmetico getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAritmetico raiz) {
        this.raiz = raiz;
    }

    // Sustituye todas las ocurrencias de variable por el valor que le dann
    public void sustituirVariable(String variable, String valor) {
        sustituirRec(raiz, variable, valor);
    }

    private void sustituirRec(NodoAritmetico nodo, String variable, String valor) {
        if (nodo == null) return;
        if (nodo.getDato().equals(variable)) {
            nodo.setDato(valor);
        }
        sustituirRec(nodo.getIzquierda(), variable, valor);
        sustituirRec(nodo.getDerecha(), variable, valor);
    }

    // Evaluando el arbol asumiendo que no quedan variables.
    public double evaluar() {
        return evaluarRec(raiz);
    }

    private double evaluarRec(NodoAritmetico nodo) {
        if (nodo == null) return 0;

        // Hoja = operando
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            return Double.parseDouble(nodo.getDato());
        }

        double izq = evaluarRec(nodo.getIzquierda());
        double der = evaluarRec(nodo.getDerecha());

        switch (nodo.getDato()) {
            case "+": return izq + der;
            case "-": return izq - der;
            case "*": return izq * der;
            case "/":
                if (der == 0) throw new ArithmeticException("División por cero");
                return izq / der;
            default:
                throw new IllegalStateException("Token inválido: " + nodo.getDato());
        }
    }

    
    // ej "* + x 3 2"
    public void construirDesdePreorden(String expresion) {
        if (expresion == null || expresion.isBlank()) {
            throw new IllegalArgumentException("Expresión vacía");
        }
        String[] tokens = expresion.trim().split("\\s+");
        int[] indice = {0};
        raiz = construirRec(tokens, indice);
        if (indice[0] < tokens.length) {
            throw new IllegalArgumentException("Tokens sobrantes en la expresión");
        }
    }

    private NodoAritmetico construirRec(String[] tokens, int[] indice) {
        if (indice[0] >= tokens.length) {
            throw new IllegalArgumentException("Expresión incompleta");
        }
        String token = tokens[indice[0]++];
        NodoAritmetico nodo = new NodoAritmetico(token);

        if (NodoAritmetico.esOperador(token)) {
            nodo.setIzquierda(construirRec(tokens, indice));
            nodo.setDerecha(construirRec(tokens, indice));
        } else if (!NodoAritmetico.esNumero(token) && !NodoAritmetico.esVariable(token)) {
            throw new IllegalArgumentException("Token inválido: " + token);
        }

        return nodo;
    }
}
