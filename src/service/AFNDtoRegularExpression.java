package service;


import model.AFND;
import model.Transition;
import model.TransitionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AFNDtoRegularExpression {
    public static String convert(AFND nfa) {
        String Qi = "Qi", Qf = "Qf";
        nfa.addState(Qi);
        nfa.addState(Qf);
        nfa.addTransition(new Transition(Qi, "ε", List.of(nfa.getStartState())));
        for (String f : nfa.getAcceptStates()) {
            nfa.addTransition(new Transition(f, "ε", List.of(Qf)));
        }
        nfa.setStartState(Qi);
        nfa.getAcceptStates().clear();
        nfa.addAcceptState(Qf);

        Set<String> toEliminate = new HashSet<>(nfa.getStates());
        toEliminate.remove(Qi);
        toEliminate.remove(Qf);

        for (String state : toEliminate) {
            eliminateState(nfa, state);
        }

        return collectExpression(nfa, Qi, Qf);
    }

    private static void eliminateState(AFND nfa, String r) {
        String loop = collectExpression(nfa, r, r);

        for (String p : new HashSet<>(nfa.getStates())) {
            if (p.equals(r)) continue;
            String in = collectExpression(nfa, p, r);
            if (in == null) continue;
            for (String q : new HashSet<>(nfa.getStates())) {
                if (q.equals(r)) continue;
                String out = collectExpression(nfa, r, q);
                if (out == null) continue;
                String expr = TransitionUtils.buildNewExpression(in, loop, out);
                overrideTransition(nfa, p, q, expr);
            }
        }

        nfa.getTransitions().removeIf(t ->
                t.getFrom().equals(r) || t.getToStates().contains(r));
        nfa.getStates().remove(r);
    }

    private static String collectExpression(AFND nfa, String from, String to) {
        StringBuilder sb = new StringBuilder();
        for (Transition t : nfa.getTransitions()) {
            if (t.getFrom().equals(from) && t.getToStates().contains(to)) {
                if (!sb.isEmpty()) sb.append("|");
                sb.append(t.getSymbol());
            }
        }
        return !sb.isEmpty() ? sb.toString() : null;
    }

    private static void overrideTransition(AFND nfa, String from, String to, String expr) {
        nfa.getTransitions().removeIf(t ->
                t.getFrom().equals(from) && t.getToStates().contains(to));
        nfa.addTransition(new Transition(from, expr, List.of(to)));
    }
}


