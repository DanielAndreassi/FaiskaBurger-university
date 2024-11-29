package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategoriaDAL;
import com.faiskaburgers.faiskaburger.database.dal.ProdutoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;
import com.faiskaburgers.faiskaburger.util.MaskFieldUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormProdutosController implements Initializable {
    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextArea taDescr;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;

    @FXML
    void onCancelar(ActionEvent event) {
        taDescr.getScene().getWindow().hide(); //fechando a janela
    }

    @FXML
    void onConfirmar(ActionEvent event) {
        double valor=Double.parseDouble(tfValor.getText().replace(",","."));
        Produto produto=new Produto(tfNome.getText(),taDescr.getText(),
                valor,cbCategoria.getSelectionModel().getSelectedItem());
        if (TabelaProdutosController.produto == null) {
            if (!new ProdutoDAL().gravar(produto)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar \n" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        else{
            produto.setIdProduto(Integer.parseInt(tfId.getText()));
            if (!new ProdutoDAL().alterar(produto)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao alterar \n" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
        }
        taDescr.getScene().getWindow().hide(); //fechando a janela
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MaskFieldUtil.monetaryField(tfValor);
        Platform.runLater(()->{
            tfNome.requestFocus();
        });
        carregarCategorias();
        if(TabelaProdutosController.produto!=null)
        {
            //PREENCHER OS INPUTS
            Produto produto=TabelaProdutosController.produto;
            tfId.setText(""+produto.getIdProduto());
            tfNome.setText(produto.getNomeProduto());
            taDescr.setText(produto.getDescricaoProduto());
            tfValor.setText(""+produto.getValorProduto()*10);
            cbCategoria.getSelectionModel().select(produto.getCategoria());
        }
    }

    private void carregarCategorias() {
        List<Categoria> categoriaList;
        categoriaList=new CategoriaDAL().get("");
        cbCategoria.setItems(FXCollections.observableArrayList(categoriaList));
        cbCategoria.getSelectionModel().select(0); //mostra o primeiro elemento
    }
}
