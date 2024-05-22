import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class FileManager {
    private String letterFilename = "letter_dictionary.txt";
    private String numberFilename = "number_dictionary.txt";

    private LetterDictionary letterDictionary;
    private NumberDictionary numberDictionary;

    public FileManager() {
        ensureFileExists(letterFilename);
        ensureFileExists(numberFilename);

        letterDictionary = new LetterDictionary(letterFilename);
        numberDictionary = new NumberDictionary(numberFilename);
    }

    public String getLetterFilename() {
        return letterFilename;
    }

    public String getNumberFilename() {
        return numberFilename;
    }

    public LetterDictionary getLetterDictionary() {
        return letterDictionary;
    }

    public NumberDictionary getNumberDictionary() {
        return numberDictionary;
    }

    private void ensureFileExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Файл " + filename + " не найден, создан новый файл.");
            } catch (IOException e) {
                System.out.println("Не удалось создать файл " + filename + ": " + e.getMessage());
            }
        }
    }

    private boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    public void chooseLetterDictionary(Scanner scanner) {
        letterFilename = promptFilename(scanner, "Введите имя файла для другого буквенного словаря:");
        if (fileExists(letterFilename)) {
            letterDictionary = new LetterDictionary(letterFilename);
            System.out.println("Буквенный словарь изменен на: " + letterFilename);
        } else {
            System.out.println("Файл " + letterFilename + " не найден.");
        }
    }

    public void chooseNumberDictionary(Scanner scanner) {
        numberFilename = promptFilename(scanner, "Введите имя файла для другого цифрового словаря:");
        if (fileExists(numberFilename)) {
            numberDictionary = new NumberDictionary(numberFilename);
            System.out.println("Цифровой словарь изменен на: " + numberFilename);
        } else {
            System.out.println("Файл " + numberFilename + " не найден.");
        }
    }

    public void createNewDictionary(Scanner scanner) {
        System.out.println("Выберите тип нового словаря:");
        System.out.println("1. Буквенный словарь (4 символа латиницы)");
        System.out.println("2. Цифровой словарь (5 цифр)");
        String newDictChoice = scanner.next();
        if (newDictChoice.equals("1")) {
            letterFilename = promptFilename(scanner, "Введите имя файла для нового буквенного словаря:");
            ensureFileExists(letterFilename);
            letterDictionary = new LetterDictionary(letterFilename);
            System.out.println("Новый буквенный словарь создан: " + letterFilename);
        } else if (newDictChoice.equals("2")) {
            numberFilename = promptFilename(scanner, "Введите имя файла для нового цифрового словаря:");
            ensureFileExists(numberFilename);
            numberDictionary = new NumberDictionary(numberFilename);
            System.out.println("Новый цифровой словарь создан: " + numberFilename);
        } else {
            System.out.println("Неверный выбор");
        }
    }

    private String promptFilename(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.println(promptMessage);
            String input = scanner.next();
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Имя файла не может быть пустым.");
        }
    }
}