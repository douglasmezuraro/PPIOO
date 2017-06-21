package Entidades;

public class Validador {
    private boolean resultado;
    private String mensagem;

    public boolean deuCerto() {
        return this.resultado == true;
    }
    
    public boolean deuErro() {
        return this.resultado == false;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Validador(boolean resultado, String mensagem) {
        this.resultado = resultado;
        this.mensagem = mensagem;
    }
}
