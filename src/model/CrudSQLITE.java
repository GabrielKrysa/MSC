/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package model;

import controller.Checador;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.StaticHoraID;
import util.VendaAtual;

/**
 * Classe responsel por criar as tabelas do bando de dados
 *
 * @author Krysa
 */
public class CrudSQLITE {

    private final ConexaoSQLITE conexao = new ConexaoSQLITE();

    /**
     * Contrutor que recebe a conec com o banco de dados.
     */
    public CrudSQLITE() {
        conexao.conectar();
    }

    /**
     * Realiza a inserção de um administrador no banco de dados.
     *
     * @param login, palavra chave de login do administrador.
     * @param senha, senha do administrador.
     * @param nomeCompleto, nome completo do administrador.
     * @param cpf, cpf do administrador.
     */
    public void insertFuncionarioADM(String login, String senha, String nomeCompleto, String cpf) {
        conexao.conectar();
        String sql = "INSERT INTO funcionario (tipoFuncionario, login, senha, nomeCompleto, cpf, numCarteiraTrabalho) VALUES(?,?,?,?,?,?)";

        PreparedStatement pstmt = conexao.criaPreparedStatment(sql);

        int x = 1;
        try {

            pstmt.setInt(1, x);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setString(4, nomeCompleto);
            pstmt.setString(5, cpf);
            pstmt.setNull(6, java.sql.Types.VARCHAR);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("INSERIU FUNCIONARIO");
            } else {
                System.out.println("NÃO INSERIU");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            conexao.desconectar();
        }
    }

    /**
     * Realiza a inserção de um administrador no banco de dados.
     *
     * @param login, palavra chave de login do funcionário.
     * @param senha, senha do funcionário.
     * @param nomeCompleto, nome completo do funcionário.
     * @param cpf, cpf do funcionário.
     * @param numeroRegistro, numero da carteira de trabalho do funcionário
     */
    public void insertFuncionarioComum(String login, String senha, String nomeCompleto, String cpf, String numeroRegistro) {
        conexao.conectar();
        String sql = "INSERT INTO funcionario (tipoFuncionario, login, senha, nomeCompleto, cpf, numCarteiraTrabalho) VALUES(?,?,?,?,?,?)";

        PreparedStatement pstmt = conexao.criaPreparedStatment(sql);

        int tipo = 2;
        try {

            pstmt.setInt(1, tipo);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setString(4, nomeCompleto);
            pstmt.setString(5, cpf);
            pstmt.setString(6, numeroRegistro);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("INSERIU FUNCIONARIO");
            } else {
                System.out.println("NÃO INSERIU");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            conexao.desconectar();
        }
    }

    /**
     * Realiza o retorno de informações da tabela funcionario no banco de dados
     * para validar o login.
     *
     * @param login, login que o usuário esta usando para realizar o login.
     * @param senha, senha que o usuário esta usando para realizar o login.
     * @return true se o login fosse valido, false se não for valido.
     */
    public boolean selectFuncinario(String login, String senha) {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;

        String query = "SELECT * FROM funcionario;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String l = rs.getString("login");
                String s = rs.getString("senha");

                if (l.equals(login) && s.equals(senha)) {
                    conexao.desconectar();
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return false;
    }

    /**
     * Realiza o retorno de informações da tabela funcionario no banco de dados
     * para verificar se existe algum administrador cadastrado no banco de
     * dados.
     *
     * @return true se existir um administrador, false se não existir um
     * administrador.
     */
    public boolean selectADM() {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;

        String query = "SELECT * FROM funcionario;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int l = rs.getInt("tipoFuncionario");

                System.out.println(l);
                if (l == 1) {
                    return true;
                }
            }

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return false;
    }

    public boolean insereFilme(String dataLan, String dataEnc, String dataPre, int sala, String hora, String tipo, String genero, String duracao, String titulo, String classificacao) {
        conexao.conectar();
        String sql = "INSERT INTO filme (nome,  dataLancamento,dataEncerramento , dataPreEstreia, hora, tipo, classificacao, genero,duracao,salaId) VALUES(?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conexao.criaPreparedStatment(sql);

        try {

            pstmt.setString(2, dataLan);
            pstmt.setString(3, dataEnc);
            pstmt.setString(4, dataPre);
            pstmt.setString(5, hora);
            pstmt.setString(6, tipo);
            pstmt.setString(8, genero);
            pstmt.setString(9, duracao);
            pstmt.setString(1, titulo);
            pstmt.setString(7, classificacao);
            pstmt.setInt(10, sala);

            if (selectFilme(dataLan, dataEnc, sala, hora)) {
                int result = pstmt.executeUpdate();
                if (result == 1) {
                    System.out.println("INSERIU FILME");
                    return true;
                } else {
                    System.out.println("NÃO INSERIU FILME");
                    return false;
                }
            } else {
                System.out.println("JÁ EXISTE UM FILME CADASTRADO NESSA SALA E HORARIO");
                return false;

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            conexao.desconectar();
        }

        return false;
    }

    public boolean selectFilme(String dataLan, String dataEnc, int sala, String hora) {

        conexao.conectar();
        ResultSet rs;
        Statement stmt;

        String query = "SELECT * FROM Filme;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (rs.getInt("salaId") == sala && rs.getString("hora").equals(hora)) {
                    String dataLan1 = rs.getString("dataLancamento");
                    String dataEnc1 = rs.getString("dataEncerramento");

                    Checador checkData = new Checador();
                    if (!checkData.checkData(dataLan1, dataEnc1, dataLan, dataEnc)) {
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return true;
    }

    public void insertLogin(String login, String senha) {
        conexao.conectar();

        String sql = "INSERT INTO login (login,senha) VALUES(?,?);";

        PreparedStatement pstmt = conexao.criaPreparedStatment(sql);

        try {
            pstmt.setString(1, login);
            pstmt.setString(2, senha);

            int result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("INSERIU LOGIN");
            } else {
                System.out.println("NÃO INSERIU LOGIN");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }

            conexao.desconectar();
        }
    }

    public ArrayList selectLogin() {

        ResultSet rs;
        Statement stmt;
        ArrayList<String> login_senha = new ArrayList<>();
        String login = "";
        String senha = "";
        String query = "SELECT * FROM login;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {            
                login = rs.getString("login");
                senha = rs.getString("senha");
            }
            login_senha.add(login);
            login_senha.add(senha);

            return login_senha;

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return login_senha;
    }

    public boolean selectTipoFuncionario(String login, String senha) {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;

        String query = "SELECT * FROM funcionario;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String l = rs.getString("login");
                String s = rs.getString("senha");

                if (l.equals(login) && s.equals(senha)) {
                    String tipo = rs.getString("tipoFuncionario");
                    conexao.desconectar();
                    if (tipo.equals("1")) {
                        return true;
                    } else if (tipo.equals("0")) {
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return false;
    }

    public List<Poltrona> listarPoltronas() throws SQLException {
        conexao.conectar();
        ResultSet rs;
        List<Poltrona> poltronas = new ArrayList<>();

        String sql = "SELECT * FROM Poltrona;";
        PreparedStatement statement = conexao.criaPreparedStatment(sql);
        rs = statement.executeQuery();

        while (rs.next()) {
            Poltrona poltrona = new Poltrona(rs.getInt("poltronaId"), rs.getInt("salaId"), rs.getString("status"), rs.getInt("horarioId"));
            poltronas.add(poltrona);
        }

        conexao.desconectar();
        return poltronas;
    }

    public void atualizar(List<Poltrona> listaPoltronas) throws SQLException {
        conexao.conectar();
        for (Poltrona p : listaPoltronas) {
            String sql = "UPDATE Poltrona SET poltronaId = ?, salaId = ?,status = ? WHERE poltronaId = ? AND salaId = ? AND horarioId = ?;";
            PreparedStatement statement = conexao.criaPreparedStatment(sql);
            statement.setInt(1, p.getPoltronaId());
            statement.setInt(2, p.getSalaId());
            statement.setString(3, p.getStatus());
            statement.setInt(4, p.getPoltronaId());
            statement.setInt(5, p.getSalaId());
            statement.setInt(6, StaticHoraID.horaId);
            statement.executeUpdate();
        }
        conexao.desconectar();
    }

    public void incluirvenda(int valor) throws SQLException {
        String nome = ultimoLogin();
        conexao.conectar();
        String sql = "INSERT INTO VendaIngresso ( filmeId, valorIngressosFinal, nomeFuncionario) VALUES(?, ?, ?)";
        PreparedStatement statement = conexao.criaPreparedStatment(sql);
        statement.setInt(1, VendaAtual.filmeId);
        statement.setInt(2, valor);
        statement.setString(3, nome);
        statement.executeUpdate();
        conexao.desconectar();
    }

    public List<Filme> getFilmesList() throws SQLException {
        conexao.conectar();
        ResultSet rs;
        List<Filme> filmes = new ArrayList<>();

        String sql = "SELECT * FROM Filme;";
        PreparedStatement statement = conexao.criaPreparedStatment(sql);
        rs = statement.executeQuery();

        while (rs.next()) {
            Filme filme = new Filme(rs.getInt("filmeId"), rs.getString("nome"), rs.getString("dataLancamento"), rs.getString("dataEncerramento"), rs.getString("hora"));
            filmes.add(filme);
        }
        conexao.desconectar();

        return filmes;
    }

    public List<Sala> getSalasList() throws SQLException {
        conexao.conectar();
        List<Sala> salas = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM Sala";
        PreparedStatement statement = conexao.criaPreparedStatment(sql);
        rs = statement.executeQuery();

        while (rs.next()) {
            Sala sala = new Sala(rs.getInt("salaId"));
            salas.add(sala);
        }

        conexao.desconectar();

        return salas;
    }

    public int selectSalaFilme(int filmeId) {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;
        int id = -1;

        String query = "SELECT nome, salaId FROM Filme WHERE filmeId=" + filmeId + ";";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("salaId");
            }
            return id;

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return id;
    }

    public String selectHorarioFilme(int filmeId) {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;
        String id = null;

        String query = "SELECT * FROM Filme WHERE filmeId=" + filmeId + ";";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id = rs.getString("hora");
            }
            return id;

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return id;
    }

    public int selectIDHorario(String horario) {
        conexao.conectar();
        ResultSet rs;
        Statement stmt;
        int id = 0;
        String query = "SELECT horarioId FROM Horario WHERE horario=" + horario + ";";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("horarioId");
                StaticHoraID.horaId = id;
            }
            return id;

        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }

        return id;
    }

    public ArrayList selectHistorico() {
        ArrayList historico = new ArrayList();
        conexao.conectar();
        ResultSet rs;
        Statement stmt;
        String query = "SELECT * FROM VendaIngresso;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String vendaId = "" + rs.getInt("vendaId");
                String nomeFunc = "" + rs.getString("nomeFuncionario");
                String dataVenda = "" + rs.getString("dataVenda");
                String valorTotal = "" + rs.getInt("valorIngressosFinal");
                historico.add(vendaId);
                historico.add(nomeFunc);
                historico.add(dataVenda);
                historico.add(valorTotal);
            }
            return historico;

        } catch (SQLException e) {
            System.err.println("ERRO HISTORICO " + e);
        } finally {
            conexao.desconectar();
        }
        return historico;
    }

    public String ultimoLogin() {
        conexao.conectar();
        String nome = "";
        ResultSet rs;
        Statement stmt;
        String query = "SELECT * FROM login;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                nome = rs.getString("login");
            }
            return nome;
            
        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }
        return "";
    }
    
    public ArrayList getHistorico(){
        ArrayList<String> historico = new ArrayList();
        conexao.conectar();
        ResultSet rs;
        Statement stmt;
        String query = "SELECT vendaId,filmeId,valorIngressoTotal FROM vendaIngresso;";

        stmt = conexao.criaStatment();

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                historico.add(""+rs.getString("nomeFuncionario"));
                historico.add(""+rs.getInt("vendaId"));
                historico.add("" + rs.getInt("filmeId"));
                historico.add(""+rs.getInt("valorIngressoTotal"));
            }
            
            return historico;
            
        } catch (SQLException e) {
            System.err.println("ERRO" + e);
        } finally {
            conexao.desconectar();
        }    
        return null;
    }

}
