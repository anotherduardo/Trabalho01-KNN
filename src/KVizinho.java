
public class KVizinho implements Comparable<KVizinho>{
    
    int classe;  // Identidade da classe
    int quant;   // Quantidade de aparições
    
    // CONSTRUTOR
    
    public KVizinho(int classe, int quant) {
        
        this.classe = classe;
        this.quant = quant;
        
    }//fim[construtor]
    
    // MÉTODOS
    
    public void somaVizinho() {
        
        this.quant++;
        
    }//fim[somaVizinho]
    
    public void zeraVizinho() {
        this.quant = 0;
        
    }//fim[zeraVizinho]
    
    @Override
    public int compareTo(KVizinho ins) {
        
        if (this.quant > ins.quant) {
            return -1;
        }
        if (this.quant < ins.quant) {
            return 1;
        }
        return 0;
        
    }//fim[compareTo]

}///fim[KVizinho]
