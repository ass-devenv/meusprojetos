package br.com.gestao.util;

public class ValidatorCNPJ {

	 public static boolean ValidaCNPJ(String cnpj_){

         String CNPJ = cnpj_.replace(".", "");
         CNPJ = CNPJ.replace("/", "");
         CNPJ = CNPJ.replace("-", "");

         int[] digitos, soma, resultado;
         int nrDig;
         String ftmt;
         boolean[] CNPJOk;

         ftmt = "6543298765432";
         digitos = new int[14];
         soma = new int[2];
         soma[0] = 0;
         soma[1] = 0;
         resultado = new int[2];
         resultado[0] = 0;
         resultado[1] = 0;
         CNPJOk = new boolean[2];
         CNPJOk[0] = false;
         CNPJOk[1] = false;

         try
         {
             for (nrDig = 0; nrDig < 14; nrDig++)
             {
                 digitos[nrDig] = Integer.parseInt(CNPJ.substring(nrDig, 1));
                 
                 if (nrDig <= 11)                     
                	 soma[0] += (digitos[nrDig] * Integer.parseInt(ftmt.substring(nrDig + 1, 1)));
                 
                 if (nrDig <= 12)
                     soma[1] += (digitos[nrDig] * Integer.parseInt(ftmt.substring(nrDig, 1)));
               }

             for (nrDig = 0; nrDig < 2; nrDig++)
             {
                 resultado[nrDig] = (soma[nrDig] % 11);
                 
                 if ((resultado[nrDig] == 0) || (resultado[nrDig] == 1))
                     
                	 CNPJOk[nrDig] = (digitos[12 + nrDig] == 0);

                 else
                     CNPJOk[nrDig] = (digitos[12 + nrDig] == (11 - resultado[nrDig]));

             }

             return (CNPJOk[0] && CNPJOk[1]);

         }
         catch(Exception e)
         {
             return false;
         }

     }
	
	
}
