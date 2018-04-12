
public class Instancia {
    
    // ATRIBUTOS
    int id;             // Id da Instância
    float atributos[];  // Coleção de atributos
    int classe;         // Classe final
    int classeEstimada; // Classe Estimada
    
    // CONSTRUTORES
    public Instancia() {
        
    }//fim[Construtor]
    
    public Instancia(int id, int quantAtributos, String dados[]) {
        
        this.id = id;
        atributos = new float[quantAtributos];
        
        for(int i = 0; i < quantAtributos; i++) {
            atributos[i] = Float.parseFloat(dados[i]);
        }//fim[for]
        
        classe = Integer.parseInt(dados[quantAtributos]);
        classeEstimada = 0;
        
    }//fim[Construtor]
    
    
}//fim[Instancia]
