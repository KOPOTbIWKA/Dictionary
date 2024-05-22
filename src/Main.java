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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileManager fileManager = new FileManager();

        while (true) {
            System.out.println("\nТекущие словари:");
            System.out.println("Буквенный словарь: " + fileManager.getLetterFilename());
            System.out.println("Цифровой словарь: " + fileManager.getNumberFilename());

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
                    dictionary = fileManager.getLetterDictionary();
                    break;
                case "2":
                    dictionary = fileManager.getNumberDictionary();
                    break;
                case "3":
                    fileManager.chooseLetterDictionary(scanner);
                    continue;
                case "4":
                    fileManager.chooseNumberDictionary(scanner);
                    continue;
                case "5":
                    fileManager.createNewDictionary(scanner);
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
}