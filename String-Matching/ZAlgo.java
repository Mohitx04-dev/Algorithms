import java.util.Arrays;

public class ZAlgo {
    static int[] zArr(String t) {
        int r=0, l=0;
        int n = t.length();
        int[] z = new int[n];
        for(int i=1;i<n;i++) {
            if(i <= r) {
                z[i] = Math.min(z[i-l],r-i+1);
            }
            while(i + z[i] < n && t.charAt(z[i])==t.charAt(z[i]+i)) {
                z[i]++;
            }
            if(z[i]>r) {
                l = i;
                r = i+z[i]-1;
            }
        } 
        return z;
    }
    static boolean contains(String str, String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append(pattern);
        sb.append("$");
        sb.append(str);
        int[] z = zArr(sb.toString());
        return Arrays.stream(z).filter(x -> x==pattern.length()).count() > 0;
    }
    public static void main(String[] args) {
        String text = "ahfidggfjgabcfjf";
        String pattern = "abc";
        System.out.println(contains(text, pattern));
    }
}
