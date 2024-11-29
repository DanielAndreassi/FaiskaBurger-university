package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.TipoPagamentoDAL;
import com.faiskaburgers.faiskaburger.database.entity.TipoPagamento;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FormMetodoDePagamentoController implements Initializable {

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
        TipoPagamento tipoPagamento=new TipoPagamento(tfNome.getText());
        if (TabelaTpPagamentoController.tipoPagamento == null) {
            if (!new TipoPagamentoDAL().gravar(tipoPagamento)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar \n" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else{
            tipoPagamento.setIdTipoPagamento(Integer.parseInt(tfId.getText()));
            if (!new TipoPagamentoDAL().alterar(tipoPagamento)) {

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
        TipoPagamento tipoPagamento = TabelaTpPagamentoController.tipoPagamento;
        if  (tipoPagamento != null) {
            tfId.setText(String.valueOf(tipoPagamento.getIdTipoPagamento()));
            tfNome.setText(tipoPagamento.getTipoPagamento());
        }
    }
}
