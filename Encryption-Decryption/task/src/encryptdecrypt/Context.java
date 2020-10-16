package encryptdecrypt;

interface EncodingDecodingAlgorithm {

    String encode(String text, int key);
    String decode(String text, int key);
}

class Shift implements EncodingDecodingAlgorithm {

    @Override
    public String encode(String text, int key) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < textChars.length; i++) {
            int c = textChars[i];
            if (textChars[i] >= 65 && textChars[i] <= 90) {
                c += key;
                if (c > 90) c = 64 + (c % 90);
            }
            if (textChars[i] >= 97 && textChars[i] <= 122) {
                c += key;
                if (c > 122) c = 96 + (c % 122);
            }
            textChars[i] = (char) c;
        }
        return new String(textChars);
    }

    @Override
    public String decode(String text, int key) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < textChars.length; i++) {
            int c = textChars[i];
            if (textChars[i] >= 65 && textChars[i] <= 90) {
                c -= key;
                if (c < 65) c = 91 - (65 % c);
            }
            if (textChars[i] >= 97 && textChars[i] <= 122) {
                c -= key;
                if (c < 97) c = 123 - (97 % c);
            }
            textChars[i] = (char) c;
        }
        return new String(textChars);
    }
}

class Unicode implements EncodingDecodingAlgorithm {

    @Override
    public String encode(String text, int key) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < textChars.length; i++) {
            int c = textChars[i] + key;
            textChars[i] = (char) c;
        }
        return new String(textChars);
    }

    @Override
    public String decode(String text, int key) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < textChars.length; i++) {
            int c = textChars[i] - key;
            textChars[i] = (char) c;
        }
        return new String(textChars);
    }
}

public class Context {

    private EncodingDecodingAlgorithm method;

    public void setMethod(EncodingDecodingAlgorithm method) {
        this.method = method;
    }

    public String encode(String text, int key) {
        return this.method.encode(text, key);
    }

    public String decode(String text, int key) {
        return this.method.decode(text, key);
    }
}
