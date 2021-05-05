package Onp;

public class Operator {
    private String str;

    public Operator(String str) {
        this.str = str;
    }

    public int getPriority() {
        if (str == "*" || str == "/")
            return 2;
        if (str == "+" || str == "-")
            return 1;
        return 0;
    }
    @Override
    public String toString() {
        return str;
    }
}
