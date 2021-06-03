package model.entidades;

public class Compromisso {
    
    private int id;
    private String descricao;
    private String local;
    private String data;
    private Contato contato;
    private Usuario usuario;

    public Compromisso() {}

    public Compromisso(int id, String descricao, String local, String data, Contato contato, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.contato = contato;
        this.usuario = usuario;
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