package Classes;

import java.util.ArrayList;
import java.util.List;

public class Semantico {

    private List<Token> listaTokens = new ArrayList();
    private List<Token> listaVariavelDeclarada = new ArrayList();

    public int AnalisadorSemantico() {

        int a1, a2, a3, a4;

        listarTokens();

        a1 = declaracaoPrevia();
        a2 = declaracaoDuplicada();
        a3 = divisaoPorZero();

        if (a1 == -1 || a2 == -1 || a3 == -1) {
            return -1;
        } else {
            return 0;
        }
    }

    private int declaracaoPrevia() {
        boolean equals = false;

        for (int i = 0; i < listaTokens.size(); i++) {
            if (listaTokens.get(i).getToken().equals("var") && listaTokens.get(i - 1).getToken().equals("integer")) {
                listaVariavelDeclarada.add(listaTokens.get(i));
            }
        }

        for (int j = 0; j < listaTokens.size(); j++) {
            if (listaTokens.get(j).getToken().equals("var")) {

                for (int k = 0; k < listaVariavelDeclarada.size(); k++) {
                    equals = listaTokens.get(j).getLexema().equals(listaVariavelDeclarada.get(k).getLexema());
                    if (equals == true) {
                        break;
                    }
                }

                if (equals == true) {
                    continue;
                } else {
                    System.out.println("ERRO Semantinco: Variavel Nao declarada anteriormente | Variavel : " + listaTokens.get(j).getLexema() + " Linha: " + listaTokens.get(j).getLinha() + " Coluna: " + listaTokens.get(j).getColuna());
                    return -1;
                }
            }
        }

        return 0;
    }

    private int declaracaoDuplicada() {

        for (int i = 0; i < listaVariavelDeclarada.size(); i++) {

            for (int j = (i + 1); j < listaVariavelDeclarada.size(); j++) {
                if (listaVariavelDeclarada.get(i).getLexema().equals(listaVariavelDeclarada.get(j).getLexema())) {
                    System.out.println("ERRO Semantico: Variavel Declarada Duas vezes: Variavel: " + listaVariavelDeclarada.get(i).getLexema() + " Linha: " + listaVariavelDeclarada.get(i).getLinha() + " Coluna: " + listaVariavelDeclarada.get(i).getColuna());
                    return -1;
                }
            }
        }
        return 0;
    }

    private int divisaoPorZero() {

        for (int i = 0; i < listaTokens.size(); i++) {

            if (listaTokens.get(i).getLexema().equals("/")) {
                if (listaTokens.get(i + 1).getLexema().equals("0")) {
                    System.out.println("ERRO Semantico: Impossivel dividir por zero: Linha: " + listaTokens.get(i + 1).getLinha() + " Coluna: " + listaTokens.get(i + 1).getColuna());
                    return -1;
                }
            }
        }
        return 0;
    }

    public void listarTokens() {

        System.out.println("==============================================\n\n");
        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            listaTokens.add(Lexico.listaDeTokens.get(i));
        }
    }

}
