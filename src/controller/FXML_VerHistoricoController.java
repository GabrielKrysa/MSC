/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.CrudSQLITE;

public class FXML_VerHistoricoController {

    @FXML
    private TextArea textAreaHistorico;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void mostraHistorico(ActionEvent evt) {
        ArrayList<String> historico = new ArrayList();
        CrudSQLITE crud = new CrudSQLITE();
        historico = crud.selectHistorico();
        String tudo = "";
        int cont = 0;
        if (historico.size() <= 0) {
            tudo = "NENHUMA VENDA CADASTRADA ATÉ O MOMENTO";
            textAreaHistorico.setText(tudo);
        } else {
            for (int i = 0; i < historico.size(); i++) {

                switch (cont) {
                    case 0:
                        tudo += "ID da Venda: " + historico.get(i);
                        break;
                    case 1:
                        tudo += "   |  Nome Funcionario: " + historico.get(i);
                        break;
                    case 2:
                        tudo += "   |  Data e Hora da Venda: " + historico.get(i);
                        break;
                    case 3:
                        tudo += "   |  Valor Total da Venda: " + historico.get(i);
                        break;
                    default:
                        break;
                }
                cont++;
                if (cont == 4) {
                    cont = 0;
                    tudo += "\n";
                }
            }
            textAreaHistorico.setText(tudo);
        }

    }
}
