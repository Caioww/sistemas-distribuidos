package com.example.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SistemasCpf {
  
	private static final String VIRGULA = "\n";
    private static BufferedReader reader;
    private static String linha = null;
    private static FileWriter filew = null;
    
    private static final List<String[]> lista = Collections.synchronizedList(new ArrayList<>());
    

     public static void main(String[] args) throws Exception {
    	 
    	 
    	 
    	 //Abrindo arquivo para leitura
         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
		 reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

         
         while ((linha = reader.readLine()) != null) {
		     String[] dadosUsuario = linha.split(VIRGULA);
		     lista.add(dadosUsuario);
		    
		}
         
         //Abrindo arquivo para escrita
		 filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
         PrintWriter gravarArq = new PrintWriter(filew);
   

    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {
	            long start = System.currentTimeMillis();
		            for( String[] str : lista) {
				        	 String numero = retornaNumero(str);
				             gravaNoArquivo(numero, gravarArq);  		        	
		            }
			    				
				long tempoFinal = System.currentTimeMillis();
				
				
			    System.out.printf("Primeira Thread: %.3f ms%n", (tempoFinal - start) / 1000d);
				
		     }
    	 });
    	 
    	 Thread t2 = new Thread(new Runnable() {
             @Override
             public void run() {
	            long start = System.currentTimeMillis();
	            for( String[] str : lista) {
			        	 String numero = retornaNumero(str);
			             gravaNoArquivo(numero, gravarArq);  
	            }
		    	
				
			long tempoFinal = System.currentTimeMillis();
			
		    System.out.printf("Segunda Thread:%.3f ms%n", (tempoFinal - start) / 1000d);
			
	     }
	 });
    	
    	 t1.start();
	     t2.start();
	     
	     t1.join();
	     t2.join();
	  
	     
	     filew.close();
         
     }
     
     private static String retornaNumero(String[] str) {
    	 String valida = Arrays.toString(str);
    	 valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
    	 
    	 //Verifica se é Cpf, caso retorne falso é um Cnpj
         boolean validaCpf = verificaEhCpf(valida);
         
         String numero;
         if(validaCpf) {
        	 VerificadorCpf verificarCPF = new VerificadorCpf();
        	 numero = verificarCPF.obterNumeracaoCPF(valida);
         }else {
        	 VerificadorCnpj verificadorCnpj = new VerificadorCnpj();
        	 numero = verificadorCnpj.obterNumeracaoCNPJ(valida);
	    
         } 
         return numero;
     }
     
     private static synchronized void gravaNoArquivo(String n,PrintWriter gravarArq) {
         gravarArq.println(n);
     }
 
     static boolean verificaEhCpf(String numero) {
    	 return numero.length() == 9;
    	 
     }
     
}