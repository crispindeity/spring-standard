package study.springstandard.ch2;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
//        Hello hello = new Hello();
//        hello.main(); // private 이라 호출 불가

        // Reflection API 를 사용
        Class helloClass = Class.forName("study.springstandard.ch2.Hello");
        Object hello = helloClass.getDeclaredConstructor().newInstance();
        Method main = helloClass.getDeclaredMethod("main");
        main.setAccessible(true);
        main.invoke(hello);
    }
}
