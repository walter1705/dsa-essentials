package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.cola;

public class Empresa {
  public Cola<Persona> colaPersonas = new Cola<>();

  public Empresa() {
  }

  public void reverse() {
    if (!colaPersonas.isEmpty()) {
      Persona p = colaPersonas.desencolar();
      reverse();
      colaPersonas.encolar(p);
    }
  }

  // Remover personas por edad
  public void remove(int n) {
    Cola<Persona> colaAux = new Cola<>();

    while(!colaPersonas.isEmpty()) {
      Persona p = colaPersonas.desencolar();
      if(p.getEdad() != n) {
        colaAux.encolar(p);
      }
    }

    colaPersonas = colaAux;
  }
}
