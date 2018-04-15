
public class Kfold {
    
    
    int k;               // Número de Folds
    KNN knn;             // Dados do KNN e amostras
    int tamKs[];         // Tamanho individual de cada fold
    int foldsTreinados[];// Relação de qual fold é o teste
    
    
    // CONSTRUTORES
    
    public Kfold(int k, KNN knn) {
        
        this.k = k;
        this.knn = knn; 
        this.tamKs = new int[k];
        this.foldsTreinados = new int[k];
        
    }//fim[construtor]
    
    // MÉTODOS
    
    public void dividirFolds() {
        
        int i, j;                   // Variavel para laços
        int aux;                    // Inteiro auxiliar
       
        // Se divisao for exata
        if(knn.amostraExp.quantInstancias % k == 0) {
            
            System.out.println("Divisão exata");
            
            for(i = 0; i < k; i++) {
                tamKs[i] = knn.amostraExp.quantInstancias / k;
                
            }//fim[for]
        }
        
        // Senão: Folds heterogêneos
        else {
            
            System.out.println("Folds desiguais");
            
            tamKs = new int[k];
            
            j = k -1;
            for(i = 0; i < j; i++) {
                //System.out.println("Divisao: " + Math.round((float)knn.amostraExp.quantInstancias / k));
                tamKs[i] = Math.round((float) knn.amostraExp.quantInstancias / k);
                
            }//fim[for]
            
            aux = Math.round((float) knn.amostraExp.quantInstancias / k)*(k-1);
            tamKs[i] = knn.amostraExp.quantInstancias-aux;
        }
        
        System.out.println("Tamanho dos Folds");
        for(i = 0; i < k; i++) {
            System.out.println("Fold " + (i+1) + ":" + tamKs[i]);
            
        }//fim[for]
        
        
    }//fim[dividirFolds]
    
    public void avaliarKNN() {
        
        int i, j, l;    // variveis para laços
        int e, d;       // intervalos do conjunto de treinamento
        
        // Setando o primeiro fold de teste (default)
        foldsTreinados[0] = 1;
        
        e = d= 0;
        
        // Procurando o conjunto de teste
        for(i = 0; i < k; i++) {
            
            // Dentro do conjunto de treinamento...
            if(foldsTreinados[i] == 1) {
               d = e + (tamKs[i]-1);
               
               for(j = e; j <= d; j++) {
                   
                   for(l = 0; l < knn.amostraExp.quantInstancias; l++) {
                       
                       if(l >= e && l <= d) {
                           
                       }
                       else {
                           // Amostra na posicao j --> distancia com as demais
                           knn.distanciaEuclediana(knn.amostraExp.colecaoInstancia.get(j));
                           
                           // Ordenar somente as distâncias que estão sendo comparadas
                           
                           // Cuidado com o Empate!
                       }
                   }
               }
               
            }
            
            //System.out.println("Intervalos: e->"+ e + " d->" + d);
            
            // Atualizando novo conjunto de treinamento
            e = d+1;
            foldsTreinados[i] = 0;
            if(i != k-1)
                foldsTreinados[i+1] = 1;
                
        }//fim[for]
        
    }//fim[avaliarKNN]

}//fim[Kfold]
