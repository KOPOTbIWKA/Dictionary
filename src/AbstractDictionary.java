import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDictionary implements Dictionary {
    private String filename;

    public AbstractDictionary(String filename) {
        this.filename = filename;
    }

    @Override
    public Map<String, String> readDictionary() {
        Map<String, String> dictionary = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (isValidKey(parts[0])) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dictionary;
    }

    @Override
    public void removeEntry(String key) {
        Map<String, String> dictionary = readDictionary();
        dictionary.remove(key);
        writeDictionary(dictionary);
    }

    @Override
    public String searchEntry(String key) {

        return key;
    }

    @Override
    public void addEntry(String key, String value) {

    }

    protected abstract boolean isValidKey(String key);

    private void writeDictionary(Map<String, String> dictionary) {

    }
}