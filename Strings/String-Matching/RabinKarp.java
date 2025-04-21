public class RabinKarp {
    static int base = 10;

    static int computeHash(String s) {
        int hash = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            hash += (s.charAt(i) - 'a') * Math.pow(base, len - i - 1);
        }
        return hash;
    }

    static int rehash(int oldHash, char oldChar, char newChar, int len) {
        int removed = (oldChar - 'a') * (int) Math.pow(base, len - 1);
        return (oldHash - removed) * base + (newChar - 'a');
    }

    static boolean contains(String text, String pattern) {
        int m = pattern.length(), n = text.length();
        if (m > n) return false;

        int patternHash = computeHash(pattern);
        int currentHash = computeHash(text.substring(0, m));

        if (patternHash == currentHash && text.startsWith(pattern)) return true;

        for (int i = 1; i <= n - m; i++) {
            currentHash = rehash(currentHash, text.charAt(i - 1), text.charAt(i + m - 1), m);
            if (currentHash == patternHash && text.substring(i, i + m).equals(pattern)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String text = "ahfidggfjgabcafjf";
        String pattern = "abc";
        System.out.println(contains(text, pattern));
    }
}
