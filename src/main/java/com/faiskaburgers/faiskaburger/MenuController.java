package com.faiskaburgers.faiskaburger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    void onAbrirProduto(ActionEvent event) {

    }

    @FXML
    void onAjudaSistema(ActionEvent event) {

    }

    @FXML
    void onAjudaSobre(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Faiska Burger System");
        alert.setContentText("Desenvolvido por FIPP sistemas\n2024");
        alert.show();
    }

    @FXML
    void onCadCategoria(ActionEvent event) {

    }

    @FXML
    void onCadProduto(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(pedidosFX.class.getResource("tabela-produtos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320,240);
        stage.setTitle("Cadastro de produtos");
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void onCadTipoPagamento(ActionEvent event) {

    }

    @FXML
    void onPedidoNovo(ActionEvent event) {

    }

    @FXML
    void onRelCardapio(ActionEvent event) {

    }

    @FXML
    void onRelPedidoPeriodo(ActionEvent event) {

    }

    @FXML
    void onRelProdutos(ActionEvent event) {

    }

    @FXML
    void onSair(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deseja realmente sair?");
        if(alert.showAndWait().get() == ButtonType.OK)
            Platform.exit();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
