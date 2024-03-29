package controller.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.DAO.DaoUsuario;
import model.entidades.Usuario;
import utils.SessionUtil;

@ManagedBean
public class BeanUsuario {
    
    private int id;
    private String nome;
    private String username;
    private String senha;
    
    public List<SelectItem> select() {
        List<SelectItem> itens = new ArrayList<>();
        
        ResultSet rs = DaoUsuario.getAll();
        try {
            while(rs.next()) {
                itens.add(new SelectItem(rs.getInt("idusuario"), rs.getString("nome")));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os usuários: " + ex.getMessage());
        }
        
        return itens;
    }
    
    public void cadastrar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.isEmpty()) {
            msg = new FacesMessage("Informe nome");
            context.addMessage(null, msg);
        }

        if (username.isEmpty()) {
            msg = new FacesMessage("Informe username");
            context.addMessage(null, msg);
        }

        if (senha.isEmpty()) {
            msg = new FacesMessage("Informe senha");
            context.addMessage(null, msg);
        }

        if (msg == null) {
            Usuario usuario = new Usuario(nome, username, senha);
            if (DaoUsuario.cadastrar(usuario)) {
                msg = new FacesMessage("Usuário salvo com sucesso");
                context.addMessage(null, msg);
            }
        }
    }
    
    public String logar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;
        
        if (username.isEmpty()) {
            msg = new FacesMessage("Informe username");
            context.addMessage(null, msg);
        }

        if (senha.isEmpty()) {
            msg = new FacesMessage("Informe senha");
            context.addMessage(null, msg);
        }
        
        if (msg == null) {
            Usuario usuario = new Usuario(nome, username, senha);
            if (DaoUsuario.logar(usuario)) {
                usuario.setId(DaoUsuario.getIdByUsername(username));
                SessionUtil.setParam("usuarioLogado", usuario);
                return "menu.jsf";
            }
        }
        msg = new FacesMessage("Login e/ou senha incorretos");
        context.addMessage(null, msg);
        return "login.jsf";
    }
    
    public String deslogar() {
        SessionUtil.remove("usuarioLogado");
        return "login.jsf";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}