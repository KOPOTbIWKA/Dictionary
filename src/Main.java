import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static void printDictionary(Map<String, String> dictionary) {
        if (dictionary.isEmpty()) {
            System.out.println("Словарь пуст.");
        } else {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    private static void ensureFileExists(String filename) {
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

    private static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String letterFilename = "letter_dictionary.txt";
        String numberFilename = "number_dictionary.txt";

        ensureFileExists(letterFilename);
        ensureFileExists(numberFilename);

        LetterDictionary letterDictionary = new LetterDictionary(letterFilename);
        NumberDictionary numberDictionary = new NumberDictionary(numberFilename);

        while (true) {
            System.out.println("\nТекущие словари:");
            System.out.println("Буквенный словарь: " + letterFilename);
            System.out.println("Цифровой словарь: " + numberFilename);

            System.out.println("\nВыберите словарь:");
            System.out.println("1. Работать с текущим буквенным словарем");
            System.out.println("2. Работать с текущим цифровым словарем");
            System.out.println("3. Выбрать другой буквенный словарь");
            System.out.println("4. Выбрать другой цифровой словарь");
            System.out.println("5. Добавить новый словарь");
            System.out.println("6. Выйти из программы");
            String choice = scanner.next();

            Dictionary dictionary = null;
            switch (choice) {
                case "1":
                    dictionary = letterDictionary;
                    break;
                case "2":
                    dictionary = numberDictionary;
                    break;
                case "3":
                    letterFilename = promptFilename(scanner, "Введите имя файла для другого буквенного словаря:");
                    if (fileExists(letterFilename)) {
                        letterDictionary = new LetterDictionary(letterFilename);
                        System.out.println("Буквенный словарь изменен на: " + letterFilename);
                    } else {
                        System.out.println("Файл " + letterFilename + " не найден.");
                    }
                    continue;
                case "4":
                    numberFilename = promptFilename(scanner, "Введите имя файла для другого цифрового словаря:");
                    if (fileExists(numberFilename)) {
                        numberDictionary = new NumberDictionary(numberFilename);
                        System.out.println("Цифровой словарь изменен на: " + numberFilename);
                    } else {
                        System.out.println("Файл " + numberFilename + " не найден.");
                    }
                    continue;
                case "5":
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
                    continue;
                case "6":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор");
                    continue;
            }

            boolean running = true;

            while (running) {
                System.out.println("\nВыберите действие:");
                System.out.println("1. Просмотреть словарь");
                System.out.println("2. Удалить запись");
                System.out.println("3. Найти запись");
                System.out.println("4. Добавить запись");
                System.out.println("5. Выйти из словаря");
                String action = scanner.next();
                switch (action) {
                    case "1":
                        printDictionary(dictionary.readDictionary());
                        break;
                    case "2":
                        System.out.println("Введите ключ для удаления:");
                        String keyToRemove = scanner.next();
                        dictionary.removeEntry(keyToRemove);
                        break;
                    case "3":
                        System.out.println("Введите ключ для поиска:");
                        String keyToSearch = scanner.next();
                        dictionary.searchEntry(keyToSearch);
                        break;
                    case "4":
                        System.out.println("Введите ключ:");
                        String keyToAdd = scanner.next();
                        System.out.println("Введите перевод:");
                        String valueToAdd = scanner.next();
                        dictionary.addEntry(keyToAdd, valueToAdd);
                        break;
                    case "5":
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор");
                }
            }
        }
    }

    private static String promptFilename(Scanner scanner, String promptMessage) {
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
