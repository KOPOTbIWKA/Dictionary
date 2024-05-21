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

    protected abstract boolean isValidKey(String key);

    private void writeDictionary(Map<String, String> dictionary) {

    }
}