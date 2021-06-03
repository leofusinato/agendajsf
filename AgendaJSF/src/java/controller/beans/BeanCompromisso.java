package controller.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import model.DAO.DaoContato;
import model.entidades.Contato;
import model.entidades.Usuario;

@ManagedBean
public class BeanCompromisso {
    
    private int id;
    private String descricao;
    private String local;
    private String data;
    private Contato contato;
    private Usuario usuario;
    
    public List<SelectItem> select() {
        List<SelectItem> itens = new ArrayList<>();
        
        ResultSet rs = DaoContato.getAll("");
        try {
            while(rs.next()) {
                itens.add(new SelectItem(rs.getInt("idcontato"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os contatos: " + ex.getMessage());
        }
        
        return itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
