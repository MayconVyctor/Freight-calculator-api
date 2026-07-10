package Consulta;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    public String getToken() throws IOException {
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            config.load(input);
        }
        return config.getProperty("melhorenvio.token");
    }
}