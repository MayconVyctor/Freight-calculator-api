package API.melhorEnvio;

import com.google.gson.annotations.SerializedName;

public class CotacaoResposta {
        private String id;
        private String name;
        private String price;
        @SerializedName("delivery_time")
        private int deliveryTime;
        private Transportadora company;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public Transportadora getCompany() {
        return company;
    }
}
