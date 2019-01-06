package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CrudSQLITE;
import model.Filme; // aqui ó
import util.VendaAtual;

public class FXML_VendaController implements Initializable {
    
    @FXML
    private JFXButton btnCadastrarVenda;
    @FXML
    private JFXButton btnEscolherPoltronas;
    @FXML
    private JFXTabPane tabPaneVenda;

    @FXML
    private TableView<Filme> tabelaFilmes = new TableView<Filme>();
    @FXML
    private TableColumn<Filme, String> nome = new TableColumn<Filme, String>();
    @FXML
    private TableColumn<Filme, Integer> idFilme = new TableColumn<Filme, Integer>();
    
    
    @FXML
    private JFXComboBox<String> ComboBoxSala;
    @FXML
    private JFXComboBox<String> ComboBoxHorario;
    

    private int id = 0;
    private String hora= "";
    
    private final CrudSQLITE vendaDAO = new CrudSQLITE();

    private Filme filmeSelecionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelaFilmes.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Filme>) (observable, oldValue, newValue) -> {
            filmeSelecionado = (Filme) newValue;
        });

        try {
            initTabelaFilmes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnCadastrarVendaOnAction(ActionEvent e) {
        tabPaneVenda.getSelectionModel().selectNext();
    }

    @FXML
    public void btnEscolherPoltronasOnAction(ActionEvent e) {
        VendaAtual.filmeId = filmeSelecionado.getFilmeId();
        VendaAtual.salaId = id;
        CrudSQLITE hId = new CrudSQLITE();
        VendaAtual.horarioId = hId.selectIDHorario(hora);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/FXMLVendaIncluir.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Escolher Poltronas");
            stage.setScene(new Scene(root, 700, 500));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Carrega tabela a tabela de filmes com o resultado do metodo
     * {@link #atualizaTabela}.
     *
     * @throws SQLException
     */
    private void initTabelaFilmes() throws SQLException {
        idFilme.setCellValueFactory(new PropertyValueFactory<Filme, Integer>("filmeId"));
        nome.setCellValueFactory(new PropertyValueFactory<Filme, String>("nome"));
        tabelaFilmes.setItems(atualizaTabelaFilmes());
    }

   
    private ObservableList<Filme> atualizaTabelaFilmes() throws SQLException {
        ObservableList<Filme> list = FXCollections.observableArrayList(vendaDAO.getFilmesList());
        tabelaFilmes.setItems(list);
        return list;
    }
    
    
    public void verificaSalaHorario(ActionEvent evt){
        int nome = tabelaFilmes.getSelectionModel().getSelectedItem().getFilmeId();        
        CrudSQLITE crud = new CrudSQLITE();
        id = crud.selectSalaFilme(nome);
        String aux = ""+id;
        hora = crud.selectHorarioFilme(nome);
        ObservableList<String> salaId = FXCollections.observableArrayList(aux);
        ObservableList<String> hora1 = FXCollections.observableArrayList(hora);
        ComboBoxSala.setItems(salaId);
        ComboBoxHorario.setItems(hora1);
    }
}
