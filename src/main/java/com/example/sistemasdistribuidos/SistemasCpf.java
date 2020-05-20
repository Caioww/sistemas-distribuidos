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
import java.util.concurrent.CountDownLatch;



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
   
         long start = System.currentTimeMillis();
    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {	
	            	try{
	            	CountDownLatch latch = new CountDownLatch(4);
	            	Thread t2 = new Thread(new CpfCnpj(lista1, gravarArq));
	            	Thread t3 = new Thread(new CpfCnpj(lista2, gravarArq));
	            	Thread t4 = new Thread(new CpfCnpj(lista3, gravarArq));
	            	Thread t5 = new Thread(new CpfCnpj(lista4, gravarArq));

	            	
	            	t2.start();
	            	t3.start();
	            	t4.start();
	            	t5.start();
	            	
	            	latch.await();
	                System.out.println("In Main thread after completion of 1000 threads");
		            }catch(Exception err){
		                err.printStackTrace();
		            }

	             }
    	 });
    	 
    	 t1.start(); 
	     t1.join();
	     
	     filew.close();

	     long tempoFinal = System.currentTimeMillis();
	     System.out.printf("Tempo Final: %.3f ms%n", (tempoFinal - start) / 1000d);
         
     }

     
}