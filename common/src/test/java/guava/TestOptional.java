package guava;

import com.google.common.base.Optional;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by caiyl on 2016/12/16.
 */
public class TestOptional {
    @Test
    public void testNull(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("a",null);
        System.out.println(map.get("a"));
        Optional<String> possible = Optional.of(null);
//        System.out.println(possible.isPresent());
//        String d = possible.get();;
    }
}
