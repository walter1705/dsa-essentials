package dsa.uniquindio.arboles.viewController;

import dsa.uniquindio.arboles.arbolBinario.Tree;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class TreeViewController {
    
    @FXML
    private TextField inputField;
    
    @FXML
    private TextArea outputArea;
    
    @FXML
    private Pane treeCanvas;
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Label sizeLabel;
    
    @FXML
    private Label heightLabel;
    
    private Tree<Integer> tree;
    
    @FXML
    public void initialize() {
        tree = new Tree<>();
        updateLabels();
        outputArea.setEditable(false);
    }
    
    @FXML
    private void handleInsert() {
        try {
            Integer value = Integer.parseInt(inputField.getText().trim());
            tree.insert(value);
            statusLabel.setText("Insertado: " + value);
            statusLabel.setStyle("-fx-text-fill: green;");
            inputField.clear();
            updateLabels();
            visualizeTree();
        } catch (NumberFormatException e) {
            showError("Por favor ingrese un número válido");
        }
    }
    
    @FXML
    private void handleSearch() {
        try {
            Integer value = Integer.parseInt(inputField.getText().trim());
            boolean found = tree.search(value);
            if (found) {
                statusLabel.setText("Elemento " + value + " encontrado");
                statusLabel.setStyle("-fx-text-fill: green;");
            } else {
                statusLabel.setText("Elemento " + value + " NO encontrado");
                statusLabel.setStyle("-fx-text-fill: orange;");
            }
        } catch (NumberFormatException e) {
            showError("Por favor ingrese un número válido");
        }
    }
    
    @FXML
    private void handleDelete() {
        try {
            Integer value = Integer.parseInt(inputField.getText().trim());
            tree.delete(value);
            statusLabel.setText("Eliminado: " + value);
            statusLabel.setStyle("-fx-text-fill: red;");
            inputField.clear();
            updateLabels();
            visualizeTree();
        } catch (NumberFormatException e) {
            showError("Por favor ingrese un número válido");
        }
    }
    
    @FXML
    private void handleInOrder() {
        outputArea.clear();
        if (tree.isEmpty()) {
            outputArea.setText("El árbol está vacío");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido InOrder (Izq-Raíz-Der):\n");
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        tree.inOrder();
        
        System.out.flush();
        System.setOut(old);
        sb.append(baos.toString());
        outputArea.setText(sb.toString());
    }
    
    @FXML
    private void handlePreOrder() {
        outputArea.clear();
        if (tree.isEmpty()) {
            outputArea.setText("El árbol está vacío");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido PreOrder (Raíz-Izq-Der):\n");
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        tree.preOrder();
        
        System.out.flush();
        System.setOut(old);
        sb.append(baos.toString());
        outputArea.setText(sb.toString());
    }
    
    @FXML
    private void handlePostOrder() {
        outputArea.clear();
        if (tree.isEmpty()) {
            outputArea.setText("El árbol está vacío");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Recorrido PostOrder (Izq-Der-Raíz):\n");
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        tree.postOrder();
        
        System.out.flush();
        System.setOut(old);
        sb.append(baos.toString());
        outputArea.setText(sb.toString());
    }
    
    @FXML
    private void handleFindMin() {
        try {
            Integer min = tree.findMin();
            outputArea.setText("Valor mínimo: " + min);
            statusLabel.setText("Mínimo encontrado: " + min);
            statusLabel.setStyle("-fx-text-fill: blue;");
        } catch (RuntimeException e) {
            outputArea.setText("El árbol está vacío");
        }
    }
    
    @FXML
    private void handleFindMax() {
        try {
            Integer max = tree.findMax();
            outputArea.setText("Valor máximo: " + max);
            statusLabel.setText("Máximo encontrado: " + max);
            statusLabel.setStyle("-fx-text-fill: blue;");
        } catch (RuntimeException e) {
            outputArea.setText("El árbol está vacío");
        }
    }
    
    @FXML
    private void handlePrintTree() {
        outputArea.clear();
        if (tree.isEmpty()) {
            outputArea.setText("El árbol está vacío");
            return;
        }
        
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        tree.printTree();
        
        System.out.flush();
        System.setOut(old);
        outputArea.setText(baos.toString());
    }
    
    @FXML
    private void handleClear() {
        tree.clear();
        treeCanvas.getChildren().clear();
        outputArea.clear();
        statusLabel.setText("Árbol limpiado");
        statusLabel.setStyle("-fx-text-fill: gray;");
        updateLabels();
    }
    
    private void updateLabels() {
        sizeLabel.setText("Tamaño: " + tree.size());
        heightLabel.setText("Altura: " + (tree.isEmpty() ? "N/A" : tree.height()));
    }
    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
    }
    
    private void visualizeTree() {
        treeCanvas.getChildren().clear();
        if (tree.isEmpty()) {
            return;
        }
        
        double canvasWidth = treeCanvas.getWidth();
        double startX = canvasWidth / 2;
        double startY = 40;
        
        drawNode(tree.getRoot(), startX, startY, canvasWidth / 4, 60);
    }
    
    private void drawNode(dsa.uniquindio.arboles.arbolBinario.Node<Integer> node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;
        
        // Dibujar líneas a los hijos
        if (node.getLeft() != null) {
            double leftX = x - xOffset;
            double leftY = y + yOffset;
            Line line = new Line(x, y, leftX, leftY);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(2);
            treeCanvas.getChildren().add(line);
            drawNode(node.getLeft(), leftX, leftY, xOffset / 2, yOffset);
        }
        
        if (node.getRight() != null) {
            double rightX = x + xOffset;
            double rightY = y + yOffset;
            Line line = new Line(x, y, rightX, rightY);
            line.setStroke(Color.GRAY);
            line.setStrokeWidth(2);
            treeCanvas.getChildren().add(line);
            drawNode(node.getRight(), rightX, rightY, xOffset / 2, yOffset);
        }
        
        // Dibujar el nodo
        Circle circle = new Circle(x, y, 20);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.DARKBLUE);
        circle.setStrokeWidth(2);
        
        Text text = new Text(x, y + 5, node.getValue().toString());
        text.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        text.setX(x - text.getLayoutBounds().getWidth() / 2);
        
        treeCanvas.getChildren().addAll(circle, text);
    }
}
