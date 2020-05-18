package com.example.sistemasdistribuidos;

import java.io.PrintWriter;
import java.util.Arrays;

public class CpfCnpj implements Runnable {
	
    
	public PrintWriter gravarArq;
    public String[] cpf;
    
    public CpfCnpj(String [] cpf, PrintWriter gravarArq){
        this.cpf=cpf;
        this.gravarArq=gravarArq;
    }

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName()+" Start. Command = "+ Arrays.toString(cpf));	
		String valida = Arrays.toString(cpf);
   	 	valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
        boolean validaCpf = verificaEhCpf(valida);
        
        
        if(validaCpf) {
       	 VerificadorCpf verificarCPF = new VerificadorCpf();
       	 String numero = verificarCPF.obterNumeracaoCPF(valida);
       	 gravaNoArquivo(numero, gravarArq);
       	 
        }else {
       	 VerificadorCnpj verificadorCnpj = new VerificadorCnpj();
       	 String numero = verificadorCnpj.obterNumeracaoCNPJ(valida);
       	 gravaNoArquivo(numero, gravarArq);
       	 
        }
        System.out.println(Thread.currentThread().getName()+" End.");
       
       
		
		
	}
	

	   private static synchronized void gravaNoArquivo(String n,PrintWriter gravarArq) {
	         gravarArq.println(n);
	     }
	
	static boolean verificaEhCpf(String numero) {
   	 return numero.length() == 9;
   	 
    }

}
