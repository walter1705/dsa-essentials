package preparcial.camion;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    // Create instances of Canasta
    Canasta c1 = new Canasta(Integer.valueOf(15), Double.valueOf(18.0));
    Canasta c2 = new Canasta(Integer.valueOf(25), Double.valueOf(20.0));
    Canasta c3 = new Canasta(Integer.valueOf(18), Double.valueOf(10.0));
    Canasta c4 = new Canasta(Integer.valueOf(30), Double.valueOf(40.0));

    // Create a Camion and add the canastas
    Camion<Canasta> camion = new Camion<>();
    camion.canastas.add(c1);
    camion.canastas.add(c2);
    camion.canastas.add(c3);
    camion.canastas.add(c4);

    // Get canastas with weight less than 20
    ArrayList<Canasta> livianas = camion.mayorQue();
    System.out.println("Canastas con peso menor a 20:");
    for (Canasta c : livianas) {
      System.out.println(c);
    }
  }
}
