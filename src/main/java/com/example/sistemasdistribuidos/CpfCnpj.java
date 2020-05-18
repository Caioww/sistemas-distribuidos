package com.example.sistemasdistribuidos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class CpfCnpj implements Runnable {
	
    
	public FileWriter filew;
    public String[] cpf;
    
    public CpfCnpj(String [] cpf, FileWriter filew){
        this.cpf=cpf;
        this.filew=filew;
    }

	@Override
	public void run() {
		
		PrintWriter gravarArq = new PrintWriter(filew);
		String valida = Arrays.toString(cpf);
   	 	valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
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
