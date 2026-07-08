import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);
        //String cep = input.next();
        try {
            ViaCepService cepService = new ViaCepService();
            cepService.buscarEndereco("39995000");
        }catch (InterruptedException | IOException e) {
            System.out.println("erro de conexao");
        }

    }
}