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

            System.out.println("Напишите путь куда писать расшифрованный текст: ");
            String fileOutputName = console.nextLine();

            System.out.println("Напишите на сколько значений сдвинуть: ");
            int key = console.nextInt();

            console.nextLine();

            System.out.println("Файл записан");
            FileHandler.processFile(fileInputName, fileOutputName, key, isEncrypt);

        } else if (userСhoice == 2) {
            boolean isEncrypt = false;

            System.out.println("Хорошо тогда расшифруем текст.");
            System.out.println("Напишите путь к файлу где находится ваш текст: ");
            String fileInputName = console.nextLine();

            System.out.println("Напишите путь куда писать расшифрованный текст: ");
            String fileOutputName = console.nextLine();
            System.out.println("Вы знаете на сколько значений сдвинут текст? \n" +
                    "1. Да \n" +
                    "2. Нет");
            int knownKey = console.nextInt();
            if (knownKey == 2) {
                System.out.println("Ничего страшного." +
                        "Тогда помогите мне расшифровать ваш текст.");
                System.out.println("Приступим!");
                FileHandler.processFile(fileInputName, fileOutputName, console);
            } else if (knownKey == 1) {
                System.out.println("Напишите на сколько значений он сдвинут: ");
                int key = console.nextInt();

                console.nextLine();

                System.out.println("Приступим!");
                FileHandler.processFile(fileInputName, fileOutputName, key, isEncrypt);
            }

        } else if (userСhoice == 3) {
            return;
        }
        else {
            return;
        }
    }
}
//C:\Users\Семён\Desktop\newFile.txt
//C:\Users\Семён\Desktop\sampler.txt