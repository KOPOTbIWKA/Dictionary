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

    private static String promptFilename(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.println(promptMessage);
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            }
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Имя файла не может быть пустым.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String letterFilename = promptFilename(scanner, "Введите имя файла для словаря с 4 символами латиницы ('exit' для выхода):");
            String numberFilename = promptFilename(scanner, "Введите имя файла для словаря с 5 символами цифр ('exit' для выхода):");

            LetterDictionary letterDictionary = new LetterDictionary(letterFilename);
            NumberDictionary numberDictionary = new NumberDictionary(numberFilename);

            while (true) {
                System.out.println("\nВыберите словарь:");
                System.out.println("1. Словарь с 4 символами латиницы");
                System.out.println("2. Словарь с 5 символами цифр");
                System.out.println("3. Выйти из программы");
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
                            String translation = dictionary.searchEntry(keyToSearch);
                            if (translation != null) {
                                System.out.println("Перевод: " + translation);
                            } else {
                                System.out.println("Запись не найдена");
                            }
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
                            continue;
                        default:
                            System.out.println("Неверный выбор");
                    }
                }
            }
        }
    }
}
