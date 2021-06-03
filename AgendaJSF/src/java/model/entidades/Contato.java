package model.entidades;

public class Contato {

    private int idcontato;
    private String nome;
    private String fone;
    private String email;
    private Usuario usuario;

    public Contato() {
    }

    public Contato(String nome, String fone, String email, int idusuario) {
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        getUsuario().setId(idusuario);
    }

    public Contato(int idcontato, String nome, String fone, String email, int idusuario) {
        this.idcontato = idcontato;
        this.nome = nome;
        this.fone = fone;
        this.email = email;
        getUsuario().setId(idusuario);
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
        if(usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
