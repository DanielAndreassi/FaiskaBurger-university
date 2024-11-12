package com.faiskaburgers.faiskaburger.controller;

import com.faiskaburgers.faiskaburger.database.dal.ProdutoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.pedidosFX;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TabelaProdutosController implements Initializable {

    @FXML
    private TableColumn<Produto, Categoria> coCategoria;

    @FXML
    private TableColumn<Produto, String> coNome;

    @FXML
    private TableColumn<Produto, String> coValor;

    @FXML
    private TableView<Produto> tableView;

    @FXML
    private TextField tfPesquisar;

    @FXML
    void onAlterar(ActionEvent event) {

    }

    @FXML
    void onApagar(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedIndex() > -1) {

            Produto produto = tableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirmar exclusao do produto?");
            if(alert.showAndWait().get() == ButtonType.OK) {
                new ProdutoDAL().apagar(produto);
            }
        }
    }

    @FXML
    void onNovoProduto(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(pedidosFX.class.getResource("form-produtos-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),320,240);
        stage.setTitle("Produtos...");
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void onPesquisar(KeyEvent event) {
        carregarTabela(tfPesquisar.getText());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        coValor.setCellValueFactory(new PropertyValueFactory<>("valorProduto"));
        coCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        carregarTabela("");
    }

    private void carregarTabela(String filtro) {
        ProdutoDAL dal = new ProdutoDAL();

        if(!filtro.isEmpty()) {
            filtro = " upper(produto_nome) LIKE '%"
                    + filtro.toUpperCase()+"%'";
        }
        List<Produto> produtoList = dal.get(filtro);
        tableView.setItems(FXCollections.observableArrayList(produtoList));

    }
}
