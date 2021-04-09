package Principal;

import Classes.CodigoFinal;
import Classes.Intermediario;
import Classes.Lexico;
import Classes.Semantico;
import Classes.Sintaxico;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) throws IOException {
        menuPrincipal();
    }

    public static void menuPrincipal() throws IOException {
        int opcao = 0;

        String URL = "C:\\Users\\igor_\\OneDrive\\Documentos\\NetBeansProjects\\Compilador8086\\src\\Util\\Gramatica.txt";

        Lexico arquivo = new Lexico();
        Sintaxico aux = new Sintaxico();
        Semantico aux2 = new Semantico();
        Intermediario aux3 = new Intermediario();
        CodigoFinal aux4 = new CodigoFinal();

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Principal ---------------------------|\n"
                    + "|  1 - Lexico\n"
                    + "|  2 - Sintaxico\n"
                    + "|  3 - Semantico\n"
                    + "|  4 - Codigo Final e Intermediario\n"
                    + "|  5 - Compilador Completo\n"
                    + "|  6 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));
        } catch (NumberFormatException e) {
            if (e.getMessage().equals("null")) {
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuPrincipal();
        }

        switch (opcao) {
            case 1:

                arquivo.iniciarLista(URL);
                arquivo.imprimirTabelaTokens();

                menuPrincipal();
                break;

            case 2:

                arquivo.iniciarLista(URL);

                aux.analisadorSintatico();

                int i = JOptionPane.showConfirmDialog(null, "Deseja Imprimir o log de operações?");

                if (i == 0) {
                    aux.imprimirLog();
                    menuPrincipal();
                } else {
                    menuPrincipal();
                }

                break;

            case 3:

                arquivo.iniciarLista(URL);

                aux2.AnalisadorSemantico();
                menuPrincipal();
                break;

            case 4:

                arquivo.iniciarLista(URL);

                if (aux.analisadorSintatico() == -1) {
                    menuPrincipal();
                    break;
                }

                if (aux2.AnalisadorSemantico() == -1) {
                    menuPrincipal();
                    break;
                }

                aux3.codigoIntermediario();
                aux3.imprimirCodigoIntermediario();

                aux4.codigoFinal();
                aux4.imprimirCodigoFinal();

                menuPrincipal();
                break;

            case 5:

                arquivo.iniciarLista(URL);

                if (aux.analisadorSintatico() == -1) {
                    menuPrincipal();
                    break;
                }

                if (aux2.AnalisadorSemantico() == -1) {
                    menuPrincipal();
                    break;
                }

                aux3.codigoIntermediario();
                aux4.codigoFinal();
                aux4.imprimirCodigoFinal();

                menuPrincipal();
                break;

            case 6:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuPrincipal();
                break;
        }
    }
}
