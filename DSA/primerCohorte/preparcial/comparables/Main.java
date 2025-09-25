package preparcial.comparables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> waiwaiwaiwaiwaiwaiwaitt = new ArrayList<>(List.of(1, 2, 3, 4));
    System.out.println(maxElem(waiwaiwaiwaiwaiwaiwaitt));

    
  }
  



  public static <T extends Comparable<? super T>> T maxElem(List<T> comparar) {
    Collections.sort(comparar);
    return comparar.get(comparar.size() - 2);
  }
}

