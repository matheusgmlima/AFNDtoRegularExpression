package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Automato {
    private final Set<String> states = new HashSet<>();
    private String startState;
    private final Set<String> acceptStates = new HashSet<>();
    private final Map<String,Map<String,String>> transitions = new HashMap<>();

    public void addState(String state){
        states.add(state);
    }

    public void setStartState(String startState) {
        this.startState = startState;
        states.add(startState);
    }

    public void addAcceptState(String state) {
        acceptStates.add(state);
        states.add(state);
    }

    public void addTransition(String fromState, String toState, String label){
        states.add(fromState);
        states.add(toState);
        transitions
                .computeIfAbsent(fromState, k -> new HashMap<>())
                .merge(toState, label, (a,b) -> "(" + a + ")|(" + b + ")");
    }
    public Set<String> getStates() {
        return states;
    }

    public String getStartState() {
        return startState;
    }

    public void setStates(Set<String> newStates) {
        states.clear();
        states.addAll(newStates);
    }

    public void setAcceptStates(Set<String> newAcceptStates) {
        acceptStates.clear();
        acceptStates.addAll(newAcceptStates);
    }

    public Set<String> getAcceptStates() {
        return acceptStates;
    }

    public Map<String, Map<String, String>> getTransitions() {
        return transitions;
    }
}
