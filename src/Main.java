import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать!");
        System.out.println("Программа занимается шифрованием текста по методу \"Шифр Цезаря\"");
        System.out.println("Выберите что вы хотите сделать:");
        System.out.println("1. Зашифровать текст \n" +
                            "2. Расшифровать текст\n" +
                            "3. Выход");
        Scanner console = new Scanner(System.in);
        int userСhoice = console.nextInt();
        console.nextLine();
        if (userСhoice == 1) {
            boolean isEncrypt = true;

            System.out.println("Хорошо тогда шифруем текст.");
            System.out.println("Напишите путь к файлу где находится ваш текст: ");
            String fileInputName = console.nextLine();

            System.out.println("Напишите путь куда писать шифрованный текст: ");
            String fileOutputName = console.nextLine();

            System.out.println("Напишите на сколько значений сдвинуть: ");
            int key = console.nextInt();

            console.nextLine();

            System.out.println("Приступим!");
            FileHandler.processFile(fileInputName, fileOutputName, key, isEncrypt);

        }

    }
}
