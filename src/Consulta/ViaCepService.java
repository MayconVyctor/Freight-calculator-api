package Consulta;

import API.viaCep.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CepInvalidoException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepService {
    public Endereco buscarEndereco(String cep) throws IOException, InterruptedException {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        if (cep.length() <8){
            throw new CepInvalidoException("CEP invalido, por favor informe um formato correto (00000000) ");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        Endereco enderecoProcessado = gson.fromJson(json, Endereco.class);
        if (enderecoProcessado.getErro() != null) {
            throw new CepInvalidoException("CEP infomando nao existe.");

        }
        return enderecoProcessado;
    }
}
