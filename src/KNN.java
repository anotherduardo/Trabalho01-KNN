

public class KNN {
    
    // ATRIBUTOS
    Amostra amostraExp;    // Amostra que sofrerá modificações
    float distancias[];    // Distâncias da Intância de teste
    
    // CONSTRUTORES
    
    public KNN(Amostra base){
        
        this.amostraExp = base;
        distancias = new float[base.quantInstancias];
        
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
    
    public void distanciaEuclediana(Instancia alvo) {
        
        int i, j;   // variavel para laços
        int d;
        
        for(i = 1; i < amostraExp.quantInstancias; i++) {
            
            d = 0;
            for(j = 0; j < amostraExp.quantAtributos; j++) {
                
                
                d += Math.pow(amostraExp.colecaoInstancia.get(i).atributos[j] - alvo.atributos[j],2);
                //System.out.println("Calc " + amostraExp.colecaoInstancia.get(i).atributos[j] + "-" + alvo.atributos[j]);
                
            }//fim[for]
            System.out.println("Valor de D:" + d);
            distancias[i] = (float) Math.sqrt(d);
            
        }//fim[for]
        
        System.out.println("Distâncias");
        for(i = 0; i < amostraExp.quantInstancias; i++) {
            System.out.println(distancias[i]);
        }
        
    }//fim[distanciaEuclediana]

}//fim[KNN]
