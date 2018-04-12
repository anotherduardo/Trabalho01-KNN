import java.util.Scanner;

public class Teste {
    
    static boolean zscore = false;
    static int k;
    
    static void capturandoParametros() {
        Scanner scn = new Scanner(System.in); 
        
        System.out.println("Entre com o valor de K:");
        k = Integer.parseInt(scn.nextLine());
        
        System.out.println("Deseja normalizar os dados? [Y/N]");
        String op = scn.nextLine();
        
        if(op.toCharArray()[0] =='Y'|| op.toCharArray()[0]== 'y')
            zscore = true;
        else
            zscore = false;
        
        System.out.println("Valor de K:"+ k);
        if(zscore)
            System.out.println("Normalização Ativada");
        else
            System.out.println("Normalização Desativada");
        
        scn.close();
        
    }//fim[capturandoParametros]

    public static void main(String[] args) {
        
        Amostra am = new Amostra("src/base/dados2.txt");
        am.exibeAmostra();
        
        //capturandoParametros();
        System.out.println("Testes com KNN");
        KNN knn = new KNN(am);
        knn.zScore();
        
    }//fim[MAIN]
    

}//fim[Teste]