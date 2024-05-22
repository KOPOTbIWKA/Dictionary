import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LetterDictionary letterDictionary = new LetterDictionary("letter_dictionary.txt");
        NumberDictionary numberDictionary = new NumberDictionary("number_dictionary.txt");

        System.out.println("Выберите язык словаря:");
        System.out.println("1. Словарь с 4 символами латиницы");
        System.out.println("2. Словарь с 5 символами цифр");
        int choice = scanner.nextInt();

        Dictionary dictionary = null;
        switch (choice) {
            case 1:
                dictionary = letterDictionary;
                break;
            case 2:
                dictionary = numberDictionary;
                break;
            default:
                System.out.println("Неверный выбор");
                System.exit(0);
        }

        boolean running = true;

        while (running) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть словарь");
            System.out.println("2. Удалить запись");
            System.out.println("3. Найти запись");
            System.out.println("4. Добавить запись");
            System.out.println("5. Выйти");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    printDictionary(dictionary.readDictionary());
                    break;
                case 2:
                    System.out.println("Введите ключ для удаления:");
                    String keyToRemove = scanner.next();
                    dictionary.removeEntry(keyToRemove);
                    break;
                case 3:
                    System.out.println("Введите ключ для поиска:");
                    String keyToSearch = scanner.next();
                    String translation = dictionary.searchEntry(keyToSearch);
                    if (translation != null) {
                        System.out.println("Перевод: " + translation);
                    } else {
                        System.out.println("Запись не найдена");
                    }
                    break;
                case 4:
                    System.out.println("Введите ключ:");
                    String keyToAdd = scanner.next();
                    System.out.println("Введите перевод:");
                    String valueToAdd = scanner.next();
                    dictionary.addEntry(keyToAdd, valueToAdd);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private static void printDictionary(Map<String, String> dictionary) {
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
