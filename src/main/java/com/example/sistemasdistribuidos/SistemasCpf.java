package com.example.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
   

    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {
	            long start = System.currentTimeMillis();
	            final ExecutorService executor = Executors.newFixedThreadPool(3);
		            for( String[] str : lista) {
		            	executor.submit(new CpfCnpj(str, filew));
		            	//executor.submit(retornaNumero(str, gravarArq));
		            }
		            executor.shutdown();
		            while (!executor.isTerminated()) {
		            }
		            System.out.println("Finished all threads");
			    				
				long tempoFinal = System.currentTimeMillis();
				
				
			    System.out.printf("Primeira Thread: %.3f ms%n", (tempoFinal - start) / 1000d);
				
		     }
    	 });
    	 
    	 t1.start();
	     
	     t1.join();
	     
	     filew.close();

	     System.out.printf("Finalizando tudo");
	     
	    ;
         
     }
     
     private static Runnable retornaNumero(String[] str, PrintWriter gravarArq) {
    	 Runnable runnable = new Runnable() {
	    	
			@Override
			public void run() {
				String valida = Arrays.toString(str);
		    	 valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
		    	 
		    	 //Verifica se é Cpf, caso retorne falso é um Cnpj
		         boolean validaCpf = verificaEhCpf(valida);
		         
		         String numero;
		         if(validaCpf) {
		        	 VerificadorCpf verificarCPF = new VerificadorCpf();
		        	 numero = verificarCPF.obterNumeracaoCPF(valida);
		        	 gravarArq.println(numero);
		        	 
		         }else {
		        	 VerificadorCnpj verificadorCnpj = new VerificadorCnpj();
		        	 numero = verificadorCnpj.obterNumeracaoCNPJ(valida);
		        	 gravarArq.println(numero);
		         }
				
			}
    	 };
	         
    	 return runnable;
   
    }	
     
     private static synchronized void gravaNoArquivo(String n,PrintWriter gravarArq) {
         gravarArq.println(n);
     }
 
     static boolean verificaEhCpf(String numero) {
    	 return numero.length() == 9;
    	 
     }
     
}