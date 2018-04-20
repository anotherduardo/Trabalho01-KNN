

public class KNN {
    
    // ATRIBUTOS
    int k_knn;                 // K vizinhos do KNN
    Amostra amostraExp;    // Amostra que sofrerá modificações
    
    // CONSTRUTORES
    
    public KNN(Amostra base, int k){
        
        this.k_knn = k;
        this.amostraExp = base;
        
    }//fim[Construtor]
    
    
    // METODOS
    
    public void zScore() {
        
        int i, j;   // variaveis para laços
        
        float medias[] = new float[amostraExp.quantAtributos];
        float variancias[] = new float[amostraExp.quantAtributos];
        float desvios[] = new float[amostraExp.quantAtributos];
        
        // Iniciando o vetor das medias
        for(i = 0; i < amostraExp.quantAtributos; i++) {
            medias[i] = 0;
            variancias[i] = 0;
            desvios[i] = 0;
        }
        
        
        // Calculando a media para todos os atributos
        for(i= 0; i < amostraExp.quantAtributos; i++) {
            
            for(j = 0; j < amostraExp.quantInstancias; j++) {
                medias[i] += amostraExp.colecaoInstancia.get(j).atributos[i];
            }
            
            medias[i] /= amostraExp.quantInstancias;
            
        }//fim[for]
        
        // Calculando a variancia para todos os atributos
        for(i= 0; i < amostraExp.quantAtributos; i++) {
            
            for(j = 0; j < amostraExp.quantInstancias; j++) {
                variancias[i] += Math.pow(amostraExp.colecaoInstancia.get(j).atributos[i] - medias[i], 2);
            }
            
            variancias[i] /= amostraExp.quantInstancias;
            
        }//fim[for]
        
        // Calculando desvios
        for(i= 0; i < amostraExp.quantAtributos; i++) {
            
            desvios[i] = (float) Math.sqrt(variancias[i]);
            
        }//fim[for]
        
        // Calculando zscore
        for(i= 0; i < amostraExp.quantAtributos; i++) {
            
            for(j = 0; j < amostraExp.quantInstancias; j++) {
                
                if(desvios[i] != 0) {
                    amostraExp.colecaoInstancia.get(j).atributos[i] =
                            (amostraExp.colecaoInstancia.get(j).atributos[i]- medias[i])/desvios[i];
                }
                else
                    amostraExp.colecaoInstancia.get(j).atributos[i] = 0;
                        
            }
            
        }//fim[for]
        
    }//fim[zScore]
    
    public void embaralharAmostra(long seed) {
        
        this.amostraExp.embaralharAmostras(seed);
        
    }//fim[embaralharAmostra]
    
    public void distanciaEuclediana(Instancia origem, Instancia alvo) {
        
        float d; // valor da distância (colocada no alvo)
        int j; // para laços
        
        d = 0;
        for(j = 0; j < amostraExp.quantAtributos; j++) {
            
            // Para debug do d
            //System.out.println("Valor atua de d:"+d);
            //System.out.println("Origem Atri:" + origem.atributos[j]+" - " + alvo.atributos[j] + ": "
            //        + Math.pow(origem.atributos[j] - alvo.atributos[j], 2));
            
            
            d += Math.pow(origem.atributos[j] - alvo.atributos[j], 2);
            
        }//fim[for]
        
        alvo.distancia = (float) Math.sqrt(d);
        
    }//fim[distanciaEuclediana]
    
    
    public void validarZscore() {
        
        float quantAtrib[];// Soma de cada coluna
        int j;              // Para laços
        
        quantAtrib = new float[amostraExp.quantAtributos];
        
        System.out.println("Quantidade de atributos:" + amostraExp.quantAtributos);
        for(j = 0; j < amostraExp.quantAtributos; j++)
            quantAtrib[0] = 0;
        
        System.out.println("A soma de todos os atributos(coluna) deve ser zero:");
        
        for(Instancia i : amostraExp.colecaoInstancia) {
            
            for(j = 0; j < amostraExp.quantAtributos; j++) {
                System.out.println("Atr:"+j+" Somando:"+quantAtrib[j] +" com " + i.atributos[j]);
                quantAtrib[j] += i.atributos[j];
            }
            System.out.println("Atributo:"+ quantAtrib[j] + "\n\n");
        }
        
        for(j = 0; j < amostraExp.quantAtributos; j++)
            System.out.println("Atributo " + j + ":"+quantAtrib[j]);
        
    }//fim[validarZscore]

}//fim[KNN]
