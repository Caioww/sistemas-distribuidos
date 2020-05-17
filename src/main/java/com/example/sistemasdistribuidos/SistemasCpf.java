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
    
    private static final List<String[]> lista = Collections.synchronizedList(new ArrayList<>());
    

     public static void main(String[] args) throws Exception {
    	 long start = System.currentTimeMillis();
 
    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {
		    	//Abrindo arquivo para escrita
		         FileWriter filew = null;
				try {
					filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         PrintWriter gravarArq = new PrintWriter(filew);
		    	 
		    	 //Abrindo arquivo para leitura
		         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
		         try {
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         String linha = null;
		         
		         try {
					while ((linha = reader.readLine()) != null) {
					     String[] dadosUsuario = linha.split(VIRGULA);
					     lista.add(dadosUsuario);
					    
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	      
		         for( String[] str : lista) {
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
		             gravaNoArquivo(numero, gravarArq);    
		    	 }

				try {
					filew.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		     }
    	 });
    	 
    	 Thread t2 = new Thread(new Runnable() {
             @Override
             public void run() {
	    	//Abrindo arquivo para escrita
	         FileWriter filew = null;
			try {
				filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         PrintWriter gravarArq = new PrintWriter(filew);
	    	 
	    	 //Abrindo arquivo para leitura
	         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
	         try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         String linha = null;
	         
	         try {
				while ((linha = reader.readLine()) != null) {
				     String[] dadosUsuario = linha.split(VIRGULA);
				     lista.add(dadosUsuario);
				    
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	      
	         for( String[] str : lista) {
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
	             gravaNoArquivo(numero, gravarArq);    
	    	 }

			try {
				filew.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     }
	 });
    	 
    	 Thread t3 = new Thread(new Runnable() {
             @Override
             public void run() {
	    	//Abrindo arquivo para escrita
	         FileWriter filew = null;
			try {
				filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         PrintWriter gravarArq = new PrintWriter(filew);
	    	 
	    	 //Abrindo arquivo para leitura
	         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
	         try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         String linha = null;
	         
	         try {
				while ((linha = reader.readLine()) != null) {
				     String[] dadosUsuario = linha.split(VIRGULA);
				     lista.add(dadosUsuario);
				    
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	      
	         for( String[] str : lista) {
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
	             gravaNoArquivo(numero, gravarArq);    
	    	 }

			try {
				filew.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	     }
	 });
	    	 
	     t1.start();
	     t2.start();
	     t3.start();
	  
	     
	     long tempoFinal = System.currentTimeMillis();

	     System.out.printf("%.3f ms%n", (tempoFinal - start) / 1000d);
	     
	     
	     
     }
     
     private synchronized static void gravaNoArquivo(String n,PrintWriter gravarArq) {
         gravarArq.println(n);
     }
 
     static boolean verificaEhCpf(String numero) {
    	 return numero.length() == 9;
    	 
     }
     
}