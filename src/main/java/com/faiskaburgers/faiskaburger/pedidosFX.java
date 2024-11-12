package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.util.SingletonDB;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class pedidosFX extends Application {
    @Override
    public void start (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(pedidosFX.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320,240);
        stage.setTitle("Faiskas Burgers");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        if(!SingletonDB.conectar()) {
            JOptionPane.showConfirmDialog(null,"Erro ao conectar " + SingletonDB.getConexao().getMensagemErro());
            Platform.exit();
        }
        launch();
    }
}
