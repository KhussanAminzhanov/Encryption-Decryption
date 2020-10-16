public class Main {

    public static void main(String[] args) {
        int count = 0;
        for (Secret value : Secret.values()) {
            char[] chars = value.name().toCharArray();
            if (chars.length < 4) continue;
            if (chars[0] == 'S'
                    && chars[1] == 'T'
                    && chars[2] == 'A'
                    && chars[3] == 'R')
                count++;
        }
        System.out.println(count);
    }
}

//enum Secret {
//    START, CRASH, STARTCRASH, CRASHSTART, STARTKING;
//}

/* At least two constants start with STAR
enum Secret {
    STAR, CRASH, START, // ...
}
*/