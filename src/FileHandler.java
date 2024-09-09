

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileHandler {

    public static void processFile(String inputFilePath, String outputFilePath, int key, boolean isEncrypt) {

        if (!Files.isRegularFile(Path.of(inputFilePath))) {
            System.out.println("Файл не найден или введён неверный путь!");
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
            int currentChar;
            while ((currentChar = bufferedReader.read()) != -1) {
                char symbol = (char) currentChar;
                char processedChar = isEncrypt ? CaesarCipher.encrypt(symbol, key) : CaesarCipher.decrypt(symbol, key);
                bufferedWriter.write(processedChar);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтение файла: " + inputFilePath, e);
        }

    }

    public static void processFile(String inputFilePath, String outputFilePath, Scanner scanner) {
        Path path = Path.of(inputFilePath);
        if (!Files.isRegularFile(path)) {
            System.out.println("Файл не найден или введён неверный путь!");
            return;
        }
        try {
            String text = Files.readString(path);
            boolean isFound = false;

            for (int key = 0; key < Alphabet.getALPHABET().length; key++) {
                String decryptedText = CaesarCipher.bruteForceDecrypt(text, key);

                System.out.println("Попробовать ключ " + key + "?");
                System.out.println("Зашифрованный текст:");
                System.out.println(decryptedText);
                System.out.println("Введите 'да', если это правильный текст, или 'нет', чтобы попробовать другой ключ:");

                String userResponse = scanner.nextLine();

                if ("да".equalsIgnoreCase(userResponse)) {
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
                        bufferedWriter.write(decryptedText);
                    }
                    System.out.println("Файл записан");
                    isFound = true;
                    break;
                }

            }
            if (!isFound){
                System.out.println("Ключ не найден");
                return;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
