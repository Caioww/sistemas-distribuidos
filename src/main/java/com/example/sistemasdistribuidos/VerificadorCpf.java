package com.example.sistemasdistribuidos;

import org.springframework.stereotype.Component;


@Component
public class VerificadorCpf {


		static int numeracao[] = new int[11];
		
		String obterNumeracaoCPF(String cpf){
			short contador = 0;
			
			if(cpf.length() == 9){
				for(short i = 0; i < cpf.length(); i++){
					if(Character.isDigit(cpf.charAt(i))){
						numeracao[contador++] = Character.digit(cpf.charAt(i), 10);
					}
				}
				obterDiv(obterSomatorio1(), 9);
	            obterDiv(obterSomatorio2(), 10);
	            return formataCpf(cpf);
			}else{
				System.out.print("Informe os 9 primeiros dígitos do CPF.");
			}
			return cpf;
		}
		
		 static String formataCpf(String numero) {
	    	 return numero + numeracao[9] + numeracao[10];
	     }
		
		int obterSomatorio1(){
			int somatorio = 0;
			int[] multiplicadores = {10, 9, 8, 7, 6, 5, 4, 3, 2};
			
			for(short i = 0; i < multiplicadores.length; i++){
				somatorio += (numeracao[i] * multiplicadores[i]);
			}
			
			return somatorio;
		}
		
		int obterSomatorio2(){
			int somatorio = 0;
			int[] multiplicadores = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
			
			for(short i = 0; i < multiplicadores.length; i++){
				somatorio += (numeracao[i] * multiplicadores[i]);
			}
			
			return somatorio;
		}
		
		void obterDiv(int soma, int t){
			int modulo;
			int resto;
			
			modulo = soma % 11;
			resto = 11 - modulo;
			
			if(resto > 9){
				if(t == 9)
					numeracao[9] = 0;
				
				else if(t == 10)
					numeracao[10] = 0;
				
			}else{
				if(t == 9)
					numeracao[9] = resto;
				
				else if(t == 10)
					numeracao[10] = resto;
			}
		}
		
		
	

}
