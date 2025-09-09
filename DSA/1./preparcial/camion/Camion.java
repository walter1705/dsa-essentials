package preparcial.camion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;



public class Camion<T extends Canasta>  {
  LinkedList<T> canastas = new LinkedList<>();

  public ArrayList<T> mayorQue() {
    Iterator<T> it = canastas.iterator();
    ArrayList<T> NL = new ArrayList<>();
    while(it.hasNext()) {
      T actual = it.next();
      if(actual.peso < 20) {
        NL.add(actual);
      }
    }

    return NL;
  }
}
