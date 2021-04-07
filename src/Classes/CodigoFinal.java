package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CodigoFinal {

    List<String> codigoIntermediario = new ArrayList();
    List<String> listaDeVariaveis = new ArrayList();
    List<String> codigoFinal = new ArrayList();
    int contador;
    Stack<String> pilha = new Stack();

    private String aux[];

    private void Inicializacao() {
        codigoIntermediario = Intermediario.codigoIntermediario;
        listaDeVariaveis = Intermediario.tabelaDeVariaveis;
    }

    public void codigoFinal() {
        Inicializacao();

        codigoFinal.add("MAIN");
        alocarMemoriaVariaveis();

        for (int i = 0; i < codigoIntermediario.size(); i++) {

            aux = codigoIntermediario.get(i).split(" ");
            System.out.println(Arrays.toString(aux));

            for (int k = 0; k < aux.length; k++) {

                if (aux[k].equals("leia")) {
                    codigoFinal.add("READ");
                    codigoFinal.add("STVL 0," + buscarEnderecoMemoria(aux[k + 1]));
                }
                if (aux[k].equals("imprima")) {
                    codigoFinal.add("LDVL 0," + buscarEnderecoMemoria(aux[k + 1]));
                    codigoFinal.add("PRNT");
                }

                if (aux[k].equals("se")) {
                    String rotulo;
                    rotulo = obterRotulo();
                    pilha.add(rotulo);

                    aux = codigoIntermediario.get(i + 1).split(" ");
                    gerarCodigoExpressão(aux);

                    switch (aux[(aux.length - 1)]) {
                        case "<":
                            codigoFinal.add("LESS");
                            break;
                        case ">":
                            codigoFinal.add("GRTR");
                            break;
                        case "=":
                            codigoFinal.add("EQUA");
                            break;
                        default:
                            break;
                    }
                    codigoFinal.add("JMPF " + rotulo + "   if");
                }

                if (aux[k].equals("senao")) {
                    String rotulo;
                    rotulo = obterRotulo();

                    codigoFinal.add("JUMP " + rotulo);
                    codigoFinal.add(pilha.pop() + ": NOOP    else");
                    pilha.add(rotulo);
                }

                if (aux[k].equals("enquanto")) {
                    
                    String rotulo;
                    rotulo = obterRotulo();
                    pilha.add(rotulo);

                    aux = codigoIntermediario.get(i + 1).split(" ");
                    gerarCodigoExpressão(aux);

                    switch (aux[(aux.length - 1)]) {
                        case "<":
                            codigoFinal.add("LESS");
                            break;
                        case ">":
                            codigoFinal.add("GRTR");
                            break;
                        case "=":
                            codigoFinal.add("EQUA");
                            break;
                        default:
                            break;
                    }                    
                    codigoFinal.add("JMPF " + rotulo + " until");
                }

                if (aux[k].equals("fimEstrutura")) {
                    String rotulo;

                    rotulo = pilha.pop();

                    codigoFinal.add(rotulo + ": NOOP");
                }

                if (aux[k].equals("=")) {

                    gerarCodigoExpressão(aux);
                    codigoFinal.add("STVL 0," + buscarEnderecoMemoria(aux[0]));
                }
            }

        }
        finalizacao();
    }

    private void alocarMemoriaVariaveis() {
        codigoFinal.add("ALOC " + listaDeVariaveis.size());
    }

    private int buscarEnderecoMemoria(String variavel) {
        int aux = 0;

        for (int i = 0; i < listaDeVariaveis.size(); i++) {
            if (listaDeVariaveis.get(i).equals(variavel)) {
                aux = i;
                break;
            }
        }
        return aux;
    }

    private void gerarCodigoExpressão(String[] expressao) {

        for (int k = 0; k < expressao.length; k++) {

            // se for uma variavel
            for (int i = 0; i < listaDeVariaveis.size(); i++) {
                if (listaDeVariaveis.get(i).equals(expressao[k])) {
                    codigoFinal.add("LDVL 0," + i);
                    break;
                }
            }

            // se for um numero
            if (expressao[k].trim().matches("[0-9]*")) {

                if (!expressao[k].isEmpty()) {
                    codigoFinal.add("LDCT " + expressao[k]);
                }

            }

            //operações matematicas
            if (expressao[k].trim().equals("+")) {
                codigoFinal.add("ADDD");
            }
            if (expressao[k].trim().equals("-")) {
                codigoFinal.add("SUBT");
            }
            if (expressao[k].trim().equals("*")) {
                codigoFinal.add("MULT");
            }
            if (expressao[k].trim().equals("/")) {
                codigoFinal.add("DIVI");
            }
        }
    }

    private void finalizacao() {
        codigoFinal.add("DLOC " + listaDeVariaveis.size());
        codigoFinal.add("STOP");
        codigoFinal.add("END");
    }

    private String obterRotulo() {
        contador++;
        String retorno = "L" + contador;
        return retorno;
    }

    public void imprimirCodigoFinal() {

        System.out.println("=======================================\n\n");
        for (int i = 0; i < codigoFinal.size(); i++) {
            System.out.println(codigoFinal.get(i));
        }
    }
}
