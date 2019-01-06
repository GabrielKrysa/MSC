/**
 * Pacote responsavel por realizar a a conexão e desconexão com o banco de dados.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsavel por conectar e finalizar a conexão com o banco de dados.
 *
 * @author krysa
 */
public class ConexaoSQLITE {

    private Connection conexao;

    /**
     * Conecta ao banco ou cria o banco se não existir.
     *
     * @return true ou false
     */
    public boolean conectar () {
        try {

            String url = "jdbc:sqlite:databaseSQLITE/msc.db";

            this.conexao = DriverManager.getConnection (url);
            System.out.println ("CONECTADO");
            return true;

        } catch (SQLException e) {

            System.err.println ("error" + e.getMessage ());

            return false;
        }

    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @return true ou false
     */
    public boolean desconectar () {
        try {

            if (this.conexao.isClosed () == false) {
                this.conexao.close ();
            }
            System.out.println ("DESCONECTOU");
        } catch (SQLException e) {

            System.err.println ("error" + e.getMessage ());
            return false;
        }
        return true;
    }

    /**
     * Criar os statements para executar os códigos SQL.
     *
     * @return Statement 
     */
    public Statement criaStatment () {
        try {

            return this.conexao.createStatement ();

        } catch (SQLException e) {

            System.err.println ("erro" + e.getMessage ());
            return null;
        }
    }

    /**
     * Criar os prepared statements para executar os códigos SQL.
     *
     * @param sql comando SQL que será executado pelo statement
     * @return prepared statement
     */
    public PreparedStatement criaPreparedStatment (String sql) {
        try {

            return this.conexao.prepareStatement (sql);

        } catch (SQLException e) {

            System.err.println ("erro" + e.getMessage ());
            return null;
        }
    }

    /**
     * Metodo responsavel por retornar a conexão do banco de dados.
     * @return uma conexão
     */
    public Connection getConexao () {
        return this.conexao;
    }
}
