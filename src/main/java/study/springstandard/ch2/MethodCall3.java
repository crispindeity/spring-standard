package study.springstandard.ch2;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MethodCall3 {

    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
        map.put("year", "2022");
        map.put("month", "12");
        map.put("day", "1");

        Model model = null;
        Class<?> aClass = Class.forName("study.springstandard.ch2.YoilTellerMVC");
        Object obj = aClass.getDeclaredConstructor().newInstance();
        aClass.newInstance();
        Method main = aClass.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        Parameter[] parameters = main.getParameters();
        Object[] argArr = new Object[main.getParameterCount()];

        for (int i = 0; i < parameters.length; i++) {
            String paramName = parameters[i].getName();
            Class<?> paramType = parameters[i].getType();
            Object value = map.get(paramName);

            if (paramType == Model.class) {
                argArr[i] = model = new BindingAwareConcurrentModel();
            } else if (value != null) {
                argArr[i] = convertTo(value, paramType);
            }
        }
        System.out.println("paramArr=" + Arrays.toString(parameters));
        System.out.println("argArr=" + Arrays.toString(argArr));


        // Controller의 main()을 호출 - YoilTellerMVC.main(int year, int month, int day, Model model)
        String viewName = (String) main.invoke(obj, argArr);
        System.out.println("viewName=" + viewName);

        // Model의 내용을 출력
        System.out.println("[after] model=" + model);

        // 텍스트 파일을 이용한 rendering
        render(model, viewName);
    }

    private static Object convertTo(Object value, Class type) {
        if (type == null || value == null || type.isInstance(value)) // 타입이 같으면 그대로 반환
            return value;

        // 타입이 다르면, 변환해서 반환
        if (String.class.isInstance(value) && type == int.class) { // String -> int
            return Integer.valueOf((String) value);
        } else if (String.class.isInstance(value) && type == double.class) { // String -> double
            return Double.valueOf((String) value);
        }

        return value;
    }

    private static void render(Model model, String viewName) throws Exception {
        StringBuilder result = new StringBuilder();

        // 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
        Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");

        while (sc.hasNextLine()) {
            result.append(sc.nextLine()).append(System.lineSeparator());
        }

        // 2. model을 map으로 변환
        Map map = model.asMap();

        // 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.

        for (Object o : map.keySet()) {
            String key = (String) o;
            // 4. replace()로 key를 value 치환한다.
            result = new StringBuilder(result.toString().replace("${" + key + "}", "" + map.get(key)));
        }

        // 5.렌더링 결과를 출력한다.
        System.out.println(result);
    }
}
