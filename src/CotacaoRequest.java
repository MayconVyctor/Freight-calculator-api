import com.google.gson.annotations.SerializedName;

public class CotacaoRequest {
    private CepFrete from;
    private CepFrete to;

    @SerializedName("package")
    private Pacote pacote;

    public CotacaoRequest(CepFrete from, CepFrete to, Pacote pacote) {
        this.from = from;
        this.to = to;
        this.pacote = pacote;
    }
}