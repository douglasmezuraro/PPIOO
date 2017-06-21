package Controladores;

import Entidades.Cor;
import Entidades.Pais;
import Entidades.Validador;

// Trata as exceçoes do código como erros do usuario de escrita ou entre outros
public class ControladorDeValidacao {
    
    // Definições de constantes utéis nos métodos abaixos
    public final String C_ALTERNATIVA_SIM = "SIM";
    public final String C_ALTERNATIVA_NAO = "NAO";
    public final String C_EMPTY_STRING = "";
    public final String C_DIGITE_NOVAMENTE = " Digite novamente:";
    
    // Implementações dos métodos de validações
    
    public Validador validaCor(String stringCor) {
        try {
            Cor cor = Cor.valueOf(stringCor.toUpperCase());
            return new Validador(true, this.C_EMPTY_STRING);
        } catch (IllegalArgumentException e) {
            return new Validador(false, "Cor inválida!" + this.C_DIGITE_NOVAMENTE);
        }                 
    }    
    
    public Validador validaPais(String stringPais) {
        try {
            Pais pais = Pais.byName(stringPais);
            return new Validador(true, this.C_EMPTY_STRING);
        } catch (IllegalArgumentException e) {
            return new Validador(false, "País inválido!" + this.C_DIGITE_NOVAMENTE);
        }         
    }
    
    public Validador validaQuantidade(String stringQuantidade) {
        try {
            int quantidade = Integer.valueOf(stringQuantidade);
            
            if(quantidade < 1)
                return new Validador(false, "Quantidade inválida! Valor negativo.");
            
            return new Validador(true, this.C_EMPTY_STRING);
        } catch (NumberFormatException e) {
            return new Validador(false, "Quantidade inválida!" + this.C_DIGITE_NOVAMENTE);
        }          
    }
    
    public Validador validaDificuldade(String stringDificuldade) {
        try {
            int quantidade = Integer.valueOf(stringDificuldade);
            
            if(quantidade < 1 || quantidade > 3)
                return new Validador(false, "Dificuldade inválida!" + this.C_DIGITE_NOVAMENTE);
            
            return new Validador(true, this.C_EMPTY_STRING);
        } catch (NumberFormatException e) {
            return new Validador(false, "Dificuldade inválida!" + this.C_DIGITE_NOVAMENTE);
        }             
    }
    
    public Validador validaContinuarJogando(String stringAlternativa) {        
        try {
            int aux = Integer.valueOf(stringAlternativa);              

            if(aux == 0 || aux == 1)
                return new Validador(true, C_EMPTY_STRING);
            else 
                return new Validador(false, "Alternativa inválida!" + this.C_DIGITE_NOVAMENTE);            
        } catch (NumberFormatException e) {
            return new Validador(false, "Alternativa inválida!" + this.C_DIGITE_NOVAMENTE);
        }             
    }
            
}
