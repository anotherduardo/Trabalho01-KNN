

public class KNN {
    
    // ATRIBUTOS
    Amostra amostraExp;    // Amostra que sofrerá modificações
    
    // CONSTRUTORES
    
    public KNN(Amostra base){
        
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

}//fim[KNN]
