package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategoriaDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.util.SingletonDB;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TabelaCategoriasController implements Initializable {

    public static Categoria categoria=null;
    @FXML
    private TableColumn<Categoria, String> coNome;

    @FXML
    private TableView<Categoria> tableView;

    @FXML
    private TextField tfPesquisar;

    @FXML
    void onAlterar(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedIndex() > -1) {
            categoria=tableView.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(pedidosFX.class.getResource("tabela-categorias-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Categoria");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            tfPesquisar.setText("");
            carregarTabela("");
            categoria=null;
        }
    }

    @FXML
    void onApagar(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedIndex() > -1) {

            Categoria categoria = tableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar exclusao do produto?");

            if(alert.showAndWait().get() == ButtonType.OK) {
                if(!new CategoriaDAL().apagar(categoria)) {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro ao apagar \n"+ SingletonDB.getConexao().getMensagemErro());
                    alert.showAndWait();
                }
                else
                    carregarTabela("");
            }
        }
    }

    @FXML
    void onNovaCategoria(ActionEvent event) throws IOException {
        tfPesquisar.getScene().getWindow().setOpacity(0.2);
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(pedidosFX.class.getResource("form-categoria-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Categorias...");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        tfPesquisar.getScene().getWindow().setOpacity(1);
        tfPesquisar.setText("");
        carregarTabela("");
    }

    @FXML
    void onPesquisar(KeyEvent event) {
        carregarTabela(tfPesquisar.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coNome.setCellValueFactory(new PropertyValueFactory<>("nomeCategoria"));
        carregarTabela("");
    }

    private void carregarTabela(String filtro) {
        CategoriaDAL dal = new CategoriaDAL();

        if(!filtro.isEmpty()) {
            filtro = " upper(cat_nome) LIKE '%"
                    + filtro.toUpperCase()+"%'";
        }
        List<Categoria> categoriaList = dal.get(filtro);
        tableView.setItems(FXCollections.observableArrayList(categoriaList));

    }
}
