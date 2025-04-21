public class KMP {
    static int[] lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int i = 0;
        int j = 1;
        while(j < n) {
            if(s.charAt(i) == s.charAt(j)) {
                i++;
                j++;
                lps[j] = i;
            }
            else {
                if(i==0) j++;
                else i = lps[i-1];
            }
        }
        return lps;
    }
    static boolean contains(String text, String pattern) {
        int[] lps = lps(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return true;  // match found
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];  // fall back using lps
                } else {
                    i++;  // move forward in text
                }
            }
        }

        return false;  // no match found
    }
    public static void main(String[] args) {
        String text = "ahfidggfjgabcfjf";
        String pattern = "abc";
        System.out.println(contains(text, pattern));
    }
}