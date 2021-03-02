package Classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lexico {

    List<String> gramatica = new ArrayList<>();
    public static List<Token> listaDeTokens = new ArrayList<>();

    private char FITA[];
    private String CAMINHO;
    private int comecoLexama;
    private int controladorLinha = 0;
    private int controladorPosicao = 0;
    private int qtdLinhas;

    private int contadorDeLinhas(String caminho) throws FileNotFoundException, IOException {

        LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(caminho)));
        String nextLine;
        int qtd = 0;

        while ((nextLine = lineCounter.readLine()) != null) {
            if (nextLine == null) {
                break;
            }
            qtd++;
        }
        return qtd;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private boolean isSymbol(char c) {
        return c == ';' || c == '=' || c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '{' || c == '}';
    }

    public void iniciarLista(String URL) throws FileNotFoundException, IOException {
        CAMINHO = URL;
        qtdLinhas = contadorDeLinhas(CAMINHO);
        Scanner in = new Scanner(new FileReader(URL));
        
        for (int i = 0; qtdLinhas != i; i++) {
            gramatica.add(in.nextLine().concat("\0"));           
        }        
        inicioFita();
    }

    private void inicioFita() {

        if (controladorLinha < gramatica.size()) {
            controladorPosicao = 0;
            FITA = gramatica.get(controladorLinha).toCharArray();
            controladorLinha++;
        }
        if (FITA[0] == '\0') {
            while (qtdLinhas > controladorLinha) {
                inicioFita();
            }
        } else {
            q0();
        }
    }

    private void q0() {
        if (isSpace(FITA[controladorPosicao])) {
            controladorPosicao++;
            while ((FITA[controladorPosicao] == ' ') || FITA[controladorPosicao] == ' ') {
                if (FITA[controladorPosicao] == ' ' || FITA[controladorPosicao] == ' ') {
                    controladorPosicao++;
                }
            }
        }
        if (FITA[controladorPosicao] == 'b') {
            comecoLexama = controladorPosicao;
            controladorPosicao++;
            q1();

        } else if (FITA[controladorPosicao] == 'i') {
            comecoLexama = controladorPosicao;
            controladorPosicao++;
            q10();

        } else if (FITA[controladorPosicao] == 'c') {
            comecoLexama = controladorPosicao;
            controladorPosicao++;
            q14();

        } else if (FITA[controladorPosicao] == 'e') {
            comecoLexama = controladorPosicao;
            controladorPosicao++;
            q26();
        } else if (FITA[controladorPosicao] == 'w') {
            comecoLexama = controladorPosicao;
            controladorPosicao++;
            q30();
        } else if (FITA[controladorPosicao] == ';') {
            comecoLexama = controladorPosicao;
            q35();
        } else if (FITA[controladorPosicao] == '(') {
            comecoLexama = controladorPosicao;
            q36();
        } else if (FITA[controladorPosicao] == ')') {
            comecoLexama = controladorPosicao;
            q37();
        } else if (FITA[controladorPosicao] == '{') {
            comecoLexama = controladorPosicao;
            q38();
        } else if (FITA[controladorPosicao] == '}') {
            comecoLexama = controladorPosicao;
            q39();
        } else if (FITA[controladorPosicao] == '+') {
            comecoLexama = controladorPosicao;
            q40();
        } else if (FITA[controladorPosicao] == '-') {
            comecoLexama = controladorPosicao;
            q41();
        } else if (FITA[controladorPosicao] == '*') {
            comecoLexama = controladorPosicao;
            q42();
        } else if (FITA[controladorPosicao] == '/') {
            comecoLexama = controladorPosicao;
            q43();
        } else if (FITA[controladorPosicao] == '=') {
            comecoLexama = controladorPosicao;
            q44();
        } else if (FITA[controladorPosicao] == '>') {
            comecoLexama = controladorPosicao;
            q45();
        } else if (FITA[controladorPosicao] == '<') {
            comecoLexama = controladorPosicao;
            q46();
        } else if (isChar(FITA[controladorPosicao])) {
            comecoLexama = controladorPosicao;
            qvar();
        }

        if (FITA.length > (controladorPosicao + 1)) {
            if (isDigit(FITA[controladorPosicao]) && isChar(FITA[controladorPosicao + 1])) {
                System.out.println("ERRO LEXICO: Variavel nao pode comecar com um numero: Linha: " + controladorLinha + " Caracter: " + controladorPosicao);
                inicioFita();
            }
        }
        if ((FITA.length > (controladorPosicao + 1)) && controladorPosicao > 0) {

            if (isDigit(FITA[controladorPosicao])) {

                if ((isSpace(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao - 1])) || isSymbol(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao - 1])) {
                    comecoLexama = controladorPosicao;
                    qint();
                }
            }
        }
    }

    private void q1() {
        if (FITA[controladorPosicao] == 'e') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q2();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }

    }

    private void q2() {
        if (FITA[controladorPosicao] == 'g') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q3();
                }
            }
        }
    }

    private void q3() {
        if (FITA[controladorPosicao] == 'i') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q4();
                }
            }
        }

    }

    private void q4() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isDigit(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q5();
                }
            }
        }
    }

    private void q5() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isDigit(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q6();
                }
            }

        }

    }

    private void q6() {
        if (FITA[controladorPosicao] == 'i') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isDigit(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q7();
                }
            }
        }

    }

    private void q7() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isDigit(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q8();
                }
            }
        }

    }

    // ultima letra beginning
    private void q8() {
        if (FITA[controladorPosicao] == 'g') {
            if ((controladorLinha - 1) == 0) {
                if (FITA.length > (controladorPosicao + 1)) {
                    if (FITA[controladorPosicao + 1] != '\0') {
                        controladorPosicao++;
                        tratamentoErroBeginning();
                    } else {
                        controladorPosicao++;
                        q9();
                    }
                }
            } else {
                System.out.println("ERRO SINTATICO: A palavra beginning deve estar na primeira linha do programa:  Linha: " + controladorLinha + "  Caracter: " + controladorPosicao);
            }
            if (FITA.length > (controladorPosicao + 1)) {
                if (isChar(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao++;
                    qvar();
                } else if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    System.out.println("ERRO LEXICO: Palavra reservada. A beginning é reservada pelo sistema: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                }
            }
        }
    }

    private void tratamentoErroBeginning() {

        for (int i = 0; FITA[controladorPosicao] != '\0'; i++) {

            if (FITA.length > controladorPosicao) {
                controladorPosicao++;
                if ((FITA[controladorPosicao] >= 33 && FITA[controladorPosicao] <= 255)) {
                    break;
                }
            }
        }

        if (FITA[controladorPosicao] == '\0') {
            q9();
        } else {
            System.out.println("Erro Lexico: Caracter inesperado --- " + " Linha: " + controladorLinha + " --- " + " Caracter: " + controladorPosicao);
            inicioFita();
        }
    }

    //finalizador token beginning
    private void q9() {
        // reconhece o token de comeco "beginning"  
        String token = "beginning";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            if (FITA[aux] == ' ') {
                aux++;
            } else {
                lexema += FITA[aux];
                aux++;
            }
        }
        imprimirTokens(token, lexema);
    }

    //ultima letra if
    private void q10() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isDigit(FITA[controladorPosicao + 1]) || isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q11();
                }
            }
        }

        if (FITA[controladorPosicao] == 'f') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra if é reservada pelo sistema e não pode ser usada como variavel: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q13();
                }

            }
        }
    }

    //ultima letra int
    private void q11() {
        if (FITA[controladorPosicao] == 't') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra int é reservada pelo sistema e não pode ser usada como variavel: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q12();
                }
            }
        }
    }

    //finalizador token integer
    private void q12() {
        String token = "integer";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        int auxiliar = (controladorPosicao + 1);

        if (isSpace(FITA[auxiliar])) {
            auxiliar++;
            while ((FITA[auxiliar] == ' ')) {
                if (FITA[auxiliar] == ' ') {
                    if (FITA.length > auxiliar) {
                        auxiliar++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        imprimirTokens(token, lexema);

        if (FITA.length > auxiliar) {
            if (isDigit(FITA[auxiliar])) {
                System.out.println("ERRO LEXICO: Variavel nao pode ser somente um numero: Linha: " + controladorLinha + " Caracter: " + controladorPosicao);
                controladorPosicao++;
                System.out.println(FITA[controladorPosicao]);
                q0();
            }
        }
    }

//finalizador token if
    private void q13() {
        // reconhece o token "if" (tem que arrumar pra só aceitar o formato if(soma>2) ou algo do tipo  
        String token = "if";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    private void q14() {
        if (FITA[controladorPosicao] == 'o') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q15();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q15() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q16();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q16() {
        if (FITA[controladorPosicao] == 's') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q17();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q17() {
        if (FITA[controladorPosicao] == 'o') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q18();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();

        }
    }

    private void q18() {
        if (FITA[controladorPosicao] == 'l') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q19();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q19() {
        if (FITA[controladorPosicao] == 'e') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q20();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q20() {

        if (FITA[controladorPosicao] == 'I') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q21();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
        if (FITA[controladorPosicao] == 'O') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q22();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    //ultima letra consoleIn
    private void q21() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra consoleIn é reservada pelo sistema e nao pode ser usada como variavel: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q23();
                }

            }
        }
    }

    private void q22() {
        if (FITA[controladorPosicao] == 'u') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q24();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    // finalizador de token consoleIn
    private void q23() {
        // reconhece o token consoleIn  

        String token = "consoleIn";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //ultima letra consoleOut
    private void q24() {
        if (FITA[controladorPosicao] == 't') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra consoleOut é reservada pelo sistema: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q25();
                }

            }
        }
    }

    //finalizador token consoleOut
    private void q25() {
        // reconhece o token consoleOut  
        String token = "consoleOut";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    private void q26() {
        if (FITA[controladorPosicao] == 'l') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q27();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q47();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q27() {
        if (FITA[controladorPosicao] == 's') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q28();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    //ultima letra else
    private void q28() {
        if (FITA[controladorPosicao] == 'e') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra consoleOut é reservada pelo sistema: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q29();
                }

            }
        }
    }

    //finalizador token else
    private void q29() {

        // reconhece o token else  
        String token = "else";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    private void q30() {
        if (FITA[controladorPosicao] == 'h') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q31();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q31() {
        if (FITA[controladorPosicao] == 'i') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q32();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q32() {
        if (FITA[controladorPosicao] == 'l') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q33();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

//ultima letra while
    private void q33() {
        if (FITA[controladorPosicao] == 'e') {
            if (FITA.length > (controladorPosicao + 1)) {

                int aux = (controladorPosicao + 1);

                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                if (FITA[aux] == ';' || FITA[aux] == '=' || FITA[aux] == '\0') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra while é reservada pelo sistema: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    q34();
                }
            }
        }
    }

    // finalizador token while
    private void q34() {
        // reconhece o token while  
        String token = "while";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token final
    private void q35() {
        // reconhece o token ;  
        String token = "final";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token abrePA
    private void q36() {
        // reconhece o token abrePA  
        String token = "abrePA";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token fechaPA
    private void q37() {
        // reconhece o token fechaPA  
        String token = "fechaPA";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token abreCH
    private void q38() {
        // reconhece o token abreCH  
        String token = "abreCH";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token fechaCH
    private void q39() {
        // reconhece o token fechaCH  
        String token = "fechaCH";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token addition
    private void q40() {
        // reconhece o token addition 
        String token = "addition";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    //finalizador token  substraction
    private void q41() {
        // reconhece o token substraction
        String token = "substraction";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    //finalizador token multiplication
    private void q42() {
        // reconhece o token multiplication
        String token = "multiplication";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    //finalizador token division
    private void q43() {
        // reconhece o token division
        String token = "division";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    //finalizador  token equal
    private void q44() {
        // reconhece o token equal
        String token = "equal";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    //finalizador  token maior
    private void q45() {
        // reconhece o token maior
        String token = "maior";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token menor
    private void q46() {
        // reconhece o token menor
        String token = "menor";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    private void q47() {
        if (FITA[controladorPosicao] == 'd') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q48();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q48() {
        if (FITA[controladorPosicao] == 'i') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q49();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

    private void q49() {
        if (FITA[controladorPosicao] == 'n') {
            if (FITA.length > (controladorPosicao + 1)) {
                if (isSpace(FITA[controladorPosicao + 1]) || isSymbol(FITA[controladorPosicao + 1]) || isDigit(FITA[controladorPosicao + 1])) {
                    controladorPosicao--;
                    qvar();
                } else {
                    controladorPosicao++;
                    q50();
                }
            }
        } else if (isSymbol(FITA[controladorPosicao]) || isSpace(FITA[controladorPosicao])) {
            controladorPosicao--;
            qvar();
        }
    }

//ultima letra ending
    private void q50() {
        if (FITA[controladorPosicao] == 'g') {
            if (FITA.length > (controladorPosicao + 1)) {
                int aux = (controladorPosicao + 1);
                if (isSpace(FITA[controladorPosicao + 1])) {
                    aux++;
                    while ((FITA[aux] == ' ')) {
                        if (FITA[aux] == ' ') {
                            aux++;
                        } else {
                            break;
                        }
                    }
                }
                // tentar achar a ultima linha do arquivo e ver se elas todas sao vazias, e se forem o ending vai ser o ultimo token.                
                if (FITA[aux] == ';' || FITA[aux] == '=') {
                    System.out.println("ERRO LEXICO: Palavra reservada. A palavra ending é reservada pelo sistema: Linha : " + controladorLinha + " Caracter: " + controladorPosicao);
                    inicioFita();
                } else {
                    controladorPosicao++;
                    q51();
                }
            }
        }
    }

    //finalizador token ending
    private void q51() {

        // reconhece o token ending
        String token = "ending";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }
        imprimirTokens(token, lexema);
    }

    //finalizador token var
    private void qvar() {

        while (isDigit(FITA[controladorPosicao]) || isChar(FITA[controladorPosicao])) {

            if (FITA.length > controladorPosicao + 1) {

                if (!isSymbol(FITA[controladorPosicao + 1]) && !isSpace(FITA[controladorPosicao + 1])) {
                    if (FITA.length > controladorPosicao + 1) {
                        controladorPosicao++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // reconhece o token de "variavel"         
        String token = "var";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    public void qint() {

        while (isDigit(FITA[controladorPosicao])) {
            if (FITA.length > controladorPosicao + 1) {
                if (!isSymbol(FITA[controladorPosicao + 1]) && !isSpace(FITA[controladorPosicao + 1])) {
                    if (FITA.length > controladorPosicao + 1) {
                        controladorPosicao++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // reconhece o token de "variavel"         
        String token = "int";
        String lexema = "";
        int aux = comecoLexama;
        while (aux <= controladorPosicao) {
            lexema += FITA[aux];
            aux++;
        }

        imprimirTokens(token, lexema);
    }

    private void imprimirTokens(String token, String lexema) {

        Token novoToken = new Token();

        novoToken.setToken("");
        novoToken.setLexema("");
        novoToken.setLinha(0);
        novoToken.setColuna(0);

        novoToken.setToken(token);
        novoToken.setLexema(lexema);
        novoToken.setLinha(controladorLinha);
        novoToken.setColuna(comecoLexama);
        listaDeTokens.add(novoToken);

        if (FITA[controladorPosicao] == '\0') {
            inicioFita();
        } else {
            controladorPosicao++;
            q0();
        }
    }

    public void imprimirTabelaTokens() {
        int aux = listaDeTokens.size();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("C:\\Users\\igor_\\OneDrive\\Documentos\\NetBeansProjects\\Compilador8086\\src\\Util\\TabelaTokens.txt", false);
        } catch (IOException ex) {
            Logger.getLogger(Lexico.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; aux > i; i++) {
            System.out.println(listaDeTokens.get(i));
            printWriter.println(listaDeTokens.get(i).getToken() + " " + listaDeTokens.get(i).getLexema() + " " + listaDeTokens.get(i).getLinha() + " " + listaDeTokens.get(i).getColuna());
            printWriter.flush();
        }
    }
}
