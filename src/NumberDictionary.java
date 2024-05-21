import java.util.HashMap;
import java.util.Map;

public class NumberDictionary implements Dictionary {
    private String filename;

    public NumberDictionary(String filename) {
        this.filename = filename;
    }

    @Override
    public Map<String, String> readDictionary() {

        return Map.of();
    }

    @Override
    public void removeEntry(String key) {

    }

    @Override
    public String searchEntry(String key) {

        return key;
    }

    @Override
    public void addEntry(String key, String value) {

    }
}