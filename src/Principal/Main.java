package Principal;

import API.melhorEnvio.CotacaoResposta;
import API.melhorEnvio.Pacote;
import Consulta.FreteService;
import Consulta.ViaCepService;
import com.google.gson.JsonSyntaxException;
import exceptions.CepInvalidoException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String buscarCep = "";
        try {
            while(!buscarCep.equalsIgnoreCase("sair")){

                System.out.println("Informe o peso do pacote (kg): ");
                double peso = input.nextDouble();

                System.out.println("Informe a altura do pacote (cm): ");
                double altura = input.nextDouble();

                System.out.println("Informe a largura do pacote (cm): ");
                double largura = input.nextDouble();

                System.out.println("Informe o comprimento do pacote (cm): ");
                double comprimento = input.nextDouble();

                Pacote novoPacote = new Pacote(peso, altura, largura, comprimento);

                System.out.println("Digite o CEP de origem da encomenda: ");
                String cepOrigemEncomenda = input.next();

                System.out.println("Digite o CEP de destino da encomenda: ");
                String cepDestinoEncomenda = input.next();



                ViaCepService viaCepService = new ViaCepService();
                viaCepService.buscarEndereco(cepOrigemEncomenda);
                viaCepService.buscarEndereco(cepDestinoEncomenda);

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
        }catch (JsonSyntaxException e){
            System.out.println("CEP invalido, por favor coloque no formato (00000000)");
        }catch (CepInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Formato das dimencoes do pacote invalido, infome no formato de peso (0,30)");
        }
        System.out.println("Programa finalizado. Até mais!");
        input.close();
    }
}