package Classes;

import Principal.Main;
import java.util.ArrayList;
import java.util.List;

public class Sintaxico {

    private List<String> pilha = new ArrayList();
    private List<Token> listaTokens = new ArrayList();

    List<String> registros = new ArrayList();

    public void analisadorLexico() {

        listarTokens();
        empilhar("$");
        empilhar("PROGRAM");

        while (!pilha.isEmpty() && !listaTokens.isEmpty()) {
            if (pilha.get(0).equals(listaTokens.get(0).getToken())) {
                desempilhar(pilha.get(0));                
                listaTokens.remove(0);
            } else {
                int aux = 0;      
                
                aux = tabelaSintatica(pilha.get(0), listaTokens.get(0).getToken());
                if (aux == -1) {
                    System.out.println("\n\nERRO Sintatico: " + listaTokens.get(0).getLexema() + " Linha: " + listaTokens.get(0).getLinha() + " Coluna: " + listaTokens.get(0).getColuna() + "\n\n");
                    break;
                } else {
                    producaoSintatica(aux);
                }
            }
        }
    }

    public void imprimirLog() {
        for (int i = 0; i < registros.size(); i++) {
            System.out.println(registros.get(i));
            System.out.println("\n======================================================\n");
        }
    }

    private void empilhar(String elemento) {
        pilha.add(0, elemento);
        registros.add("Elemento empilhado: " + elemento);
    }

    private void desempilhar(String nome) {
        pilha.remove(0);
        registros.add("Elemento desempilhado: " + nome);
    }

    public void listarTokens() {

        System.out.println("==============================================\n\n");
        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            listaTokens.add(Lexico.listaDeTokens.get(i));
        }

        Token terminal = new Token();
        terminal.setToken("$");
        terminal.setLexema("$");
        terminal.setLinha(0);
        terminal.setColuna(0);
        listaTokens.add(terminal);
    }

    // Aqui é montada a tabela sintatica do gaus, linha = Não-terminais , Coluna = terminais;
    private int tabelaSintatica(String linha, String coluna) {

        if (linha.equals("PROGRAM") && coluna.equals("beginning")) {
            return 0;
        }

        //Comands_list comeco        
        if (linha.equals("COMMANDS_LIST") && coluna.equals("var")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("int")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("integer")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("consoleIn")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("consoleOut")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("if")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("while")) {
            return 1;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("ending")) {
            return 2;
        } else if (linha.equals("COMMANDS_LIST") && coluna.equals("fechaCH")) {
            return 2;
        }
        //Comands_list final

        //Command comeco
        if (linha.equals("COMMAND") && coluna.equals("var")) {
            return 4;
        } else if (linha.equals("COMMAND") && coluna.equals("int")) {
            return 4;
        } else if (linha.equals("COMMAND") && coluna.equals("integer")) {
            return 3;
        } else if (linha.equals("COMMAND") && coluna.equals("consoleIn")) {
            return 11;
        } else if (linha.equals("COMMAND") && coluna.equals("consoleOut")) {
            return 10;
        } else if (linha.equals("COMMAND") && coluna.equals("if")) {
            return 15;
        } else if (linha.equals("COMMAND") && coluna.equals("while")) {
            return 18;
        }
        //Command final

        //Assignment comeco        
        if (linha.equals("ASSIGNMENT") && coluna.equals("final")) {
            return 7;
        } else if (linha.equals("ASSIGNMENT") && coluna.equals("equal")) {
            return 5;
        }
        //Assignment final

        //Parameter comeco
        if (linha.equals("PARAMETER") && coluna.equals("var")) {
            return 9;
        } else if (linha.equals("PARAMETER") && coluna.equals("int")) {
            return 8;
        }
        //Parameter final

        //Symbol comeco
        if (linha.equals("SYMBOL") && coluna.equals("var")) {
            return 6;
        } else if (linha.equals("SYMBOL") && coluna.equals("int")) {
            return 6;
        } else if (linha.equals("SYMBOL") && coluna.equals("abrePA")) {
            return 6;
        }
        //Symbol comeco

        //Mathematical_Expression comeco
        if (linha.equals("MATHEMATICAL_EXPRESSION") && coluna.equals("var")) {
            return 19;
        } else if (linha.equals("MATHEMATICAL_EXPRESSION") && coluna.equals("int")) {
            return 19;
        } else if (linha.equals("MATHEMATICAL_EXPRESSION") && coluna.equals("abrePA")) {
            return 20;
        }
        //Mathematical_Expression final

        // Complete_Parameter comeco
        if (linha.equals("COMPLETE_PARAMETER") && coluna.equals("var")) {
            return 13;
        } else if (linha.equals("COMPLETE_PARAMETER") && coluna.equals("int")) {
            return 12;
        }
        // Complete_Parameter final

        //Calculation comeco
        if (linha.equals("CALCULATION") && coluna.equals("abrePA")) {
            return 14;
        }
        //Calculation final

        //Logical_Operator comeco
        if (linha.equals("LOGICAL_OPERATOR") && coluna.equals("maior")) {
            return 27;
        } else if (linha.equals("LOGICAL_OPERATOR") && coluna.equals("menor")) {
            return 28;
        }
        //Logical_Operator final

        // Else comeco
        if (linha.equals("ELSE") && coluna.equals("ending")) {
            return 17;
        }
        if (linha.equals("ELSE") && coluna.equals("var")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("int")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("integer")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("consoleIn")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("consoleOut")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("if")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("else")) {
            return 16;
        } else if (linha.equals("ELSE") && coluna.equals("while")) {
            return 17;
        } else if (linha.equals("ELSE") && coluna.equals("fechaCH")) {
            return 17;
        }
        //Else final

        //Mathematical_Operation comeco
        if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("final")) {
            return 22;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("fechaPA")) {
            return 22;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("addition")) {
            return 21;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("substraction")) {
            return 21;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("multiplication")) {
            return 21;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("division")) {
            return 21;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("maior")) {
            return 22;
        } else if (linha.equals("MATHEMATICAL_OPERATION") && coluna.equals("menor")) {
            return 22;
        }
        //Mathematical_Operation final

        //Arithmetic_Operator comeco
        if (linha.equals("ARITHMETIC_OPERATOR") && coluna.equals("addition")) {
            return 23;
        } else if (linha.equals("ARITHMETIC_OPERATOR") && coluna.equals("substraction")) {
            return 24;
        } else if (linha.equals("ARITHMETIC_OPERATOR") && coluna.equals("multiplication")) {
            return 25;
        } else if (linha.equals("ARITHMETIC_OPERATOR") && coluna.equals("division")) {
            return 26;
        } else {
            return -1;
        }

    }

    private void producaoSintatica(int numero) {

        if (numero == 0) {

            desempilhar("PROGRAM");

            empilhar("ending");
            empilhar("COMMANDS_LIST");
            empilhar("beginning");

        } else if (numero == 1) {

            desempilhar("COMMANDS_LIST");

            empilhar("COMMANDS_LIST");
            empilhar("COMMAND");

        } else if (numero == 2) {

            desempilhar("COMMANDS_LIST");

            registros.add("Ê");

        } else if (numero == 3) {

            desempilhar("COMMAND");

            empilhar("final");
            empilhar("ASSIGNMENT");
            empilhar("var");
            empilhar("integer");

        } else if (numero == 4) {

            desempilhar("COMMAND");

            empilhar("final");
            empilhar("ASSIGNMENT");
            empilhar("PARAMETER");

        } else if (numero == 5) {

            desempilhar("ASSIGNMENT");

            empilhar("SYMBOL");
            empilhar("equal");

        } else if (numero == 6) {

            desempilhar("SYMBOL");

            empilhar("MATHEMATICAL_EXPRESSION");

        } else if (numero == 7) {

            desempilhar("ASSIGNMENT");

            registros.add("Ê");

        } else if (numero == 8) {

            desempilhar("PARAMETER");

            empilhar("int");

        } else if (numero == 9) {

            desempilhar("PARAMETER");

            empilhar("var");

        } else if (numero == 10) {

            desempilhar("COMMAND");

            empilhar("final");
            empilhar("fechaPA");
            empilhar("COMPLETE_PARAMETER");
            empilhar("abrePA");
            empilhar("consoleOut");

        } else if (numero == 11) {

            desempilhar("COMMAND");

            empilhar("final");
            empilhar("fechaPA");
            empilhar("var");
            empilhar("abrePA");
            empilhar("consoleIn");

        } else if (numero == 12) {

            desempilhar("COMPLETE_PARAMETER");

            empilhar("int");

        } else if (numero == 13) {

            desempilhar("COMPLETE_PARAMETER");

            empilhar("var");

        } else if (numero == 14) {

            desempilhar("CALCULATION");

            empilhar("fechaPA");
            empilhar("MATHEMATICAL_EXPRESSION");
            empilhar("LOGICAL_OPERATOR");
            empilhar("MATHEMATICAL_EXPRESSION");
            empilhar("abrePA");

        } else if (numero == 15) {

            desempilhar("COMMAND");

            empilhar("ELSE");
            empilhar("fechaCH");
            empilhar("COMMANDS_LIST");
            empilhar("abreCH");
            empilhar("CALCULATION");
            empilhar("if");

        } else if (numero == 16) {

            desempilhar("ELSE");

            empilhar("fechaCH");
            empilhar("COMMANDS_LIST");
            empilhar("abreCH");
            empilhar("else");

        } else if (numero == 17) {

            desempilhar("ELSE");

            registros.add("Ê");

        } else if (numero == 18) {

            desempilhar("COMMAND");

            empilhar("fechaCH");
            empilhar("COMMANDS_LIST");
            empilhar("abreCH");
            empilhar("CALCULATION");
            empilhar("while");

        } else if (numero == 19) {

            desempilhar("MATHEMATICAL_EXPRESSION");

            empilhar("MATHEMATICAL_OPERATION");
            empilhar("PARAMETER");

        } else if (numero == 20) {

            desempilhar("MATHEMATICAL_EXPRESSION");

            empilhar("MATHEMATICAL_OPERATION");
            empilhar("fechaPA");
            empilhar("MATHEMATICAL_EXPRESSION");
            empilhar("abrePA");

        } else if (numero == 21) {

            desempilhar("MATHEMATICAL_OPERATION");

            empilhar("MATHEMATICAL_EXPRESSION");
            empilhar("ARITHMETIC_OPERATOR");

        } else if (numero == 22) {

            desempilhar("MATHEMATICAL_OPERATION");

            registros.add("Ê");

        } else if (numero == 23) {

            desempilhar("ARITHMETIC_OPERATOR");

            empilhar("addition");

        } else if (numero == 24) {

            desempilhar("ARITHMETIC_OPERATOR");

            empilhar("substraction");

        } else if (numero == 25) {

            desempilhar("ARITHMETIC_OPERATOR");

            empilhar("multiplication");

        } else if (numero == 26) {

            desempilhar("ARITHMETIC_OPERATOR");

            empilhar("division");

        } else if (numero == 27) {

            desempilhar("LOGICAL_OPERATOR");

            empilhar("maior");

        } else if (numero == 28) {

            desempilhar("LOGICAL_OPERATOR");

            empilhar("menor");

        }
    }

}
