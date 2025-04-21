package Strings.Palindrome;

public class Manacher {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // Transform the string to avoid even length palindromes issue
        StringBuilder transformed = new StringBuilder();
        transformed.append("#");
        for (int i = 0; i < s.length(); i++) {
            transformed.append(s.charAt(i));
            transformed.append("#");
        }

        int n = transformed.length();
        int[] P = new int[n];  // Array to store lengths of palindromes
        int C = 0, R = 0;      // Center and right edge of the palindrome

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i;  // Find the mirror index of i

            if (i < R) {
                P[i] = Math.min(R - i, P[mirror]);
            }

            // Try to expand the palindrome centered at i
            while (i + P[i] + 1 < n && i - P[i] - 1 >= 0 && transformed.charAt(i + P[i] + 1) == transformed.charAt(i - P[i] - 1)) {
                P[i]++;
            }

            // Update C and R if the palindrome centered at i expands beyond R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Find the longest palindrome
        int maxLength = 0, centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLength) {
                maxLength = P[i];
                centerIndex = i;
            }
        }

        // Extract the longest palindrome from the original string
        int start = (centerIndex - maxLength) / 2;  // start index in the original string
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String s = "ababa";
        System.out.println("Longest Palindrome: " + longestPalindrome(s));  // Output: "ababa"
    }
}
