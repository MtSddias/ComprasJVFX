package com.example.comprasjvfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.IOException;

public class HelloApplication extends Application {
    private ArrayList<String> listaCompras = new ArrayList<>();
    private ListView<String> listaVisu = new ListView<>();

    @Override
    public void start(Stage stage) throws IOException {
        TextField descItem = new TextField();
        Button botaoAdd = new Button("Add: ");
        Button botaoExport = new Button("Exportar lista: ");
        Button botaqqr= new Button();

        Label labelAdd = new Label("Digite o que deseja adicionar: ");
        Label labellistaCompras = new Label("Lista de compras: ");

        ObservableList<String> observableListCompras = FXCollections.observableArrayList(listaCompras);
        listaVisu.setItems(observableListCompras);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelAdd, descItem, botaoAdd, labellistaCompras, listaVisu, botaoExport);

        botaoAdd.setOnAction(e -> {
            String item = descItem.getText();
            if (!item.isEmpty()) {
                listaCompras.add(item);
                listaVisu.getItems().add(item);
                descItem.clear();


            }
        });

        botaoExport.setOnAction(e -> {
            try {
                File arquivo = new File("listaCompras.txt");
                PrintWriter writer = new PrintWriter(arquivo);
                for (String item : listaCompras) {
                    writer.println(item);
                }
                writer.close();
            } catch (Exception ex) {
                System.out.println("Erro ocorrido " + ex.getMessage());
            }


        });

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(vBox, 350, 300);
        stage.setTitle("Lista de compras!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}