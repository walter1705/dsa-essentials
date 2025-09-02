package dsa.uniquindio.navegacion;

public class Main {
public static void main(String[] args) {
        Navegador nav = new Navegador();

        nav.visitar("google.com");
        nav.visitar("youtube.com");
        nav.visitar("openai.com");

        nav.atras();     // youtube.com
        nav.atras();     // google.com
        nav.adelante();  // youtube.com

        nav.visitar("github.com"); // limpia pilaAdelante
        nav.adelante();  // no hay páginas adelante
    }
}

