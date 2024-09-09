

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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


}
