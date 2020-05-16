package com.example.sistemasdistribuidos;

import org.springframework.stereotype.Component;

@Component
public class VerificadorCnpj {
	
	   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	    String obterNumeracaoCNPJ(String cnpj) {

	      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
	      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
	      return cnpj.substring(0,12) + digito1.toString() + digito2.toString();
	   }

	}