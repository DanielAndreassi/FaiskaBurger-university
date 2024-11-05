package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
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

    }

    @FXML
    void onNovoProduto(ActionEvent event) {

    }

    @FXML
    void onPesquisar(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Abriu");
    }
}
