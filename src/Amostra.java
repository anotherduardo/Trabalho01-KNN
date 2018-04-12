import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Amostra {
    
    // ATRIBUTOS
    
    ArrayList<Instancia> colecaoInstancia = new ArrayList<Instancia>();

    // CONSTRUTORES
    
    public Amostra() {
        
        
    }//fim[Construtor]
    
    public Amostra(String path) {
        
        try {
            importarIntancias(path);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }//fim[Construtor]
    
    public void embaralharAmostras(long seed) {
        
        // Com seed = 0, usamos um valor mais aleatório
        if(seed == 0)
            seed = System.nanoTime();

        Collections.shuffle(colecaoInstancia, new Random(seed));
        
    }//fim[embaralharAmostras]
    
    // MÉTODOS
    
    private void importarIntancias(String path) throws IOException {
        
        int quantInstancias;  // Quantidade de Instâncias da Amostra
        int quantAtributos;   // Quantidade de Atributos da Amostra
        
        FileReader arq;       // Ponteiro para arquivo lido
        String aux[];         // Coleção de Strings auxiliares
        String linha;         // Armazena a linha atual
        
        try {
            
            arq = new FileReader(path);
            BufferedReader buffer = new BufferedReader(arq);
            linha = buffer.readLine();
            aux = linha.split(" ");
            
            // Importanto as caracteristicas da base:
            
            quantInstancias = Integer.parseInt(aux[0]);
            quantAtributos = Integer.parseInt(aux[1]);
            
            for(int i = 0; i < quantInstancias; i++) {
                
                linha = buffer.readLine();
                aux = linha.split(" ");
                colecaoInstancia.add(new Instancia(i, quantAtributos, aux));
                
            }//fim[for]
            
            arq.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }//fim[importarInstancias]
    
    public void exibeAmostra() {
        
        System.out.println(" id at1 at2 at3 at4 cls est");
        for(Instancia i : colecaoInstancia) {
            
            System.out.printf("%4d ", i.id);
            System.out.println(i.atributos[0] + " " +
                               i.atributos[1] + " " +
                               i.atributos[2] + " " +
                               i.atributos[3] + " " +
                               i.classe + " " +
                               i.classeEstimada);
        }
        System.out.println();
        
    }//fim[exibe Amostra]

}//fim[Amostra]