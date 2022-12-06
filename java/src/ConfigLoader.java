import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static String configPath = "C:/Users/rockp/source/repos/Advent of Code 2022/java/config.properties";
    private ConfigLoader() {};

    public static String GetConfigKey(String key){
        try{
            FileInputStream fs = new FileInputStream(configPath);
            Properties properties = new Properties();
            properties.load(fs);
            return properties.getProperty(key);
        } catch(IOException ex){
            return null;
        }
    }
}
