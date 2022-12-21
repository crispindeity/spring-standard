package study.springstandard.ch2;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Class<?> yoilTellerClass = Class.forName("study.springstandard.ch2.YoilTeller");
        Object obj = yoilTellerClass.newInstance();
        MethodInfo methodInfo = new MethodInfo();

    }
}
