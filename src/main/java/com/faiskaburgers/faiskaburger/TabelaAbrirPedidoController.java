package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.CategoriaDAL;
import com.faiskaburgers.faiskaburger.database.dal.PedidoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Categoria;
import com.faiskaburgers.faiskaburger.database.entity.Pedido;
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

public class TabelaAbrirPedidoController implements Initializable {

    public static Pedido pedido = null;
    @FXML
    private TableColumn<Pedido.Item, String> coNome;

    @FXML
    private TableColumn<Pedido.Item, Double> coValor;

    @FXML
    private TableView<Pedido> tableView;

    @FXML
    private TextField tfPesquisar;

    @FXML
    void onAlterar(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedIndex() > -1) {
            pedido=tableView.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(pedidosFX.class.getResource("form-pedidos-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Pedido");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            tfPesquisar.setText("");
            carregarTabela("");
            pedido=null;
        }
    }

    @FXML
    void onApagar(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedIndex() > -1) {

            Pedido pedido = tableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar exclusao do produto?");

            if(alert.showAndWait().get() == ButtonType.OK) {
                if(!new PedidoDAL().apagar(pedido)) {
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
    void onPesquisar(KeyEvent event) {
        carregarTabela(tfPesquisar.getText());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coNome.setCellValueFactory(new PropertyValueFactory<>("pedido_cli_nome"));
        carregarTabela("");
    }

    private void carregarTabela(String filtro) {
        PedidoDAL dal = new PedidoDAL();

        if (filtro != null && !filtro.isEmpty()) {
            filtro = "UPPER(pedido_cli_nome) LIKE '%" + filtro.toUpperCase() + "%'";
        } else {
            filtro = ""; // Se filtro for nulo ou vazio, retorna todos os pedidos
        }

        List<Pedido> pedidosList = dal.get(filtro);

        if (pedidosList != null) {
            tableView.setItems(FXCollections.observableArrayList(pedidosList));
        } else {
            tableView.setItems(FXCollections.observableArrayList());
        }
    }


    public void onNovoProduto(ActionEvent actionEvent) throws IOException {
    }
}
