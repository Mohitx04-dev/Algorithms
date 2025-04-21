package Strings.SuffixArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SuffixArray {
    String s;
    private Integer[] suffix;
    private int[] lcp;
    public SuffixArray(String s) {
        this.s = s;
        buildSuffixArray();
        buildLCPArray();
    }
    private void buildSuffixArray() {
        int n = s.length();
        suffix = new Integer[n];
        for(int i=0;i<s.length();i++) {
            suffix[i] = i;
        }
        Arrays.sort(suffix, (a, b)->(s.substring(a).compareTo(s.substring(b))));
        System.out.println(Arrays.toString(suffix));
    }
    private void buildLCPArray() {  
        int n = s.length();
        int[] rank = new int[n];
        lcp = new int[n];
        for(int i=0;i<n;i++) {
            rank[suffix[i]] = i;
        }
        int m = 0; // matching
        for(int i=0;i<n;i++) {
            if(rank[i] > 0) {
                int j = suffix[rank[i]-1];
                while(i + m < n && j + m < n && s.charAt(i+m) == s.charAt(j+m)) m++;
                lcp[rank[i]] = m;
                if(m>0) m--;
            }
        }
        System.out.println(Arrays.toString(lcp));
    }
}

public class Main {
    public static void main(String[] args) {
        String t = "banana";
        SuffixArray sa = new SuffixArray(t);
    
    }
}
