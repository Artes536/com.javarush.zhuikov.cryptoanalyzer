

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

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            boolean isFound = false;
            StringBuilder sampleBuilder = new StringBuilder();
            int currentChar;
            int maxSampleSize = 100;
            while ((currentChar = bufferedReader.read()) != -1 && sampleBuilder.length() < maxSampleSize) {
                sampleBuilder.append((char) currentChar);
            }

            String sampleText = sampleBuilder.toString();

            for (int key = 0; key < Alphabet.getALPHABET().length; key++) {
                String decryptedSample = CaesarCipher.bruteForceDecrypt(sampleText, key);

                System.out.println("Попробовать ключ " + key + "?");
                System.out.println("Расшифрованный фрагмент:");
                System.out.println(decryptedSample);
                System.out.println("Введите 'да', если это правильный текст, или 'нет', чтобы попробовать другой ключ:");

                String userResponse = scanner.nextLine();

                if ("да".equalsIgnoreCase(userResponse)) {
                    try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFilePath));
                         BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
                        while ((currentChar = fileReader.read()) != -1) {
                            char symbol = (char) currentChar;
                            char decryptedSymbol = CaesarCipher.decrypt(symbol, key);
                            fileWriter.write(decryptedSymbol);
                        }
                    }
                    System.out.println("Файл записан");
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Ключ не найден");
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + inputFilePath, e);
        }
    }
}
