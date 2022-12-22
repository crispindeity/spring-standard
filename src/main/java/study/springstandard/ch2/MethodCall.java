package study.springstandard.ch2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MethodCall {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, String> map = new HashMap<>();
        System.out.println("before: " + map);

        ModelController modelController = new ModelController();

        String viewName = modelController.main(map);

        System.out.println("after: " + map);

        render(map, viewName);
    }

    static void render(HashMap map, String viewName) throws FileNotFoundException {
        String result = "";

        Scanner sc = new Scanner(new File(viewName + ".txt"));

        while (sc.hasNextLine()) {
            result += sc.nextLine() + System.lineSeparator();

            Iterator iterator = map.keySet().iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();

                result = result.replace("${" + key + "}", String.valueOf(map.get(key)));
            }
        }

        System.out.println(result);
    }

}

class ModelController {
    public String main(HashMap map) {
        map.put("id", "asdf");
        map.put("pwd", 1234);

        return "txtView2";
    }
}
