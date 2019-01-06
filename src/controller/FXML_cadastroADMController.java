package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.CrudSQLITE;

/**
 * classe responsavel por controlar a interface grafica FXML_cadastroADM
 *
 *
 * @author Krysa
 */
public class FXML_cadastroADMController implements Initializable {

    @FXML
    private JFXPasswordField texteConfirmarSenha;

    @FXML
    private JFXPasswordField texteSenha;

    @FXML
    private JFXTextField textNomeCompletoADM;

    @FXML
    private JFXTextField textCpfAdm;

    @FXML
    private JFXTextField textLoginADM;

    @FXML
    private JFXButton buttonCadastrar;

    private String login;
    private String senha;
    private String confirmarSenha;
    private String nomeCompleto;
    private String cpf;
    private String strCpf = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método que cadastrar as informações do administrador no banco de dados.
     *
     *
     * @param evt evento
     * @throws java.io.IOException exceção
     */
    @FXML
    protected void cadastrarAdministrador(ActionEvent evt) throws IOException {
        //informações de usuario e senho do ADM;
        login = textLoginADM.getText();
        senha = texteSenha.getText();
        confirmarSenha = texteConfirmarSenha.getText();
        nomeCompleto = textNomeCompletoADM.getText();
        cpf = textCpfAdm.getText();
        Checador check = new Checador();

        if (login.length() >= 4) {

            if (senha.equals(confirmarSenha)) {

                if (senha.length() >= 5) {

                    if (nomeCompleto.length() >= 5) {

                        if (check.checkCPF(cpf)) {

                            for (int y = 0; y < cpf.length(); y++) {
                                char c = cpf.charAt(y);
                                  if(!(c == ' ' || c == '.' || c == '-')){
                                    strCpf += ""+c;
                                }
                            }
                            
                            CrudSQLITE insert = new CrudSQLITE();
                            insert.insertFuncionarioADM(login, senha, nomeCompleto, strCpf);

                            registerFailed(evt, "ADMINISTRADOR CADASTRADO COM SUCESSO");

                            buttonCadastrar.setText("Seguir para a tela de login");
                            buttonCadastrar.setOnAction((ActionEvent e) -> {
                                try {
                                    seguir(evt);
                                } catch (IOException ex) {
                                    Logger.getLogger(FXML_cadastroADMController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });

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

    /**
     * Método responsavel por fazer o Snack bar surgir com texto especifico.
     *
     * @param evt, evento.
     * @param str, texto que aparecera no Scack bar
     */
    public void registerFailed(ActionEvent evt, String str) {
        JFXSnackbar bar = new JFXSnackbar((Pane) ((Node) evt.getTarget()).getScene().getRoot());
        bar.getStylesheets().add("/view/Style_SnackBar.css");
        bar.show(str, 3500);
    }

    /**
     * Método responsavel por fechar a interface grafica.
     */
    private void fechar() {
        Stage stage = (Stage) buttonCadastrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Método responsavel por abrir a tela de login.
     *
     * @param evt evento.
     * @throws IOException utilziado na linha 152 na parte de achar o caminho.
     * da interface.
     */
    public void seguir(ActionEvent evt) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        fechar();
    }

}
