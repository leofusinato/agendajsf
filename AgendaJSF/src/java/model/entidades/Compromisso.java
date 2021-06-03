package model.entidades;

import java.util.Date;

public class Compromisso {
    
    private int id;
    private String descricao;
    private String local;
    private Date data;
    private Contato contato;
    private Usuario usuario;

    public Compromisso() {}

    public Compromisso(String descricao, String local, Date data, int idContato, int idIsuario) {
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.getContato().setIdcontato(idContato);
        this.getUsuario().setId(idIsuario);
    }
    
    public Compromisso(int id, String descricao, String local, Date data, int idContato, int idIsuario) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.getContato().setIdcontato(idContato);
        this.getUsuario().setId(idIsuario);
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
    
}