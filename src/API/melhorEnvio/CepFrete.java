package API.melhorEnvio;

import com.google.gson.annotations.SerializedName;

public class CepFrete {
    @SerializedName("postal_code")
    private String postalCode;

    public CepFrete(String postalCode) {
        this.postalCode = postalCode;
    }
}