package Consulta;

import API.melhorEnvio.CepFrete;
import API.melhorEnvio.CotacaoRequest;
import API.melhorEnvio.CotacaoResposta;
import API.melhorEnvio.Pacote;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FreteService {
    public List<CotacaoResposta> calcularFrete(String cepOrigem, String cepDestino, Pacote pacote)
            throws IOException, InterruptedException {

       ConfigService configService = new ConfigService();
       String token = configService.getToken();

        CepFrete cepFreteOrigem = new CepFrete(cepOrigem);
        CepFrete cepFreteDestino = new CepFrete(cepDestino);

        CotacaoRequest cotacaoRequest = new CotacaoRequest(cepFreteOrigem, cepFreteDestino, pacote);

        String jsonBody = new Gson().toJson(cotacaoRequest);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("User-Agent", "Aplicação ConsultorDeFreteAPI (mayconsantosfc11@gmail.com)")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<ArrayList<CotacaoResposta>>(){}.getType();
        List<CotacaoResposta> resultado = new Gson().fromJson(response.body(), listType);

        return resultado;
    }
}
