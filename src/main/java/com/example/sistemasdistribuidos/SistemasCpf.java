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

         //Adicionando numeros em um array
         while ((linha = reader.readLine()) != null) {
		     String[] dadosUsuario = linha.split(VIRGULA);
		     lista.add(dadosUsuario);
		    
		}
         
         
         //Abrindo arquivo para escrita
		 filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
		 PrintWriter gravarArq = new PrintWriter(filew);
		 
         long start = System.currentTimeMillis();
         
    	 
		 for( String[] str : lista) {
			 	
			 	String valida = Arrays.toString(str);
		   	 	valida = valida.substring(1, valida.length()-1).replace("]", "").replace("[", "").replaceAll("\\s+","");
		   	 	
		   	 	//Verifica se o numero é um CPF ou um CNPJ
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

	     filew.close();

	     long tempoFinal = System.currentTimeMillis();
	     System.out.printf("Tempo Final: %.3f ms%n", (tempoFinal - start) / 1000d);
         
     }
     
     private static boolean verificaEhCpf(String numero) {
       	 return numero.length() == 9;
       	 
        }

     
}