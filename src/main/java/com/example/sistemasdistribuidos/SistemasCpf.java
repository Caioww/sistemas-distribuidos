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
   
         long start = System.currentTimeMillis();
    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {	
	            	ExecutorService executor = Executors.newFixedThreadPool(5);
		            for( String[] str : lista) {
		            	Runnable CpfCnpj = new CpfCnpj(str, gravarArq);
		            	executor.execute(CpfCnpj);
		            }
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