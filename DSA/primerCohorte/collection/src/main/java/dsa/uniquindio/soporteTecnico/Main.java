package dsa.uniquindio.soporteTecnico;

import java.time.LocalDateTime;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Ticket> tickets = new PriorityQueue<>();

        // Simulamos tickets
        tickets.add(new Ticket("Servidor caído", Severidad.CRITICA, LocalDateTime.now().minusHours(3)));
        tickets.add(new Ticket("Error de login", Severidad.ALTA, LocalDateTime.now().minusHours(5)));
        tickets.add(new Ticket("Consulta de usuario", Severidad.BAJA, LocalDateTime.now().minusDays(1)));
        tickets.add(new Ticket("Problema con impresora", Severidad.MEDIA, LocalDateTime.now().minusHours(2)));
        tickets.add(new Ticket("Base de datos lenta", Severidad.CRITICA, LocalDateTime.now().minusHours(1)));

        // Atender tickets en orden
        while (!tickets.isEmpty()) {
            System.out.println("Atendiendo: " + tickets.poll());
        }
    }
}