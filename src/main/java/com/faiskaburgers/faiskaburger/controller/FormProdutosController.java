package com.faiskaburgers.faiskaburger.controller;

import com.faiskaburgers.faiskaburger.database.dal.CategorialDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FormProdutosController implements Initializable {

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private Label taDesc;

    @FXML
    private TextField tfId;

    @FXML
    private Label tfNome;

    @FXML
    private Label tfValor;

    @FXML
    void onCancelar(ActionEvent event) {

    }

    @FXML
    void onConfirmar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        MaskFieldUtil.monetaryField(tfValor);
        Platform.runLater(()-> {
            tfNome.requestFocus();
        });
        carregarCategotias();
    }

    private void carregarCategotias() {
        List<Categoria> categoriaList;
        categoriaList = new CategorialDAL().get("");
        cbCategoria.getItems().addAll(categoriaList);

    }
}
