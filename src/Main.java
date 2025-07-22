
import IO.AutomatonIO;
import model.AFND;
import service.AFNDtoRegularExpression;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        AFND nfa = AutomatonIO.loadFromFile("C:\\Programming\\entrada_afnd.txt" );

        String regex = AFNDtoRegularExpression.convert(nfa);

        System.out.println("Expressão regular equivalente:");
        System.out.println(regex);

        Files.writeString(Path.of("C:\\Programming\\saida_regex.txt"), regex);
    }
}