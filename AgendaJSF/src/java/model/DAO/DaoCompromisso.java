package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entidades.Compromisso;
import utils.Conexao;

public class DaoCompromisso {

    public static boolean cadastrar(Compromisso compromisso) {
        try {
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO compromisso(\n"
                    + " descricao, local, data, idcontato, usuario)\n"
                    + " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, compromisso.getDescricao());
            stm.setString(2, compromisso.getLocal());
            stm.setDate(3, new Date(compromisso.getData().getTime()));
            stm.setInt(4, compromisso.getContato().getIdcontato());
            stm.setInt(5, compromisso.getUsuario().getId());
            stm.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o compromisso: " + ex.getMessage());
        }
        return true;
    }

    public static ResultSet getAll() {
        ResultSet rs = null;
        try {
            Connection conexao = Conexao.conectar();
            String sql = "select * from compromisso";
            PreparedStatement stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
        return rs;
    }
    
    public static ResultSet getAllByData(Date data) {
        ResultSet rs = null;
        try {
            Connection conexao = Conexao.conectar();
            String sql = "select * from compromisso where data = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setDate(1, data);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
        return rs;
    }
    
    public static ResultSet getAllByContato(int idContato) {
        ResultSet rs = null;
        try {
            Connection conexao = Conexao.conectar();
            String sql = "select * from compromisso where idcontato = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, idContato);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
        return rs;
    }
    
    public static ResultSet getAllByDataAndContato(Date data, int idContato) {
        ResultSet rs = null;
        try {
            Connection conexao = Conexao.conectar();
            String sql = "select * from compromisso where idcontato = ? and data = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, idContato);
            stm.setDate(2, data);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
        return rs;
    }

    public static boolean excluir(int id) {
        try {
            Connection conexao = Conexao.conectar();
            String sql = "delete from compromisso "
                    + " where idcompromisso = ?";
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir compromisso: " + ex.getMessage());
        }
        return true;
    }

}
