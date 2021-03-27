package Classes;

import java.util.ArrayList;
import java.util.List;

public class Intermediario {

    List<Token> listaTokens = new ArrayList();
    List<String> logIntermediario = new ArrayList();
    List<String> codigoIntermediario = new ArrayList();
    String formula = "";

    public void listarTokens() {
        System.out.println("==============================================\n\n");
        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            listaTokens.add(Lexico.listaDeTokens.get(i));
        }
    }

    public void codigoIntermediario() {
        listarTokens();

        for (int i = 0; i < listaTokens.size(); i++) {

            if (listaTokens.get(i).getToken().equals("consoleIn")) {
                codigoIntermediario.add("leia " + listaTokens.get(i + 2).getLexema());
                logIntermediario.add("Reconhecido o comando de leitura da variavel " + listaTokens.get(i + 2).getLexema());

            } else if (listaTokens.get(i).getToken().equals("consoleOut")) {
                codigoIntermediario.add("imprima " + listaTokens.get(i + 2).getLexema());
                logIntermediario.add("Reconhecido o comando de impressão da variavel " + listaTokens.get(i + 2).getLexema());

            } else if (listaTokens.get(i).getToken().equals("integer")) {
                codigoIntermediario.add("var " + listaTokens.get(i + 1).getLexema());
                logIntermediario.add("Reconhecido a variavel " + listaTokens.get(i + 2).getLexema());

            } else if (listaTokens.get(i).getToken().equals("equal")) {

                for (int k = i; k < listaTokens.size(); k++) {
                    if (!listaTokens.get(i).getToken().equals("final")) {
                        if (listaTokens.get(k - 1).getLexema().equals(";")) {
                            break;
                        }
                        formula += listaTokens.get(k - 1).getLexema() + " ";
                    }
                }
                codigoIntermediario.add(formula);
                logIntermediario.add("Reconhecido a formula aritimetica " + listaTokens.get(i - 1).getLexema() + " " + listaTokens.get(i).getLexema() + " " + formula);
            }
        }
    }

    public void imprimirCodigoIntermediario() {
        System.out.println("E aqui?");
        for (int i = 0; i < codigoIntermediario.size(); i++) {
            System.out.println("\n" + codigoIntermediario.get(i));
        }
    }

}
