package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Territorio {
    private Pais pais;
    private Cor cor;
    private Continente continente;
    private List<Terrestre> terrestres = new ArrayList<>();
    private List<Aereo> aereos = new ArrayList<>();
    
    public Pais getPais() {
        return this.pais;
    }
    
    public Continente getContinente() {
        return this.continente;
    }
    
    public Cor getCor() {
        return this.cor;
    }
    
    public List<Terrestre> getTerrestres() {
        return this.terrestres;
    }
    
    public List<Aereo> getAereos() {
        return this.aereos;
    }
    
    public void setPais(Pais pais) {
        this.pais = pais;
    } 
    
    public void setContinente(Continente continente) {
        this.continente = continente;
    }
    
    public void setCor(Cor cor) {
        this.cor = cor;
    }
    
    public void adicionaTerrestre(int quantidade) {
        for(int i = 0; i < quantidade; i++) {
            this.addTerrestre(new Terrestre());
        }
    }
    
    private void addTerrestre(Terrestre terrestre) {
        this.terrestres.add(terrestre);
    }
    
    public void removeTerrestre(int quantidade) {
        int ultimo = this.terrestres.size() - 1;
        for(int i = 0; i < quantidade; i++) {
            this.delTerrestre(this.terrestres.get(ultimo));
        }
    }
    
    public void delTerrestre(Terrestre terrestre) {
        this.terrestres.remove(terrestre);
    }
    
    public void adicionaAereo(int quantidade) {
        for(int i = 0; i < quantidade; i++) {
            this.addAereo(new Aereo());
        }
    }
    
    private void addAereo(Aereo aereo) {
        this.aereos.add(aereo);
    }
    
    public void removeAereo(Aereo aereo) {
       this.aereos.remove(aereo);
    }
}
