package Classes;

import java.util.ArrayList;
import java.util.List;

public class Semantico {

    private List<Token> listaTokens = new ArrayList();
    private List<Token> listaVariavelDeclarada = new ArrayList();

    public void AnalisadorSemantico() {
        listarTokens();
        declaracaoPrevia();
        declaracaoDuplicada();

    }

    private void declaracaoPrevia() {
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
                    break;
                }
            }
        }
    }
    
    private void declaracaoDuplicada(){
        
    }

    public void listarTokens() {

        System.out.println("==============================================\n\n");
        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            listaTokens.add(Lexico.listaDeTokens.get(i));
        }
    }

}
