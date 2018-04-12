import java.io.IOException;

public class Teste {

    public static void main(String[] args) {
        
        Amostra am = new Amostra("src/base/dados.txt");
        am.exibeAmostra();
        am.embaralharAmostras(1);
        am.exibeAmostra();

    }

}
