package model;

public class TransitionUtils {
    public static String buildNewExpression(String in, String loop, String out) {
        if (loop == null) loop = "";
        String loopPart = loop.isEmpty() ? "" : "(" + loop + ")*";
        return "(" + in + ")" + loopPart + "(" + out + ")";
    }
}

