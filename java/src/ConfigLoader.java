import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigLoader {
    private static String configPath = "config.properties";
    private ConfigLoader() {};

    public static Path GetConfigKey(String key){
        try{
            FileInputStream fs = new FileInputStream(configPath);
            Properties properties = new Properties();
            properties.load(fs);
            return Path.of(properties.getProperty(key));
        } catch(IOException ex){
            return null;
        }
    }
}
