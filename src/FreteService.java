import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class FreteService {
    public List<CotacaoResposta> calcularFrete(String cepOrigem, String cepDestino, Pacote pacote)
            throws IOException, InterruptedException {
        Type listType = new TypeToken<List<CotacaoResposta>>(){}.getType();
        List<CotacaoResposta> cotacoes = gson.fromJson(json, listType);
        return 0;
    }
}
