import java.util.ArrayList;
import java.util.Collections;

public class Kfold {
    
    
    int k_folds;           // Número de Folds
    KNN knn;               // Dados do KNN e amostras
    int mapaInstancias[];  // Mapeia teste e treinamento (indices)
    
    
    // CONSTRUTORES
    
    public Kfold(int k, KNN knn) {
        
        this.k_folds = k;
        this.knn = knn;
        mapaInstancias = new int[knn.amostraExp.quantInstancias];
        
    }//fim[construtor]
    
    // MÉTODOS
    
    // PRINCIPAL MÉTODO DO TRABALHO
    public void avaliarKNN(boolean debug) {
        
        int i, j, l;                      // Para laços
        int acertos;                      // Quantidade de Acertos
        int classeF;                      // Classe Final escolhida
        int quantInst;                    // Quantidade de Instâncias
        int knnAtual;                     // Valor atual do KNN(pode diminuir em empate)
        boolean empate;                   // Se o KNN obteve empate
        ArrayList<Instancia> listaDeComp; // Armazena uma cópia dos itens calculados
        ArrayList<KVizinho> contagemK;    // Armazena uma cópia dos itens calculados
        
        classeF = 0;
        acertos = 0;
        contagemK = new ArrayList<KVizinho>();
        listaDeComp = new ArrayList<Instancia>();
        quantInst = knn.amostraExp.quantInstancias;
       
        
        // LAÇOS
        
        for(i = 1; i <= k_folds; i++) {
            
            if(debug)
                System.out.println("Exetuando fold #:"+i);
            
            for(l = 0; l < quantInst; l++) {
            
                if(mapaInstancias[l] == i) {
                    
                    if(debug) {
                        System.out.println("Testando Instancia ID:"+
                            knn.amostraExp.colecaoInstancia.get(l).id + " classe " +
                            knn.amostraExp.colecaoInstancia.get(l).classe);
                    }
                    
                    for(j = 0; j < quantInst; j++) {
                        
                        if(mapaInstancias[j] != i) {
                            
                            // Calculando a distância
                            knn.distanciaEuclediana(knn.amostraExp.colecaoInstancia.get(l),
                                    knn.amostraExp.colecaoInstancia.get(j));
                            
                            // Montando a lista que será ordenada
                            listaDeComp.add(knn.amostraExp.colecaoInstancia.get(j));
                        }
                        
                    }//fim[Cálculo das distâncias]
                    
                    // Escolhendo o K vizinho
                    Collections.sort(listaDeComp);
                    
                    if(debug)
                        for(Instancia c:listaDeComp)
                            System.out.println(c.distancia + "\t-->\t"+ c.classe);
                    
                    knnAtual = knn.k_knn;
                    empate = true;
                    while(empate) {
                        
                        contagemK.add(new KVizinho(1, 0));
                        contagemK.add(new KVizinho(2, 0));
                        contagemK.add(new KVizinho(3, 0));
                        
                        // Contagem das menores distâncias
                        for(j = 0; j < knnAtual; j++)
                            contagemK.get(listaDeComp.get(j).classe-1).somaVizinho();
                        
                        
                        if(debug) {
                            System.out.println("Objetos da contagem");
                            for(KVizinho c:contagemK) 
                                System.out.println(c.classe + "--> "+ c.quant);
                        }
                        
                        // Ordenando o K que mais aparece
                        Collections.sort(contagemK);
                        
                        if(debug) {
                            System.out.println("Objetos da contagem, pos ordencao");
                            for(KVizinho c:contagemK) {
                                System.out.println(c.classe + "--> "+ c.quant);
                            }
                        }
                        
                        
                        
                        // Checando empate
                        if(knnAtual != 1) {
                            if(contagemK.get(0).quant != contagemK.get(1).quant)
                                empate = false;
                            else {
                                if(debug)
                                    System.out.println("Empatou novamente, valor atual do K: "+ knnAtual);
                            }
                                
                        }
                        else
                            empate = false;
                        
                        knnAtual--;
                        classeF = contagemK.get(0).classe;
                        contagemK.clear();
                        
                    }//fim[Enquanto estiver empatado]
                    
                    
                    // ENFIM, CLASSIFICANDO:
                    if(knn.amostraExp.colecaoInstancia.get(l).classe == classeF)
                        acertos++;
                    
                    listaDeComp.clear();
                    
                }// teste da amostra
                
            }//fim[Testando cada item do fold]
            
            
        }//fim[Laço dos folds]
        
        System.out.println("Quantidade de acertos:" + acertos);
        System.out.println("Porcentagem de acertos:" + (acertos*100.0)/quantInst + "%");
        
    }//fim[avaliarKNN]
    
    public void dividirFolds(boolean debug) {
        
        int i, j;               // Para laços
        
        // Preenchendo o mapeamento:
        j = 1;
        for(i = 0; i < knn.amostraExp.quantInstancias; i++) {
            
            mapaInstancias[i] = j;
            j++;
            if(j > k_folds)
                j = 1;
            
        }//fim[for]
        
        if(debug)
            exibeDivisaoFolds();
       
        
    }//fim[dividirFolds]
    
    public void exibeDivisaoFolds() {
        
        int i; // Para laços
        
        System.out.println("Divisao dos Folds");
        System.out.println("# do Fold -->  ID da Instancia --> Classe");
        for(i = 0; i < knn.amostraExp.quantInstancias; i++) {
            
            System.out.println("[" + mapaInstancias[i] +"] --> "
            + knn.amostraExp.colecaoInstancia.get(i).id + " --> "
            + knn.amostraExp.colecaoInstancia.get(i).classe);
            
        }//fim[for]
        
        System.out.println("\n\n");
        
    }//fim[exibeDivisaoFolds]

}//fim[Kfold]
