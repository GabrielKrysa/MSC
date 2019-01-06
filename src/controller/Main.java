package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CriaTabelasSQLITE;
import model.CrudSQLITE;

/**
 * classe principal responsavel por inciar a primeira interface grafica:
 * FXML_Login ou FXML_cadastroADM
 *
 * @author Krysa
 */
public class Main extends Application {

    /**
     * Esse método é chamado quando o aplicativo deve parar e fornece um local
     * conveniente para se preparar para a saída de aplicativos e destruir
     * recursos.
     *
     * @param stage janela da aplicação
     * @throws Exception exceção
     */
    @Override
    public void start (Stage stage) throws Exception {
        CrudSQLITE crud = new CrudSQLITE ();
        Parent root;
        if (crud.selectADM ()) {
            root = FXMLLoader.load (getClass ().getResource ("/view/FXML_Login.fxml"));
        } else {
            root = FXMLLoader.load (getClass ().getResource ("/view/FXML_cadastroADM.fxml"));
        }

        Scene scene = new Scene (root);

        stage.setScene (scene);
        stage.show ();
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        CriaTabelasSQLITE criaTabela = new CriaTabelasSQLITE ();
        criaTabela.CriaTabelas ();
        launch (args);
    }

}
