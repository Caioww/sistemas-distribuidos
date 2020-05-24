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

         //Adicionando numeros do arquivo em um array
         while ((linha = reader.readLine()) != null) {
		     String[] dadosUsuario = linha.split(VIRGULA);
		     lista.add(dadosUsuario);
		    
		}
         
         //Adicionando dados do array lista para um array que recebe objetos para armazenar posição
         List<Numeros> numeros = new ArrayList<Numeros>();
         for(int i=0; i<lista.size(); i++) {
        	 numeros.add(new Numeros(Arrays.toString(lista.get(i)), i));
         }
         
         //Abrindo arquivo para escrita
		 filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
		 PrintWriter gravarArq = new PrintWriter(filew);
		 
         long start = System.currentTimeMillis();
         
         //Instanciando Thread de gerenciamento
    	 Thread t1 = new Thread(new Runnable() {
	             @Override
	             public void run() {	
	            	//Fila de 4 Threads pré instanciadas
	            	ExecutorService executor = Executors.newFixedThreadPool(4);
	            	//Para cada numero a Thread que estiver disponível faz o cálculo desse número
		            for( Numeros str : numeros) {
		            	Runnable CpfCnpj = new CpfCnpj(str, numeros, gravarArq);
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

	     //Gravar numeros em novo arquivo após a Thread t1 terminar a execução.
	     for(Numeros str:numeros) {
	    	 gravarArq.println(str.getNumero());
	     }
	     
	     filew.close();

	     long tempoFinal = System.currentTimeMillis();
	     System.out.printf("Tempo Final: %.3f ms%n", (tempoFinal - start) / 1000d);
         
     }

     
}