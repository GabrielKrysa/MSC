package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CrudSQLITE;

/**
 * classe responsavel por controlar a interface grafica FXML_Escolha.
 *
 * @author Administrador
 */
public class FXML_EscolhaController implements Initializable {

    @FXML
    private JFXButton buttonHistorico;

    @FXML
    private JFXButton buttonVenda;

    @FXML
    private JFXButton buttonCadastraFunc;

    @FXML
    private JFXButton buttonSair;

    @FXML
    private JFXButton buttonCadastraFilme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> login_senha = new ArrayList<>();
        CrudSQLITE getLogin = new CrudSQLITE();
        login_senha = getLogin.selectLogin();

        System.out.println(login_senha.get(0));
        System.out.println(login_senha.get(1));

        if (!(getLogin.selectTipoFuncionario(login_senha.get(0), login_senha.get(1)))) {
            buttonCadastraFilme.setDisable(true);
            buttonCadastraFilme.setOpacity(0);
            buttonCadastraFunc.setDisable(true);
            buttonCadastraFunc.setOpacity(0);
            buttonHistorico.setDisable(true);
            buttonHistorico.setOpacity(0);
        }
    }

    /**
     * Método responsavel por controlar a inicialização de uma parte da
     * interface do sistema, cadastro de funcionario.
     *
     * @param event evento
     * @throws java.io.IOException
     */
    @FXML
    public void anchorPrimeiro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método responsavel por controlar a inicialização de uma parte da
     * interface do sistema, realizar venda.
     *
     * @param event evento
     * @throws java.io.IOException
     */
    @FXML
    public void anchorSegundo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_CadastroFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método responsavel por controlar a inicialização de uma parte da
     * interface do sistema, consultar filme.
     *
     * @param event evento
     * @throws java.io.IOException
     */
    @FXML
    public void anchorTerceiro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_VerHistorico.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método responsavel por controlar a inicialização de uma parte da
     * interface do sistema, cadastro de filmes.
     *
     * @param event evento
     * @throws java.io.IOException
     */
    @FXML
    public void anchorQuarto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_CadastroFilme.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void sair(ActionEvent evt) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        fechar();
    }

    public void fechar() {
        Stage stage = (Stage) buttonSair.getScene().getWindow();
        stage.close();
    }

}
