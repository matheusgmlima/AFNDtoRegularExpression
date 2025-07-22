package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Transition {
    private final String fromState;
    private final String symbol;
    private final Set<String> toStates;

    public Transition(String fromState, String symbol, Collection<String> toStates) {
        this.fromState = fromState;
        this.symbol     = symbol;
        this.toStates   = new HashSet<>(toStates);
    }

    public String getFrom() { return fromState; }
    public String getSymbol() { return symbol; }
    public Set<String> getToStates() { return toStates; }

    @Override
    public String toString() {
        return fromState + "," + symbol + "->" + String.join(",", toStates);
    }
}