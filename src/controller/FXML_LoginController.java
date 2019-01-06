package controller;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CrudSQLITE;

/**
 * classe responsavel por controlar a interface grafica FXML_Login.
 *
 * @author Krysa, Mark, Tiago
 */
public class FXML_LoginController implements Initializable {

    @FXML
    private JFXPasswordField textSenha;

    @FXML
    private JFXButton buttonSobre;

    @FXML
    private JFXButton buttonMinimize;

    @FXML
    private JFXTextField textUsuario;

    @FXML
    private JFXButton buttonClose;

    @FXML
    private JFXButton buttonEntrar;

    @FXML
    private Label labelStatus;

    private String usuario;
    private String senha;

 
    public void verificaShortcuts(){
	textSenha.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    CrudSQLITE login = new CrudSQLITE();
                    if (login.selectFuncinario(getLogin(), getSenha())) {
                        try {
                            login.insertLogin(getLogin(),getSenha());
                            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Escolha.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                            fechar();
                        } catch (IOException ex) {
                            Logger.getLogger(FXML_LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        loginFailed(ke, "LOGIN E/OU SENHA INCORRETO(S)");
                    }
                }
            }
        });
    }
    public void verificaShortcutsUser(){
	textUsuario.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    CrudSQLITE login = new CrudSQLITE();
                    if (login.selectFuncinario(getLogin(), getSenha())) {
                        try {
                            login.insertLogin(getLogin(),getSenha());
                            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Escolha.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                            fechar();
                        } catch (IOException ex) {
                            Logger.getLogger(FXML_LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        loginFailed(ke, "LOGIN E/OU SENHA INCORRETO(S)");
                    }
                }
            }
        });
    }
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método responsavel por realizar o login de usuario.
     */
    @FXML
    private void login(ActionEvent event) throws IOException, SQLException {
        CrudSQLITE login = new CrudSQLITE();
        if (login.selectFuncinario(getLogin(), getSenha())) { 
            login.insertLogin(getLogin(),getSenha());
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Escolha.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            fechar();

        } else {
            loginFailed(event, "LOGIN E/OU SENHA INCORRETO(S)");
        }
    }

    /**
     * Método responsavel por fechar a interface grafica.
     */
    private void fechar() {
        Stage stage = (Stage) buttonEntrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Método responsavel por retornar o login do usuario
     *
     *
     * @return login usuario;
     */
    public String getLogin() {
        usuario = textUsuario.getText();
        return usuario;
    }

    /**
     * Método responsavel por retornar a senha do usuario
     *
     *
     * @return senha usuario;
     */
    public String getSenha() {
        senha = textSenha.getText();
        return senha;
    }

    /**
     * Método responsavel por fazer o Snack bar surgir com texto especifico.
     *
     * @param evt evento.
     * @param str texto que aparecera no Scack bar
     */
    public void loginFailed(ActionEvent evt, String str) {
        JFXSnackbar bar = new JFXSnackbar((Pane) ((Node) evt.getTarget()).getScene().getRoot());
        bar.getStylesheets().add("/view/Style_SnackBar.css");
        bar.show(str, 3500);

    }
    public void loginFailed(KeyEvent evt, String str) {
        JFXSnackbar bar = new JFXSnackbar((Pane) ((Node) evt.getTarget()).getScene().getRoot());
        bar.getStylesheets().add("/view/Style_SnackBar.css");
        bar.show(str, 3500);

    }

    public void sobreMSC(ActionEvent evt) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_SobreMSC.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }

}
