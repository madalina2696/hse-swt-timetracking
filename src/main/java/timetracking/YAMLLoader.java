import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlLoader {

    private YamlLoader() {
    }

    public static List<Map<String, Object>> load(String fileName) throws IOException {
        Yaml yaml = new Yaml();
        try (FileInputStream fis = new FileInputStream(fileName + ".yaml")) {
            return yaml.load(fis);
        }
    }

    public static void save(String fileName, List<Map<String, Object>> data) throws IOException {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(fileName + ".yaml")) {
            yaml.dump(data, writer);
        }
    }
}
