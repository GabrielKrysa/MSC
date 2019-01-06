/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import model.CrudSQLITE;

/**
 * FXML Controller class
 *
 * @author Gabri
 */
public class FXML_CadastroFilmeController implements Initializable {

    @FXML
    private DatePicker datePreEstreia;

    @FXML
    private JFXComboBox<String> comboBoxClassificacao;

    @FXML
    private JFXComboBox<String> comboBoxGenero;

    @FXML
    private DatePicker dateEstreia;

    @FXML
    private JFXComboBox<String> comboBoxVisualização;

    @FXML
    private JFXTextField textTitulo;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private JFXTextField textDuracao;

    @FXML
    private JFXComboBox<String> comboBoxEscolhaSalaCadastro;

    @FXML
    private JFXComboBox<String> comboBoxEscolhaHorario;

    @FXML
    private JFXButton buttonRevisar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startCombox();
    }

    protected void startCombox() {

        String e[] = new String[3];
        String gen[] = new String[29];
        String salas[] = new String[4];

        e[0] = "16";
        e[1] = "19";
        e[2] = "22";

        salas[0] = "1";
        salas[1] = "2";
        salas[2] = "3";
        salas[3] = "4";

        String vi1 = "2D";
        String vi2 = "3D";

        String c1 = "L";
        String c2 = "10";
        String c3 = "12";
        String c4 = "14";
        String c5 = "16";
        String c6 = "18";

        gen[0] = "Ação";
        gen[1] = "Animação";
        gen[2] = "Aventura";
        gen[3] = "Cinema de arte";
        gen[4] = "Cinema catástrofe";
        gen[5] = "Comédia";
        gen[6] = "Comédia";
        gen[7] = "Comédia dramática";
        gen[8] = "Comédia de ação";
        gen[9] = "Cult";
        gen[10] = "Dança";
        gen[11] = "Documentário";
        gen[12] = "Docuficção";
        gen[13] = "Drama";
        gen[14] = "Espionagem";
        gen[15] = "Erótico";
        gen[16] = "Fantasia";
        gen[17] = "Faroeste";
        gen[18] = "Ficção  científica ";
        gen[19] = "Franchise / Séries";
        gen[20] = "Guerra";
        gen[21] = "Musical";
        gen[22] = "Policial";
        gen[23] = "Robologia";
        gen[24] = "Romance";
        gen[25] = "Seriado";
        gen[26] = "Suspense";
        gen[27] = "Terror(ou horror)";
        gen[28] = "Trash";

        ObservableList<String> visualização = FXCollections.observableArrayList(vi1, vi2);
        ObservableList<String> classificação = FXCollections.observableArrayList(c1, c2, c3, c4, c5, c6);
        ObservableList<String> genero = FXCollections.observableArrayList(gen);
        ObservableList<String> escolha = FXCollections.observableArrayList(e);
        ObservableList<String> sala = FXCollections.observableArrayList(salas);

        comboBoxVisualização.setItems(visualização);
        comboBoxClassificacao.setItems(classificação);
        comboBoxGenero.setItems(genero);
        comboBoxEscolhaSalaCadastro.setItems(sala);
        comboBoxEscolhaHorario.setItems(escolha);
    }

    @FXML
    public void cadastraFilme(ActionEvent evt) {
        /*comboBoxVisualização comboBoxClassificacao comboBoxGenero
        comboBoxEscolhaSalaCadastro comboBoxEscolhaHorario textDuracao textTitulo*/

        LocalDate ED = dateEstreia.getValue();
        LocalDate FD = dateFinal.getValue();
        LocalDate PD = datePreEstreia.getValue();
        String classificacao = null;
        String visuzalisacao = null;
        String horario = null;
        String genero = null;
        String duracao = null;
        String titulo = null;
        String escolhaSala = null;
        int sala = 0;

        if (comboBoxEscolhaSalaCadastro.getValue() != null) {
            escolhaSala = comboBoxEscolhaSalaCadastro.getValue().toLowerCase();
            sala = Integer.parseInt(escolhaSala);
        }

        if (comboBoxClassificacao.getValue() != null) {
            classificacao = comboBoxClassificacao.getValue().toLowerCase();
        }

        if (comboBoxVisualização.getValue() != null) {
            visuzalisacao = comboBoxVisualização.getValue().toLowerCase();
        }

        if (comboBoxEscolhaHorario.getValue() != null) {
            horario = comboBoxEscolhaHorario.getValue().toLowerCase();
        }

        if (comboBoxGenero.getValue() != null) {
            genero = comboBoxGenero.getValue().toLowerCase();
        }

        if (!"".equals(textDuracao.getText())) {

            duracao = textDuracao.getText();
        }

        if (!"".equals(textTitulo.getText())) {

            titulo = textTitulo.getText();
        }

        if (classificacao != null && visuzalisacao != null && horario != null && genero != null && duracao != null && titulo != null && sala != 0) {
            if (ED != null && FD != null) {
                CrudSQLITE crud = new CrudSQLITE();

                int estreiaDia = ED.getDayOfMonth();
                int estreiaMes = ED.getMonthValue();
                int estreiaAno = ED.getYear();

                int finalDia = FD.getDayOfMonth();
                int finalMes = FD.getMonthValue();
                int finalAno = FD.getYear();

                String finalData = "" + finalDia + "-" + finalMes + "-" + finalAno;
                String estreiaData = "" + estreiaDia + "-" + estreiaMes + "-"
                        + estreiaAno;

                if (estreiaMes == finalMes && estreiaDia <= finalDia) {
                    if (PD != null) {
                        int preDia = PD.getDayOfMonth();
                        int preMes = PD.getMonthValue();
                        int preAno = PD.getYear();

                        if (estreiaMes - preMes == 0) {
                            if (estreiaDia - preDia <= 3 && estreiaDia
                                    > preDia) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {

                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                }
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else if (estreiaMes - preMes == 1) {
                            if (preDia < 32 && preDia > 26) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {

                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                }
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else if (estreiaMes == 1 && preMes == 12) {
                            if (preDia < 32 && preDia
                                    > 26) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {

                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                }
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else {
                            registerFailed(evt, "ERRO DESCONHECIDO");
                        }

                    } else {
                        if (crud.insereFilme(estreiaData, finalData, "", sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {
                            registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                        } else {
                            registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                        }
                    }
                } else if (estreiaMes < finalMes) {
                    if (PD != null) {
                        int preDia
                                = PD.getDayOfMonth();
                        int preMes = PD.getMonthValue();
                        int preAno
                                = PD.getYear();

                        if (estreiaMes - preMes == 0) {
                            if (estreiaDia - preDia <= 3 && estreiaDia
                                    > preDia) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {
                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                };
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else if (estreiaMes - preMes == 1) {
                            if (preDia < 32 && preDia > 26) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {

                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                }
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else if (estreiaMes == 1 && preMes == 12) {
                            if (preDia < 32 && preDia
                                    > 26) {
                                String preData = "" + preDia + "-" + preMes + "-" + preAno;
                                if (crud.insereFilme(estreiaData, finalData, preData, sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {

                                    registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                                } else {
                                    registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                                }
                            } else {
                                registerFailed(evt, "DIA PRÉ ESTRÉIA MUITO LONGE DA ESTRÉIA");
                            }
                        } else {
                            registerFailed(evt, "ERRO DESCONHECIDO");
                        }

                    } else {
                        if (crud.insereFilme(estreiaData, finalData, " ", sala, horario, visuzalisacao, genero, duracao, titulo, classificacao)) {
                            registerFailed(evt, "FILME CADASTRADO COM SUCESSO");
                        } else {
                            registerFailed(evt, "JA EXISTE UM FILME CADASTRADO NESSA HORARIO E SALA");
                        }
                    }
                } else {
                    registerFailed(evt, "DATAS INFORMADAS INCORRETAS");
                }
            } else {
                registerFailed(evt, "ESCOLHA UMA DATA");
            }

        } else {
            registerFailed(evt, "UM OU MAIS CAMPOS VAZIOS, PREENCHA-OS");
        }

    }

    public void registerFailed(ActionEvent evt, String str) {
        JFXSnackbar bar = new JFXSnackbar((Pane) ((Node) evt.getTarget()).getScene().getRoot());
        bar.getStylesheets().add("/view/Style_SnackBar.css");
        bar.show(str, 3500);

    }

}
