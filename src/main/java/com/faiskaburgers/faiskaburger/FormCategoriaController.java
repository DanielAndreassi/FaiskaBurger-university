package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategoriaDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FormCategoriaController implements Initializable {


    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    void onCancelar(ActionEvent event) {
        tfId.getScene().getWindow().hide();
    }

    @FXML
    void onConfirmar(ActionEvent event) {
        Categoria categoria=new Categoria(tfNome.getText());
        if (TabelaCategoriasController.categoria == null) {
            if (!new CategoriaDAL().gravar(categoria)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar \n" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else{
            categoria.setIdCategoria(Integer.parseInt(tfId.getText()));
            if (!new CategoriaDAL().alterar(categoria)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao alterar \n" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        tfId.getScene().getWindow().hide(); //fechando a janela
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            tfNome.requestFocus();
        });
        Categoria categoria = TabelaCategoriasController.categoria;
        if  (categoria != null) {
            tfId.setText(String.valueOf(categoria.getIdCategoria()));
            tfNome.setText(categoria.getNomeCategoria());
        }
    }
}
