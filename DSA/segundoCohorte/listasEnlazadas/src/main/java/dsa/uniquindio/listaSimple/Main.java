package DSA.segundoCohorte.listasEnlazadas.src.main.java.dsa.uniquindio.listaSimple;


public class Main {
    public static void main(String[] args) {
    
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.print();
        list.reverse();
        
        list.print();

    }


}
