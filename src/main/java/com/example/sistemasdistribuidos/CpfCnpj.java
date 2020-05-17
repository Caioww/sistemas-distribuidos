package com.example.sistemasdistribuidos;

import java.io.PrintWriter;

public class CpfCnpj implements Runnable {
	
    
	public PrintWriter gravarArq;
    public String valida;
    
    public CpfCnpj(String valida,  PrintWriter gravarArq){
        this.valida=valida;
    }

	@Override
	public void run() {
		
      
		
   	 
   	 //Verifica se é Cpf, caso retorne falso é um Cnpj
        boolean validaCpf = verificaEhCpf(valida);
        
        
        if(validaCpf) {
       	 VerificadorCpf verificarCPF = new VerificadorCpf();
       	String numero = verificarCPF.obterNumeracaoCPF(valida);
       	 gravarArq.println(numero);
       	 
        }else {
       	 VerificadorCnpj verificadorCnpj = new VerificadorCnpj();
       	String numero = verificadorCnpj.obterNumeracaoCNPJ(valida);
       	gravarArq.println(numero);
       	 
        }
       
		
		
	}
	
	static boolean verificaEhCpf(String numero) {
   	 return numero.length() == 9;
   	 
    }

}
