package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entidades.Compromisso;
import utils.Conexao;

public class DaoCompromisso {
    
    public static boolean cadastrar(Compromisso compromisso) {
        try {
            Connection conexao = Conexao.conectar();
            String sql = "INSERT INTO compromisso(\n" +
                                " descricao, local, data, idcontato, usuario)\n" +
                                " VALUES (?, ?, ?, ?, ?);";
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
    
}