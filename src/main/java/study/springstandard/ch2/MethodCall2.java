package study.springstandard.ch2;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MethodCall2 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("study.springstandard.ch2.YoilTellerMVC");
        Object obj = aClass.newInstance();
        Method main = aClass.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        Model model = new BindingAwareModelMap();
        System.out.println("[before] model=" + model);

        String viewName = (String) main.invoke(obj, new Object[]{2021, 1, model});
        System.out.println("viewName=" + viewName);

        System.out.println("[after] model=" + model);
    }

    static void render(Model model, String viewName) throws Exception {
        String result = "";

        // 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
        Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");

        while (sc.hasNextLine())
            result += sc.nextLine() + System.lineSeparator();

        // 2. model을 map으로 변환
        Map map = model.asMap();

        // 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
        Iterator it = map.keySet().iterator();

        while (it.hasNext()) {
            String key = (String) it.next();

            // 4. replace()로 key를 value 치환한다.
            result = result.replace("${" + key + "}", "" + map.get(key));
        }

        // 5.렌더링 결과를 출력한다.
        System.out.println(result);
    }
}
