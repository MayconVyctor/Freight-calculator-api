package Principal;

import Consulta.ViaCepService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ViaCepService cepService = new ViaCepService();
            cepService.buscarEndereco("39995000");
        }catch (InterruptedException | IOException e) {
            System.out.println("erro de conexao");
        }

    }
}