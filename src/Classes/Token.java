package Classes;

public class Token {

    private String token;
    private String lexema;
    private int linha;
    private int coluna;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return "{" + "token = " + token + " | lexema = " + lexema + " | linha = " + linha + " | coluna = " + coluna + '}';
    }
    

}
