import java.lang.reflect.Field;

/**
 * Created by OurEDA on 2016/1/4.
 */
public class StaticTest {
    public static String config1 = "default";
    public static String config2 = "dafault";

    public static void main(String arg[]) {
        try {
            Class clazz = Class.forName("StaticTest");
            Field field;
            field = clazz.getField("config1");
            field.set(null, "setted");
            System.err.println(field.get(null));
            field = clazz.getField("config2");
            field.set(null, "setted");
            System.err.println(field.get(null));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
