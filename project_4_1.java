import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class project_4_1
{

    public static class Global
    {   
        public static int array[];
        public static int n;

        public static void cria_array(int x , int y){
            n = x*y;
            array = new int[n];
        }
    } 

    static String le_parametros(Scanner sc)
    {
        String str;  
            
        try{
                
        str = sc.nextLine();

        }
        //Se o valor for um valor causar um erro, ira ser avisado ao usuario que o valor nao e valido.
        catch (java.util.InputMismatchException e){
            System.out.printf("Valor Introduzido nao e valido.");
            return null;
        }
        
        return str;
    }

    //permite receber o input do usuario e verificar qual a opcao inserida.
    static void opcoes(Scanner sc){
    
    int fim = 0;

    while(fim == 0)
    {

        String[] parametros;
        parametros = le_parametros(sc).split(" ");


        if(parametros[0].equals("RASTER")){
            
            int n,m;
            int indice = 0;

            n = Integer.parseInt(parametros[1]);
            m = Integer.parseInt(parametros[2]);

            Global.cria_array(n, m);

            for(int i = 0 ; i < n ; i++){

                parametros = le_parametros(sc).split(" ");

                for(int x = 0 ; x < m ; x++){
                    
                    Global.array[indice] = Integer.parseInt(parametros[x]);
                    indice++;

                }
            }
            System.out.println("RASTER GUARDADO");
        }
        
        if(parametros[0].equals("AMPLITUDE")){
            
            int max = Global.array[0];
            int min = Global.array[0];

            for(int x = 0 ; x < Global.n ; x++){

                if(Global.array[x] < min)
                    min = Global.array[x];

                if(Global.array[x] > max)
                    max = Global.array[x];

            }

            int amplitude = 0;

            amplitude = max-min;

            System.out.println(amplitude);

        }

        if(parametros[0].equals("PERCENTIL")){ 

            int valor = Integer.parseInt(parametros[1]);
            parametros = le_parametros(sc).split(" ");

            for(int c = 0 ; c < valor ; c++){

                int numero_limite = Integer.parseInt(parametros[c]);

                double contador = 0;
                double elementos = 0;

                for(int x = 0 ; x < Global.n ; x++){
                        
                    elementos++;

                    if(Global.array[x] < numero_limite){
                        contador++;
                    }

                }

                int percentil;
                percentil = (int) Math.floor((contador / elementos) * 100);

                if(c == valor -1)
                    System.out.println(percentil);
                else
                System.out.print(percentil + " ");
            }
        }

        if(parametros[0].equals("MEDIANA")){

            int tamanho = Global.array.length;
            ArrayList<Integer> novo_array = new ArrayList<Integer>();
            int mediana;

            for(int x = 0 ; x < tamanho ; x++)
                novo_array.add(Global.array[x]);

            while(!(tamanho < 3 ))
            {
                int indice_max = 0 , indice_min = 0;

                int max = novo_array.get(0);   
                int min = novo_array.get(0);

                for(int x = 0 ; x < tamanho ; x++){

                    if(novo_array.get(x) < min){
                        indice_min = x;
                        min = novo_array.get(x);
                    }

                    if(novo_array.get(x) > max){
                        indice_max = x;
                        max = novo_array.get(x);
                    }
                }
                

                if(indice_max > indice_min)
                {
                    novo_array.remove(indice_max);
                    novo_array.remove(indice_min);
                }
                else
                {
                    novo_array.remove(indice_min);
                    novo_array.remove(indice_max);
                }

                tamanho = novo_array.size();
                
            }

            if(tamanho == 2)
                mediana = (novo_array.get(0) + novo_array.get(1)) / 2;
            else
                mediana = novo_array.get(0);

            System.out.println(mediana);
        }

        if(parametros[0].equals("TCHAU")){
                
            fim++;
            sc.close();

        }

        }
    }


    public static void main(String[] args) 
    {   
        Scanner sc = new Scanner(System.in);

        opcoes(sc);
        sc.close();
    }
}