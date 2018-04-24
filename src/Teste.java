import java.util.Scanner;

public class Teste {
    
    static boolean zscore = false;
    static boolean rand = false;
    static int seed;
    static int k_fold;
    static int k_knn;
    static boolean debug;
    
    static void capturandoParametros() {
        
        String op;   // Opcoes digitadas
        Scanner scn; // Scanner de entrada
        
        scn = new Scanner(System.in);
        
        System.out.println("Entre com o valor de K do KNN:");
        k_knn = Integer.parseInt(scn.nextLine());
        
        System.out.println("Entre com o valor de K do KFOLD:");
        k_fold = Integer.parseInt(scn.nextLine());
        
        System.out.println("Deseja normalizar os dados? [Y/N]");
        op = scn.nextLine();
        
        if(op.toCharArray()[0] =='Y'|| op.toCharArray()[0]== 'y')
            zscore = true;
        else
            zscore = false;
        
        System.out.println("Deseja embaralhar a amostra? [Y/N]");
        op = scn.nextLine();
        if(op.toCharArray()[0] =='Y'|| op.toCharArray()[0]== 'y') {
            
            rand = true;
            System.out.println("Digite o seed:");
            op = scn.nextLine();
            seed = Integer.parseInt(op);
        }
        else
            rand = false;
        
        System.out.println("Deseja ativar o debug? [Y/N]");
        op = scn.nextLine();
        if(op.toCharArray()[0] =='Y'|| op.toCharArray()[0]== 'y')
            debug = true;
        else
            debug = false;
        scn.close();
        
    }//fim[capturandoParametros]
    
    static void relatorioInicial() {
        System.out.println("===============================");
        System.out.println("Valor do K (knn)\t" + k_knn);
        System.out.println("Valor do K (k-fold)\t" + k_fold);
        if(zscore)
            System.out.println("Normalizacao Ativada");
        else
            System.out.println("Normalizacao Desativada");
        
        if(rand)
            System.out.println("Sorteio Ativado.:Seed-->"+ seed);
        else
            System.out.println("Sorteio Desativado \t"+ seed);
        
        if(debug)
            System.out.println("Debug Ativado");
        else
            System.out.println("Debug Desativado");
        
        System.out.println("===============================");
        
    }//fim[relatorioInicial]

    public static void main(String[] args) {
        
        // Importando a base e os parametros principais
        Amostra am = new Amostra("src/base/dados4.txt");
        capturandoParametros();
        relatorioInicial();
        
        if(debug)
            am.exibeAmostra("Dados importados do arquivo", true);
        
        KNN knn = new KNN(am, k_knn);   // Importando a base para o KNN
        if(zscore) {                    // Usar o Zscore?
            knn.zScore();
            if(debug)
                knn.amostraExp.exibeAmostra("Dados normalizados", true);
        }
        
        if(rand) {                      // Embaralhar a amostra?
            knn.embaralharAmostra(seed);
            if(debug)
                knn.amostraExp.exibeAmostra("Dados embaralhados", true);
        }
        
        // Distribuindo as Instâncias nos respectivos folds
        knn.amostraExp.redistribuirAmostras();
        if(debug)
            knn.amostraExp.exibeAmostra("Dados pos distribuicao", true);
        
        
        Kfold kfold = new Kfold(k_fold, knn);
        kfold.dividirFolds(debug);
        kfold.avaliarKNN(debug);
        
    }//fim[MAIN]
    

}//fim[Teste]