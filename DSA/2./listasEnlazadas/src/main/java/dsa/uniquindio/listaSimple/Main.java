package dsa.uniquindio.listaSimple;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
