package prelim;

public final class ConsoleUI {
    private ConsoleUI() {}

    public static String title(String text) {
        String bar = repeat("=", text.length() + 8);
        return "\n" + bar + "\n==  " + text + "  ==\n" + bar;
    }

    public static String section(String text) {
        String bar = repeat("-", text.length() + 6);
        return bar + "\n-- " + text + " --\n" + bar;
    }

    public static String success(String text) {
        return "[OK] " + text;
    }

    public static String info(String text) {
        return "[i] " + text;
    }

    public static String warn(String text) {
        return "[!] " + text;
    }

    public static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }
}

