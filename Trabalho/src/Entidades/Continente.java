package Entidades;

public enum Continente {
    AFRICA("África"),
    AMERICA_DO_NORTE("América do Norte"),
    AMERICA_DO_SUL("América do Sul"),
    ASIA("Ásia"),
    EUROPA("Europa"),
    OCEANIA("Oceania");
    
    public String nome;
    
    Continente(String nome) {
        this.nome = nome;
    }
};