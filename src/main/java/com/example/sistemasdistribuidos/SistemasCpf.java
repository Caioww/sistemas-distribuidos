package com.example.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    	 
    	 long start = System.currentTimeMillis();
    	 //Abrindo arquivo para leitura
         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
		 reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		 List<String[]> lista1 = Collections.synchronizedList(new ArrayList<>());
	     List<String[]> lista2 = Collections.synchronizedList(new ArrayList<>());
	     List<String[]> lista3 = Collections.synchronizedList(new ArrayList<>());
	     List<String[]> lista4 = Collections.synchronizedList(new ArrayList<>());
	        
         while ((linha = reader.readLine()) != null) {
        	 	String[] nome = linha.split(VIRGULA);
        	 	lista.add(nome);
        	 if(lista.size() <= 299999) {
            	 String[] dadosUsuario = linha.split(VIRGULA);
			     lista1.add(dadosUsuario);
             }else if (lista.size() > 299999 && lista.size() <= 599999) {
            	 String[] dadosUsuario = linha.split(VIRGULA);
			     lista2.add(dadosUsuario);          		 
             }else if(lista.size() > 599999 && lista.size() <= 899999) {
            	 String[] dadosUsuario = linha.split(VIRGULA);
			     lista3.add(dadosUsuario); 
             }else if(lista.size() > 899999 && lista.size() <= 1299999) {
            	 String[] dadosUsuario = linha.split(VIRGULA);
			     lista4.add(dadosUsuario); 
             }
         }
         
		    
         
       
        	 
      
         
         //Abrindo arquivo para escrita
		 filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
		 PrintWriter gravarArq = new PrintWriter(filew);
   
         
         Thread t1 = new Thread(new Runnable() {
             @Override
             public void run() {	
            	ExecutorService executor = Executors.newFixedThreadPool(4);
	            Runnable CpfCnpj = new CpfCnpj(lista1, gravarArq);
	            executor.execute(CpfCnpj);
	            
	            Runnable CpfCnpj1 = new CpfCnpj(lista2, gravarArq);
	            executor.execute(CpfCnpj1);
	            
	            Runnable CpfCnpj2 = new CpfCnpj(lista3, gravarArq);
	            executor.execute(CpfCnpj2);
	            
	            Runnable CpfCnpj3 = new CpfCnpj(lista4, gravarArq);
	            executor.execute(CpfCnpj3);
	           
            	executor.shutdown();
                while (!executor.isTerminated()) {
                }
                System.out.println("Finished all threads");

             }
	 });
	 
	 t1.start(); 
     t1.join();
    	 

	     filew.close();

	     long tempoFinal = System.currentTimeMillis();
	     System.out.printf("Tempo Final: %.3f ms%n", (tempoFinal - start) / 1000d);
         
     }

     
}