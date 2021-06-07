package controller.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.DAO.DaoCompromisso;
import model.entidades.Compromisso;
import model.entidades.Contato;
import model.entidades.Usuario;

@ManagedBean
public class BeanCompromisso {
    
    private int id;
    private String descricao;
    private String local;
    private Date data;
    private Contato contato;
    private Usuario usuario;
    private List<Compromisso> lista = new ArrayList<>();
    
    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (descricao.isEmpty()) {
            msg = new FacesMessage("Informe descrição");
            context.addMessage(null, msg);
        }

        if (local.isEmpty()) {
            msg = new FacesMessage("Informe local");
            context.addMessage(null, msg);
        }

        if (data.equals("")) {
            msg = new FacesMessage("Informe data");
            context.addMessage(null, msg);
        }
        
        if (contato.getIdcontato() <= 0) {
            msg = new FacesMessage("Informe contato");
            context.addMessage(null, msg);
        }
        
        if (usuario.getId() <= 0) {
            msg = new FacesMessage("Informe usuário");
            context.addMessage(null, msg);
        }
        
        if (msg == null) {
            Compromisso compromisso = new Compromisso(descricao, local, data, getContato().getIdcontato(), getUsuario().getId());
            if (DaoCompromisso.cadastrar(compromisso)) {
                msg = new FacesMessage("Compromisso salvo com sucesso");
                context.addMessage(null, msg);
            }
        }
    }
    
    public void excluir(Compromisso compromisso){
       DaoCompromisso.excluir(compromisso.getId());
    }
    
    public String editar(Compromisso compromisso){
       return "editacontato.jsf?faces-redirect=true&idcompromisso="+compromisso.getId();
    }
    
    public void getAll(){
        lista.clear();
        try {
            ResultSet rs = DaoCompromisso.getAll();
            while(rs.next()){
               lista.add(new Compromisso(rs.getInt("idcompromisso"), 
                                         rs.getString("descricao"), 
                                         rs.getString("local"),
                                         rs.getDate("data"),
                                         rs.getInt("idcontato"),
                                         rs.getInt("usuario")));
            }
        } catch (SQLException ex) {
           throw new RuntimeException("Erro de consulta: " + ex.getMessage());
        }
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Contato getContato() {
        if(contato == null) {
            contato = new Contato();
        }
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Usuario getUsuario() {
        if(usuario == null) {
            usuario = new Usuario();    
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Compromisso> getLista() {
        return lista;
    }
    
}
