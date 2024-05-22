import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractDictionary implements Dictionary {
    private final String filename;

    public AbstractDictionary(String filename) {
        this.filename = filename;
    }

    @Override
    public Map<String, String> readDictionary() {
        Map<String, String> dictionary = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (isValidKey(key) && isValidValue(value)) {
                        dictionary.put(key, value);
                    } else {
                        System.out.println("Неверный формат ключа или значения: " + line);
                    }
                } else {
                    System.out.println("Неверный формат строки: " + line);
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
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            writeDictionary(dictionary);
            System.out.println("Запись удалена.");
        } else {
            System.out.println("Запись с ключом '" + key + "' не найдена.");
        }
    }

    @Override
    public void searchEntry(String key) {
        Map<String, String> dictionary = readDictionary();
        if (dictionary.get(key) != null) {
            System.out.println("Перевод: " + dictionary.get(key));
        } else {
            System.out.println("Запись не найдена.");
        }
    }

    @Override
    public void addEntry(String key, String value) {
        if (!isValidKey(key)) {
            System.out.println("Неверный формат ключа");
            return;
        }
        if (!isValidValue(value)) {
            System.out.println("Неверный формат значения");
            return;
        }
        Map<String, String> dictionary = readDictionary();
        dictionary.put(key, value);
        writeDictionary(dictionary);
    }

    protected abstract boolean isValidKey(String key);

    private boolean isValidValue(String value) {
        return value.matches("[А-Яа-яЁё]+");
    }

    private void writeDictionary(Map<String, String> dictionary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
