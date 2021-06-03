package controller.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.DAO.DaoContato;
import model.entidades.Contato;
import model.entidades.Usuario;
import utils.SessionUtil;

@ManagedBean
@ViewScoped
public class BeanContato {

    private int idcontato;
    private String nome;
    private String fone;
    private String email;
    private Usuario usuario;
    private String filtro = "";
    private List<Contato> lista = new ArrayList<>();
   
    public String editar(Contato ct){
       return "editacontato.jsf?faces-redirect=true&idcontato="+ct.getIdcontato();
    }
    
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
    
    public void excluir(Contato ct){
       DaoContato.excluir(ct.getIdcontato());
    }
    
    public void getById(int id){
      ResultSet rs = DaoContato.getById(id);
        try {
            if(rs.next()){
               idcontato = rs.getInt("idcontato");
               nome = rs.getString("nome");
               fone =  rs.getString("fone");
               email = rs.getString("email");
            } 
        } catch (SQLException ex) {
            throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
    }
    
    public void getAll(){
        lista.clear();
        try {
            ResultSet rs = DaoContato.getAll(filtro);
            while(rs.next()){
               lista.add(new Contato(
                       rs.getInt("idcontato"),
                       rs.getString("nome"),
                       rs.getString("fone"),
                       rs.getString("email"),
                       rs.getInt("usuario")));
            }
        } catch (SQLException ex) {
           throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
    }
    
    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.isEmpty()) {
            msg = new FacesMessage("Informe nome");
            context.addMessage(null, msg);
        }

        if (fone.isEmpty()) {
            msg = new FacesMessage("Informe fone");
            context.addMessage(null, msg);
        }

        if (email.isEmpty()) {
            msg = new FacesMessage("Informe email");
            context.addMessage(null, msg);
        }

        if (msg == null) {
            Usuario user = (Usuario) SessionUtil.getParam("usuarioLogado");
            Contato contato = new Contato(this.nome, fone, email, user.getId());
            if (DaoContato.salvar(contato)) {
                msg = new FacesMessage("Contato salvo com sucesso");
                context.addMessage(null, msg);
            }
        }

    }
    
    public void atualizar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (nome.isEmpty()) {
            msg = new FacesMessage("Informe nome");
            context.addMessage(null, msg);
        }

        if (fone.isEmpty()) {
            msg = new FacesMessage("Informe fone");
            context.addMessage(null, msg);
        }

        if (email.isEmpty()) {
            msg = new FacesMessage("Informe email");
            context.addMessage(null, msg);
        }
        
        if (msg == null) {
            Usuario user = (Usuario) SessionUtil.getParam("usuarioLogado");
            Contato contato = new Contato(idcontato, nome, fone, email, user.getId());
            if (DaoContato.atualizar(contato)) {
                msg = new FacesMessage("Contato atualizado com sucesso");
                context.addMessage(null, msg);
            }
        }
    }

    public int getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(int idcontato) {
        this.idcontato = idcontato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Contato> getLista() {
        return lista;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}