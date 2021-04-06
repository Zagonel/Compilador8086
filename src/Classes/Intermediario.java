package Classes;

import Util.Posfixa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intermediario {

    List<Token> listaTokens = new ArrayList();
    List<String> logIntermediario = new ArrayList();
    public static List<String> codigoIntermediario = new ArrayList();
    public static List<String> tabelaDeVariaveis = new ArrayList();
    
    String formula = "";
    private char FITA[];

    public void listarTokens() {
        System.out.println("==============================================\n\n");
        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            listaTokens.add(Lexico.listaDeTokens.get(i));
        }
    }

    public void codigoIntermediario() {
        listarTokens();

        for (int p = 0; p < listaTokens.size(); p++) {
            if (listaTokens.get(p).getToken().equals("integer")) {
                
                codigoIntermediario.add("var " + listaTokens.get(p + 1).getLexema());
                tabelaDeVariaveis.add(listaTokens.get(p + 1).getLexema());                
                
                logIntermediario.add("Reconhecido a variavel " + listaTokens.get(p + 2).getLexema());
            }
        }

        for (int i = 0; i < listaTokens.size(); i++) {

            if (listaTokens.get(i).getToken().equals("consoleIn")) {
                codigoIntermediario.add("leia " + listaTokens.get(i + 2).getLexema());
                logIntermediario.add("Reconhecido o comando de leitura da variavel " + listaTokens.get(i + 2).getLexema());

            } else if (listaTokens.get(i).getToken().equals("consoleOut")) {
                codigoIntermediario.add("imprima " + listaTokens.get(i + 2).getLexema());
                logIntermediario.add("Reconhecido o comando de impressão da variavel " + listaTokens.get(i + 2).getLexema());

            } else if (listaTokens.get(i).getToken().equals("while")) {

                codigoIntermediario.add("enquanto ");
                logIntermediario.add("Reconhecido o comando Enquanto ");

                for (int x = i + 2; x < listaTokens.size(); x++) {

                    if (!listaTokens.get(x).getToken().equals("fechaPA")) {
                        formula += listaTokens.get(x).getLexema() + " ";
                    } else {
                        break;
                    }
                }

                formula = Posfixa.conversao(formula);

                codigoIntermediario.add(formula);
                logIntermediario.add("Reconhecido a formula condicional " + formula);
                formula = "";

            } else if (listaTokens.get(i).getToken().equals("if")) {

                codigoIntermediario.add("se ");
                logIntermediario.add("Reconhecido o comando IF ");

                for (int y = i + 2; y < listaTokens.size(); y++) {

                    if (!listaTokens.get(y).getToken().equals("fechaPA")) {
                        formula += listaTokens.get(y).getLexema() + " ";
                    } else {
                        break;
                    }
                }

                formula = Posfixa.conversao(formula);

                codigoIntermediario.add(formula);
                logIntermediario.add("Reconhecido a formula condicional " + formula);
                formula = "";

            } else if (listaTokens.get(i).getToken().equals("else")) {

                codigoIntermediario.add("senao ");
                logIntermediario.add("Reconhecido o comando Else ");

            } else if (listaTokens.get(i).getToken().equals("fechaCH")) {

                codigoIntermediario.add("fimEstrutura ");
                logIntermediario.add("Reconhecido final de estrutura condicional");

            } else if (listaTokens.get(i).getToken().equals("equal")) {
                for (int k = i; k < listaTokens.size(); k++) {
                    if (!listaTokens.get(i).getToken().equals("final")) {
                        if (listaTokens.get(k - 1).getLexema().equals(";")) {
                            break;
                        }
                        formula += listaTokens.get(k - 1).getLexema() + " ";
                    }
                }
                formula = Posfixa.conversao(formula);

                codigoIntermediario.add("aritmetico "+formula);
                logIntermediario.add("Reconhecido a formula aritimetica " + listaTokens.get(i - 1).getLexema() + " " + listaTokens.get(i).getLexema() + " " + formula);
                formula = "";
            }
        }
    }

    public void imprimirCodigoIntermediario() {
        for (int i = 0; i < codigoIntermediario.size(); i++) {
            System.out.println("\n" + codigoIntermediario.get(i));
        }
    }

}
