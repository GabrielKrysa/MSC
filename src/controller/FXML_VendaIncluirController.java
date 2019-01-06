package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import model.CrudSQLITE;
import model.Poltrona;
import util.VendaAtual;

public class FXML_VendaIncluirController implements Initializable {

    private @FXML
    GridPane gridEsquerda;
    private @FXML
    GridPane gridDireita;

    private @FXML
    Label salaSelecionadaId;
    private @FXML
    Label filmeSalacionadoNome;

    private @FXML
    JFXButton btnAtualizarPoltronasSelecionadas;
    
    private int contadorPoltronas = 0;
    
    private CrudSQLITE poltronaDAO = new CrudSQLITE();

    private ObservableList<Poltrona> listPoltronas;
    private List<Poltrona> listPoltronasDaSalaSelecionada;

    /**
     * Carrega todos as poltronas do banco de dados.
     *
     * @throws SQLException
     */
    private ObservableList<Poltrona> obtemPoltronas() throws SQLException {
        ObservableList<Poltrona> list = FXCollections.observableArrayList(poltronaDAO.listarPoltronas());

        return list;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            listPoltronas = obtemPoltronas();
            listPoltronasDaSalaSelecionada = obtemPoltronas(); // Inicializa a lista
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Limpa a lista para que possam ser adicionados apenas as poltronas da sala selecionada
        listPoltronasDaSalaSelecionada.clear();

        // Adiciona apenas as poltronas da sala selecionada
        for (Poltrona p : listPoltronas) {
            if (p.getSalaId() == VendaAtual.salaId) {
                if (p.getHorario() == VendaAtual.horarioId) {
                    Poltrona polt = new Poltrona(p.getPoltronaId(), p.getSalaId(), p.getStatus(), p.getHorario());
                    listPoltronasDaSalaSelecionada.add(polt);
                }
            }
        }

        salaSelecionadaId.setText(String.valueOf(VendaAtual.salaId));
        filmeSalacionadoNome.setText(String.valueOf(VendaAtual.filmeId));

        // Este contador se refere ao ID da poltrona no banco de dados
        int cont = 0;
        
        // Inicializa as poltronas do lado ESQUERDO
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0 && j == 0) || ((i == 1 && j == 0)) || ((i == 0 && j == 1)) || ((i == 3 && j == 7))) {
                    // Esse if tira as poltronas das posi��es n�o desejadas no GridPane
                } else {
                    // A lista de poltronas selecionadas � a mesma quantidade de poltronas que tem cadastrado no banco
                    if (listPoltronasDaSalaSelecionada.size() > cont) {
                        for (Poltrona p : listPoltronasDaSalaSelecionada) {
                            // Como pode ver, o id da poltrona deve ser o mesmo do contador
                            if (p.getPoltronaId() == cont) {
                                JFXButton botaoPoltrona = criarBotao(String.valueOf(cont));

                                botaoPoltrona.setOnAction(e -> {
                                    verificaStatus(botaoPoltrona, botaoPoltrona.getText());
                                });

                                estilizaBotao(botaoPoltrona, p.getStatus());

                                gridEsquerda.add(botaoPoltrona, i, j);
                            }
                        }
                        // Completa o restante das poltronas que n�o possuem no banco com a cor AZUL
                    } else {
                        JFXButton botaoPoltrona = criarBotao(String.valueOf(cont));

                        estilizaBotao(botaoPoltrona, "Teste");

                        gridEsquerda.add(botaoPoltrona, i, j);
                    }

                    cont++;
                }
            }
        }

        // Inicializa as poltronas do lado DIREITO
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 2 && j == 0) || ((i == 3 && j == 0)) || ((i == 3 && j == 1)) || ((i == 0 && j == 7))) {
                    // Esse if tira as poltronas das posi��es n�o desejadas no GridPane
                } else {
                    // A lista de poltronas selecionadas � a mesma quantidade de poltronas que tem cadastrado no banco
                    if (listPoltronasDaSalaSelecionada.size() > cont) {
                        for (Poltrona p : listPoltronasDaSalaSelecionada) {
                            // Como pode ver, o id da poltrona deve ser o mesmo do contador
                            if (p.getPoltronaId() == cont) {
                                JFXButton botaoPoltrona = criarBotao(String.valueOf(cont));

                                botaoPoltrona.setOnAction(e -> {
                                    verificaStatus(botaoPoltrona, botaoPoltrona.getText());
                                });

                                estilizaBotao(botaoPoltrona, p.getStatus());

                                gridDireita.add(botaoPoltrona, i, j);
                            }
                        }
                        // Completa o restante das poltronas que n�o possuem no banco com a cor AZUL
                    } else {
                        JFXButton botaoPoltrona = criarBotao(String.valueOf(cont));

                        estilizaBotao(botaoPoltrona, "Teste");

                        gridDireita.add(botaoPoltrona, i, j);
                    }
                    cont++;
                }
            }
        }
    }

    private void estilizaBotao(JFXButton botaoPoltrona, String status) {
        botaoPoltrona.setStyle("-fx-background-radius: 100; -fx-background-color: #ffffff");
        botaoPoltrona.setPrefSize(40, 40);
        botaoPoltrona.setRipplerFill(Color.valueOf("#ffaa00"));

        SVGGlyph glyph;
        String path = "M55.522,28.426H45.64v5.381l-1.241,0.517V10.096c0-4.225-3.426-7.65-7.65-7.65H20.012c-4.225,0-7.65,3.425-7.65,7.65    v21.093l-1.204,0.502v-3.265H7.176l0.633-4.846c-1.282,0.076-2.351,0.076-2.351,0.076H5.167c0,0-1.068,0-2.353-0.076l0.633,4.846    H1.276C0.57,28.425,0,28.996,0,29.7v2.976c0,0.704,0.57,1.275,1.276,1.275h5.738v11.182h1.603c0,0.451,0,0.939,0,1.461    c0,1.996,1.712,3.613,3.825,3.613h6.747v4.146h18.384v-4.146h6.749c2.111,0,3.824-1.64,3.824-3.665v-0.503h1.604V33.951h5.772    c0.705,0,1.276-0.571,1.276-1.275V29.7C56.797,28.998,56.227,28.426,55.522,28.426z M44.399,39.264H12.361v-1.115h32.038V39.264z";

        // Se for inativo a cor fica vermelha, se for ativo a cor fica verde, caso contrario fica azul
        if (status.equals("Inativo")) {
            glyph = new SVGGlyph(-1, status, path, Color.web("#D80027"));
        } else {
            if (status.equals("Ativo")) {
                glyph = new SVGGlyph(-1, status, path, Color.web("#91DC5A"));
            } else {
                glyph = new SVGGlyph(-1, "test", path, Color.web("#4286f4"));
            }
        }

        glyph.setSize(20, 20);
        botaoPoltrona.setGraphic(glyph);
    }

    private void verificaStatus(JFXButton button, String text) {
        int idButton = Integer.parseInt(text);

        for (Poltrona p : listPoltronasDaSalaSelecionada) {
            if (p.getPoltronaId() == idButton) {
                if (p.getStatus().equals("Ativo")) {
                    p.setStatus("Inativo");
                    estilizaBotao(button, p.getStatus());
                    contadorPoltronas++;
                    System.out.println(contadorPoltronas);
                } 
            }
        }

        mostrarPoltronasSelecionadas(listPoltronasDaSalaSelecionada);
    }

    private void mostrarPoltronasSelecionadas(List<Poltrona> listPoltronasDaSalaSelecionada2) {
        System.out.println("Lista de poltronas: ");
        for (Poltrona p : listPoltronasDaSalaSelecionada) {
            System.out.println("Sala: " + p.getSalaId()
                    + " Poltrona: " + p.getPoltronaId()
                    + " Status: " + p.getStatus());
        }
        System.out.println();
    }

    private JFXButton criarBotao(String texto) {
        JFXButton botao = new JFXButton(texto);
        botao.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        GridPane.setFillHeight(botao, true);
        GridPane.setFillWidth(botao, true);
        GridPane.setHgrow(botao, Priority.ALWAYS);
        GridPane.setVgrow(botao, Priority.ALWAYS);
        return botao;
    }

    public GridPane inicializarGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(3);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        return grid;
    }

    @FXML
    public void btnAtualizarPoltronasSelecionadasOnAction(ActionEvent e) {
        try {
            poltronaDAO.atualizar(listPoltronasDaSalaSelecionada);
            poltronaDAO.incluirvenda(contadorPoltronas * 20);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
