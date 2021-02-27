package Classes;

public class Pilha {

    private static final int MAX = 1000;
    private String[] pilha = new String[MAX];
    private int top;

    Pilha() {
        top = -1;
    }

    private boolean isEmpty() {
        return (top < 0);
    }

    public boolean push(String x) {
        if (top >= (MAX - 1)) {
            System.out.println("Estouro de Pilha!");
            return false;
        } else {
            pilha[++top] = x;
            return true;
        }
    }

    public String pop() {
        if (top < 0) {
            return "Pilha Vazia!";
        } else {
            String x = pilha[top--];
            return x;
        }
    }

    public String peek() {
        if (top < 0) {
            return "Pilha Vazia!";
        } else {
            return pilha[top];
        }
    }

}
