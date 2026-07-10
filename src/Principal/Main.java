package Principal;

import API.melhorEnvio.CotacaoResposta;
import API.melhorEnvio.Pacote;
import Consulta.FreteService;
import Consulta.ViaCepService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String buscarCep = "";
        try {
            while(!buscarCep.equalsIgnoreCase("sair")){
                System.out.println("Digite o CEP de origem da encomenda: ");
                String cepOrigemEncomenda = input.next();

                System.out.println("Digite o CEP de destino da encomenda: ");
                String cepDestinoEncomenda = input.next();

                Pacote novoPacote = new Pacote(1,1,1,1);

                FreteService freteService = new FreteService();
                System.out.println("Buscando cotações no Melhor Envio...");
                List<CotacaoResposta> opcoesDeFrete = freteService.calcularFrete(cepOrigemEncomenda, cepDestinoEncomenda,novoPacote);

                System.out.println("\n=== Opções de Frete Encontradas ===");
                for (CotacaoResposta opcao : opcoesDeFrete) {
                    if (opcao.getError() != null){
                        System.out.println(" A transportadora "+ opcao.getName() + " Apresentou um erro ao exibir o preco: " + opcao.getError());
                    }else {
                        System.out.println("Transportadora: " + opcao.getName() + " | Preço: R$ " + opcao.getPrice());
                    }
                }
                System.out.println("Deseja continuar? (Se nao insira sair) ");
                buscarCep = input.next();

            }
        }catch (InterruptedException | IOException e) {
            System.out.println("erro de conexao" + e.getMessage());
        }
        System.out.println("Programa finalizado. Até mais!");
        input.close();
    }
}