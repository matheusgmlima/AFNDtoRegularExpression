package IO;

import model.AFD;
import model.AFND;
import model.Transition;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomatonIO {

    public static AFND loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String typeLine = reader.readLine();
            String type     = typeLine.split(":")[1].trim();

            Set<String> states  = parseSet(reader.readLine());
            Set<String> alpha   = parseSet(reader.readLine());
            String start        = reader.readLine().split(":")[1].trim();
            Set<String> accept  = parseSet(reader.readLine());
            reader.readLine();
            AFND automaton = type.equals("AFD") ? new AFD() : new AFND();
            states.forEach(automaton::addState);
            automaton.setAlphabet(alpha);
            automaton.setStartState(start);
            accept.forEach(automaton::addAcceptState);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                String[] left  = parts[0].split(",");
                String from    = left[0].trim();
                String symbol  = left[1].trim();
                List<String> to= List.of(parts[1].split(","));
                automaton.addTransition(new Transition(from, symbol, to));
            }
            return automaton;
        }
    }

    public static void saveToFile(AFND automaton, String filename, boolean isAFD) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("TYPE: " + (isAFD ? "AFD" : "AFND"));
            writer.println("STATES: " + String.join(",", automaton.getStates()));
            writer.println("ALPHABET: " + String.join(",", automaton.getAlphabet()));
            writer.println("START: "  + automaton.getStartState());
            writer.println("ACCEPT: "+ String.join(",", automaton.getAcceptStates()));
            writer.println("TRANSITIONS:");
            for (Transition t : automaton.getTransitions()) {
                writer.println(t);
            }
        }
    }

    private static Set<String> parseSet(String line) {
        return new HashSet<>(Arrays.asList(line.split(":")[1].trim().split(",")));
    }
}
