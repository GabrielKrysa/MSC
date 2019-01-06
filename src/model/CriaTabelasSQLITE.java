/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gabri
 */
public class CriaTabelasSQLITE {

    private final ConexaoSQLITE conexao = new ConexaoSQLITE();

    public CriaTabelasSQLITE() {

        conexao.conectar();
    }

    /**
     * Metedo responsavel por chamar os metodos que criam em si as tabelas do
     * banco de dados.
     */
    public void CriaTabelas() {
        criaTabelaFilme();
        criaTabelaFuncionario();
        criaTabelaLogin();
        criaTabelaPoltrona();
        criaTabelaSala();
        criaTabelaVedaIngresso();
        criaTabelaHorario();
    }

    private void criaTabelaHorario() {
        String sql
                = "CREATE TABLE Horario (horarioId integer primary key, horario varchar (5));";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA HORARIO");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    private void criaTabelaSala() {
        String sql
                = "CREATE TABLE Sala (\n"
                + " salaId int NOT NULL primary key\n"
                + ");";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA SALA");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    /**
     * Cria a tabela filme no banco de dados utilizando Statement.
     */
    private void criaTabelaFilme() {
        String sql
                = "CREATE TABLE Filme (\n"
                + " filmeId integer primary key,\n"
                + " nome varchar (30),\n"
                + " dataLancamento varchar (10),\n"
                + " dataEncerramento varchar (10),\n"
                + " dataPreEstreia varchar (10),\n"
                + " hora varchar (10),\n"
                + " tipo varchar (10),\n"
                + " classificacao varchar (20),\n"
                + " genero varchar (20),\n"
                + " duracao varchar (10), \n"
                + " salaId int,\n"
                + " FOREIGN KEY (salaId) REFERENCES Sala (salaId) ON UPDATE CASCADE ON DELETE CASCADE\n"
                + ");";
        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA FILME");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    private void criaTabelaPoltrona() {
        String sql
                = "CREATE TABLE Poltrona (\n"
                + " poltronaId int NOT NULL,\n"
                + " salaId int NOT NULL,\n"
                + " horarioId int NOT NULL, "
                + " status varchar (10),\n"
                + " FOREIGN KEY (salaId) REFERENCES Sala (salaId) ON UPDATE CASCADE ON DELETE CASCADE,\n"
                + " FOREIGN KEY (horarioId) REFERENCES Horario (horarioId) ON UPDATE CASCADE ON DELETE CASCADE"
                + ");";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA POLTRONA");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    private void criaTabelaVedaIngresso() {
        String sql
                = " CREATE TABLE VendaIngresso (\n"
                + " vendaId integer primary key,\n"
                + " filmeId int NOT NULL,\n"
                + " nomeFuncionario varchar (15) NOT NULL,\n"
                + " dataVenda timestamp default current_timestamp,\n"
                + " valorIngressosFinal numeric (7,2),\n"
                + " FOREIGN KEY (nomeFuncionario) REFERENCES Funcionario (login) ON UPDATE CASCADE ON DELETE CASCADE, \n"
                + " FOREIGN KEY (filmeId) REFERENCES Filme (filmeId) ON UPDATE CASCADE ON DELETE CASCADE\n"
                + ");";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA VENDA INGRESSO");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    private void criaTabelaFuncionario() {
        String sql
                = "CREATE TABLE funcionario (\n"
                + " tipoFuncionario int,\n"
                + " login varchar (15),\n"
                + " senha varchar (30),\n"
                + " nomeCompleto varchar (30),\n"
                + " cpf varchar (15),\n"
                + " numCarteiraTrabalho varchar (15),\n"
                + " PRIMARY KEY (cpf)\n"
                + ");";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA FUNCIONARIO");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }

    private void criaTabelaLogin() {
        String sql
                = "CREATE TABLE login(\n"
                + " login varchar (30),\n"
                + " senha varchar (30),\n"
                + " dataLogin timestamp default current_timestamp\n"
                + ");";

        try {

            Statement stmt = this.conexao.criaStatment();

            stmt.execute(sql);
            System.out.println("CRIOU TABELA LOGIN");
        } catch (SQLException e) {

            System.err.println("erro" + e.getMessage());

        }
    }
}
