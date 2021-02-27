package Classes;

import java.util.ArrayList;
import java.util.List;

public class Sintaxico {

    Pilha pilha = new Pilha();
    List<String> registros = new ArrayList();

    //inicia no propio construtor
    public Sintaxico() {
        Token[] listaDeTokens = listarTokens();
        empilhar("$");
        empilhar("PROGRAM");
    }

    public void analisadorLexico() {

    }

    private void empilhar(String elemento) {
        pilha.push(elemento);
        registros.add("Elemento empilhado: " + elemento);
    }

    private void desenpilhar() {
        registros.add("Elemento deempilhado: " + pilha.pop());
    }

    private Token[] listarTokens() {
        Token[] pilhaTokens = new Token[(Lexico.listaDeTokens.size()) + 1];

        for (int i = 0; i < Lexico.listaDeTokens.size(); i++) {
            pilhaTokens[i] = Lexico.listaDeTokens.get(i);
        }

        Token terminal = new Token();
        terminal.setToken("$");
        terminal.setLexema("$");
        terminal.setLinha(0);
        terminal.setColuna(0);
        pilhaTokens[Lexico.listaDeTokens.size()] = terminal;

        return pilhaTokens;
    }

    public void imprimirTeste() {
        Token[] teste = listarTokens();

        for (int i = 0; i < teste.length; i++) {
            System.out.println("--------------------------------------------");
            System.out.println(teste[i]);
        }
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

}
