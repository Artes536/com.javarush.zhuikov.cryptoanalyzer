import java.util.Arrays;

public class CaesarCipher {


    public static char encrypt(char symbol, int key) {
        char[] alphabet = Alphabet.getALPHABET();
        int length = alphabet.length;
        int newIndex;
        int index = -1;

        for (int i = 0; i < length; i++) {
            if (alphabet[i] == symbol) {
                index = i;
                break;
            }
        }
        newIndex = (index + key) % length;
        if (newIndex < 0) {
            newIndex += length;
        }

        return alphabet[newIndex];
    }

    public static char decrypt(char symbol, int key) {
        // Логика расшифровки
        char[] alphabet = Alphabet.getALPHABET();
        int length = alphabet.length;
        int newIndex;
        int index = -1;

        for (int i = 0; i < length; i++) {
            if (alphabet[i] == symbol) {
                index = i;
                break;
            }
        }
        newIndex = (index - key) % length;
        if (newIndex < 0) {
            newIndex += length;
        }

        return alphabet[newIndex];
    }

    public static String bruteForceDecrypt(String text) {
        // Перебор всех ключей для расшифровки
        return "";
    }
}
