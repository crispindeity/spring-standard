package study.springstandard.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Class<?> yoilTellerClass = Class.forName("study.springstandard.ch2.YoilTeller");
        Object obj = yoilTellerClass.newInstance();

        Method[] methods = yoilTellerClass.getDeclaredMethods();

        for (Method method : methods) {
            String name = method.getName();
            Parameter[] parameters = method.getParameters();
            Class<?> returnType = method.getReturnType();

            StringJoiner stringJoiner = new StringJoiner(", ", "(", ")");

            for (Parameter param : parameters) {
                String paramName = param.getName();
                Class<?> paramType = param.getType();

                stringJoiner.add(paramType.getName() + " " + paramName);
            }

            System.out.printf("%s %s%s%n", returnType.getName(), name, stringJoiner);
        }
    }
}
