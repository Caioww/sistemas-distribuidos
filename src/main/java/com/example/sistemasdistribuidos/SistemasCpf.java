package com.example.sistemasdistribuidos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SistemasCpf {
  
	private static final String VIRGULA = "\n";
    private static BufferedReader reader;
    
    
    
    
    



     public static void main(String[] args) throws Exception {
    	 
    	 List<String[]> lista = new ArrayList<String[]>();
    	 
    	//Abrindo arquivo para escrita
         FileWriter filew = new FileWriter(SistemasCpf.class.getResource("/arquivosCpfCnpj/CPFCNPJ.txt").getFile());
         PrintWriter gravarArq = new PrintWriter(filew);
    	 
    	 //Abrindo arquivo para leitura
         File file = new File(SistemasCpf.class.getResource("/arquivosCpfCnpj/BASEPROJETO.txt").getFile());
         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
         String linha = null;
         
         while ((linha = reader.readLine()) != null) {
             String[] dadosUsuario = linha.split(VIRGULA);
             lista.add(dadosUsuario);
            
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
             
             
             gravarArq.println(numero);
            
         }
             
         filew.close();
         
		
     }
     
     
   
     
     static boolean verificaEhCpf(String numero) {
    	 return numero.length() == 9;
    	 
     }
     
}