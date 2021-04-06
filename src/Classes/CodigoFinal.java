package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodigoFinal {

    List<String> codigoIntermediario = new ArrayList();
    List<String> listaDeVariaveis = new ArrayList();
    List<String> codigoFinal = new ArrayList();

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

            if (aux[0].equals("leia")) {
                codigoFinal.add("READ");
                codigoFinal.add("STVL 0," + buscarEnderecoMemoria(aux[1]));
            }
            if(aux[0].equals("imprima")){
                codigoFinal.add("LDVL 0," + buscarEnderecoMemoria(aux[1]));
                codigoFinal.add("PRNT");                
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
    
    private void gerarCodigoExpressão(){
        
    }

    private void finalizacao() {
        codigoFinal.add("DLOC " + listaDeVariaveis.size());
        codigoFinal.add("STOP");
        codigoFinal.add("END");
    }

    public void imprimirCodigoFinal() {

        System.out.println("=======================================\n\n");
        for (int i = 0; i < codigoFinal.size(); i++) {
            System.out.println(codigoFinal.get(i));
        }

    }
}
