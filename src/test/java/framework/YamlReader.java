package framework;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class YamlReader {
    public static String yamlFilePath;

    @SuppressWarnings("resource")
    public static String setYamlFilePath() {

        yamlFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources"
                + File.separator + "properties" + File.separator + "config.yml";
        try {
            new FileReader(yamlFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return yamlFilePath;
    }

    public static String getYamlValue(String token) {
        if (yamlFilePath == null) {
            setYamlFilePath();
        }
        return getValue(token);
    }

    public static String getYamlValue(String yamlPath, String token) {
        if (yamlFilePath == null) {
            setYamlFilePath();
        }
        return getValue(yamlPath, token);
    }

    public static Map<String, Object> getYamlValues(String token) {
        if (yamlFilePath == null) {
            setYamlFilePath();
        }
        Reader doc;
        try {
            doc = new FileReader(yamlFilePath);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        Yaml yaml = new Yaml();
        // TODO: check the type casting of object into the Map and create
        // instance in one place
        Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
        return parseMap(object, token + ".");
    }

    private static String getValue(String token) {
        Reader doc = null;
        try {
            doc = new FileReader(yamlFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        Yaml yaml = new Yaml();
        Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
        try {
            return getMapValue(object, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String getValue(String yamlPath, String token) {
        Reader doc = null;
        try {
            doc = new FileReader(yamlPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        Yaml yaml = new Yaml();
        Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
        try {
            return getMapValue(object, token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String getMapValue(Map<String, Object> object, String token) {
        // TODO: check for proper yaml token string based on presence of '.'
        String[] st = token.split("\\.");
        return parseMap(object, token).get(st[st.length - 1]).toString();
    }

    private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
        if (token.contains(".")) {
            String[] st = token.split("\\.");
            object = parseMap((Map<String, Object>) object.get(st[0]), token.replace(st[0] + ".", ""));
        }
        return object;
    }

    public static List<String> getYamlValuesAsList(String token) {
        return Arrays.asList(getYamlValue(token).split("\\|"));
    }
}
