package dsa.uniquindio.arboles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tree-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 1080);
        stage.setTitle("Simulador de Árbol Binario de Búsqueda");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
