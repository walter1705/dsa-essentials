package dsa.uniquindio.controlAcceso;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> ids = new HashSet<>();

        String id1 = "123";
        String id2 = "NO REPETIDA";
        String id3 = "123";

        ids.add(id1);
        ids.add(id2);
        ids.add(id3);
        for(String i : ids) {
            System.out.println(i);
        }

        //Para que verificar si ya existe, si SET no permite duplicados???????????????
    }
}
