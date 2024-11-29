package com.faiskaburgers.faiskaburger;

import com.faiskaburgers.faiskaburger.database.dal.PedidoDAL;
import com.faiskaburgers.faiskaburger.database.dal.ProdutoDAL;
import com.faiskaburgers.faiskaburger.database.dal.TipoPagamentoDAL;
import com.faiskaburgers.faiskaburger.database.entity.Pedido;
import com.faiskaburgers.faiskaburger.database.entity.Produto;
import com.faiskaburgers.faiskaburger.database.entity.TipoPagamento;
import com.faiskaburgers.faiskaburger.util.MaskFieldUtil;
import com.faiskaburgers.faiskaburger.util.ModalTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FormPedidosController implements Initializable {

    @FXML
    private Button btProduto;

    @FXML
    private ComboBox<TipoPagamento> cbTipoPagamento;

    @FXML
    private TableColumn<Pedido.Item, String> coProduto;

    @FXML
    private TableColumn<Pedido.Item, String> coQuantidade;

    @FXML
    private TableColumn<Pedido.Item, String> coValor;

    @FXML
    private DatePicker dpData;

    @FXML
    private Label lbTotal;

    @FXML
    private CheckBox rbViagem;

    @FXML
    private Spinner<Integer> spQuantidade;

    @FXML
    private TableView<Pedido.Item> tableView;

    @FXML
    private TextField tfCliente;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfTelefone;

    private Produto produto = null;

    @FXML
    void onAdicionar(ActionEvent event) {
        Pedido.Item item = new Pedido.Item(produto,spQuantidade.getValue(),produto.getValorProduto());
        tableView.getItems().add(item);
        btProduto.setText("Selecione um item");
        spQuantidade.getEditor().setText("1");
    }

    @FXML
    void onCancelar(ActionEvent event) {
        tfID.getScene().getWindow().hide();
    }

    @FXML
    void onConfirmar(ActionEvent event) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(dpData.getValue());
        pedido.setFoneCliente(tfTelefone.getText());
        pedido.setNomeCliente(tfCliente.getText());
        pedido.setTipoPagamento(cbTipoPagamento.getValue());
        pedido.setViagem(rbViagem.isSelected() ? 'S' : 'N');

        for(int i=0;i<tableView.getItems().size();i++) {
            pedido.addItem(tableView.getItems().get(i));
        }

        PedidoDAL pedidoDAL = new PedidoDAL();
        pedidoDAL.gravar(pedido);
        btProduto.getScene().getWindow().hide();
    }

    @FXML
    void onSelProduto(ActionEvent event) {
        ModalTable mt=new ModalTable(new ProdutoDAL().get(""),new String[]{"idProduto","nomeProduto","valorProduto","categoria"},"nomeProduto");
        Stage stage=new Stage();
        stage.setScene(new Scene(mt));
        stage.setWidth(600); stage.setHeight(480); stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        produto = (Produto) mt.getSelecionado();
        if(produto!=null){
            btProduto.setText(produto.getNomeProduto());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coProduto.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().produto().getNomeProduto()));
        coQuantidade.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().quantidade()));
        coValor.setCellValueFactory(cellData-> new SimpleStringProperty(""+cellData.getValue().valor()));
        MaskFieldUtil.foneField(tfTelefone);
        spQuantidade.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1));
        carregarTiposPagamento();
    }

    private void carregarTiposPagamento() {
        List<TipoPagamento> tipoPagamentoList = new TipoPagamentoDAL().get("");
        cbTipoPagamento.setItems(FXCollections.observableArrayList(tipoPagamentoList));
        cbTipoPagamento.getSelectionModel().select(0); //first select
    }
}

