package com.example.sistemasdistribuidos;

import java.io.PrintWriter;
import java.util.List;

public class CpfCnpj implements Runnable {
	
    
	public PrintWriter gravarArq;
    public Numeros cpf;
    public List<Numeros> lista;

    
    public CpfCnpj(Numeros cpf, List<Numeros> lista, PrintWriter gravarArq){
        this.cpf=cpf;
        this.gravarArq=gravarArq;
        this.lista=lista;
    }

	@Override
	public void run() {
		
		String valida = cpf.getNumero();
   	 	valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
   	 	
   	 	//Verifica se o numero é um CPF ou um CNPJ
        boolean validaCpf = verificaEhCpf(valida);
        
        if(validaCpf) {
       	 VerificadorCpf verificarCPF = new VerificadorCpf();
       	 String numero = verificarCPF.obterNumeracaoCPF(valida);
       	 gravaLista(cpf, lista, numero);

        }else {
       	 VerificadorCnpj verificadorCnpj = new VerificadorCnpj();
       	 String numero = verificadorCnpj.obterNumeracaoCNPJ(valida);
       	 gravaLista(cpf, lista, numero);
  	 
       }           
	
	}
	
	private synchronized void gravaLista(Numeros cpf2, List<Numeros> lista2, String numero) {
		lista.set(cpf2.getIndice(), new Numeros(numero, cpf.getIndice()));
	}

	private boolean verificaEhCpf(String numero) {
   	 return numero.length() == 9;
   	 
    }

}
