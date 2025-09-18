import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.nextLine();
    int m = scanner.nextInt();
    scanner.nextLine();
    ArrayList<Integer> arr = Arrays.stream(scanner.nextLine().split(" "))
        .map(Integer::parseInt)
        .collect(Collectors.toCollection(ArrayList::new));
    scanner.close();
    HashMap<Integer, String> a = new HashMap<>();
    Iterator<Map.Entry<Integer,String>> it = a.entrySet().iterator();
    System.out.println(method(str, m, arr));
  }

  public static String method(String str, int m, ArrayList<Integer> values) {
    int lengthStr = str.length();
    StringBuilder builder = new StringBuilder(str);
    int[] arr = new int[values.size()];
    for (int i = 0; i < m; i++) {
      int start = values.get(i);
      if (arr[i] == start) {
        arr[i] = 0;
        continue;
      }
      arr[i] = start;
      int end = lengthStr - start + 1;
      String reversedStr = reverse(builder.substring(start - 1, end), "");
      builder.replace(start - 1, end, reversedStr);
    }
    return builder.toString();
  }

  private static String reverse(String substring, String nwstring) {
    if (substring.isEmpty())
      return nwstring;
    nwstring = nwstring + substring.charAt(substring.length() - 1);
    return reverse(substring.substring(0, substring.length() - 1), nwstring);
  }
}
