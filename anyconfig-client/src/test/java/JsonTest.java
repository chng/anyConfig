
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by OurEDA on 2016/1/4.
 */
public class JsonTest {

    public int intValue = 1;
    public int [] intArray = {1,2,3,4};
    public String stringValue = "string";
    public String [] stringArray = {"string1", "string2"};


    public static void main(String arg[]) {
        JsonTest ins = new JsonTest();
        String str = JSON.toJSONString(ins);
        System.err.println(str);

        final  JSONObject obj = JSON.parseObject(str);
        final JsonTest reflectedIns = JSON.parseObject(str, JsonTest.class);
        System.err.println(JSON.toJSONString(reflectedIns));
    }
}
