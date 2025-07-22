package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AFND {
    protected Set<String> states      = new HashSet<>();
    protected Set<String> alphabet    = new HashSet<>();
    protected String       startState;
    protected Set<String> acceptStates= new HashSet<>();
    protected List<Transition> transitions = new ArrayList<>();

    public void addState(String s)            { states.add(s); }
    public void setAlphabet(Set<String> a)    { alphabet = a; }
    public void setStartState(String s)       { startState = s; states.add(s); }
    public void addAcceptState(String s)      { acceptStates.add(s); states.add(s); }
    public void addTransition(Transition t)   { transitions.add(t); }

    public Set<String> getStates()            { return states; }
    public Set<String> getAlphabet()          { return alphabet; }
    public String getStartState()             { return startState; }
    public Set<String> getAcceptStates()      { return acceptStates; }
    public List<Transition> getTransitions()  { return transitions; }
}
