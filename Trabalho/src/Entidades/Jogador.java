package Entidades;

public class Jogador {
    private Cor cor;    
    private int contingenteTerrestreDisponivel;
    private int contingenteAereoDisponivel;
    
    public Cor getCor() {
        return this.cor;
    }
    
    public void setCor(Cor cor) {
        this.cor = cor;
    }
    
    public int getContingenteTerrestreDisponivel() {
        return this.contingenteTerrestreDisponivel;
    }
    
    public void setContingenteTerrestreDisponivel(int contingenteTerrestreDisponivel) {
        this.contingenteTerrestreDisponivel = contingenteTerrestreDisponivel;
    }
    
    public int getContingenteAereoDisponivel() {
        return this.contingenteAereoDisponivel;
    }
    
    public void setContingenteAereoDisponivel(int contingenteAereoDisponivel) {
        this.contingenteAereoDisponivel = contingenteAereoDisponivel;
    }

    public boolean possuiContingenteTerrestreParaRemanejar() {
        return this.contingenteTerrestreDisponivel > 0;
    }
    
    public boolean possuiContingenteAereoParaRemanejar() {
        return this.contingenteAereoDisponivel > 0;
    }
    
}
