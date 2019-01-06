/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import model.CrudSQLITE;

/**
 * FXML Controller class
 *
 * @author Gabri
 */
public class FXML_CadastroFuncionarioController implements Initializable {
    
    @FXML
    private JFXPasswordField passwordSenha;

    @FXML
    private JFXTextField textCPF;

    @FXML
    private JFXTextField textNomeCompleto;

    @FXML
    private JFXTextField textLogin;

    @FXML
    private JFXButton buttonCadastrar;

    @FXML
    private JFXTextField textNumeroRegistro;

    @FXML
    private JFXPasswordField passwordRepitaSenha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     /**
     * Método responsavel por realizar o cadastro de um funcionário no banco de
     * dados.
     *
     * @param evt, evento.
     */
    @FXML
    protected void cadastrarFuncionario(ActionEvent evt) {

        Checador check = new Checador();
        String strCpf = "";
        String nomeCompleto = textNomeCompleto.getText();
        String numeroRegistro = textNumeroRegistro.getText();
        String cpf = textCPF.getText();
        String login = textLogin.getText();
        String senha = passwordSenha.getText();
        String confirmarSenha = passwordRepitaSenha.getText();

        if (login.length() >= 4) {

            if (senha.equals(confirmarSenha)) {

                if (senha.length() >= 5) {

                    if (nomeCompleto.length() >= 5) {

                        if (check.checkCPF(cpf)) {
                            for (int y = 0; y < cpf.length(); y++) {
                                char c = cpf.charAt(y);
                                String a = "" + c;
                                if (!(" ".equals(a) || ".".equals(a) || "-".equals(a))) {

                                    strCpf += a;
                                }
                            }

                            CrudSQLITE insert = new CrudSQLITE();
                            insert.insertFuncionarioComum(login, senha, nomeCompleto, strCpf, numeroRegistro);
                            registerFailed(evt, "FUNCIONARIO CADASTRADO COM SUCESSO");
                            textNomeCompleto.setText("");
                            textCPF.setText("");
                            textLogin.setText("");
                            textNumeroRegistro.setText("");
                            passwordRepitaSenha.setText("");
                            passwordSenha.setText("");

                        } else {
                            registerFailed(evt, "CPF INVALIDO, INSIRA UM CPF VALIDO");
                        }
                    } else {
                        registerFailed(evt, "NOME COMPLETO INVALIDO, INSIRA UM NOME VALIDO");
                    }
                } else {
                    registerFailed(evt, "SENHA CURTA DEMAIS,PRECISA TER PELO MENOS 5 DIGITOS");
                }
            } else {
                registerFailed(evt, "AS SENHAS NÃO COINCIDEM");
            }
        } else {
            registerFailed(evt, "LOGIN INVALIDO, PRECISA TER PELO MENOS 5 DIGITOS");
        }
    }
    
    public void registerFailed(ActionEvent evt, String str) {
        JFXSnackbar bar = new JFXSnackbar((Pane) ((Node) evt.getTarget()).getScene().getRoot());
        bar.getStylesheets().add("/view/Style_SnackBar_Escolha.css");
        bar.show(str, 3500);
    }
    
}
