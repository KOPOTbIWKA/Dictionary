import java.util.Map;

public interface Dictionary {
    Map<String, String> readDictionary();
    void removeEntry(String key);
    String searchEntry(String key);
    void addEntry(String key, String value);
}
