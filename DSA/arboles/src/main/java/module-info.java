module dsa.uniquindio.arboles {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens dsa.uniquindio.arboles to javafx.fxml;
    opens dsa.uniquindio.arboles.viewController to javafx.fxml;
    exports dsa.uniquindio.arboles;
    exports dsa.uniquindio.arboles.viewController;
    exports dsa.uniquindio.arboles.arbolBinario;
}
