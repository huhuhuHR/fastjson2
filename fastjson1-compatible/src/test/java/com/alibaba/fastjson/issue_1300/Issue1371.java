package com.alibaba.fastjson.issue_1300;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wenshao on 05/08/2017.
 */
public class Issue1371 {
    private enum Rooms{
        A, B, C, D ,E ;
    }

    @Test
    public void testFastjsonEnum(){
        Map<Rooms, Rooms> enumMap = new TreeMap<Rooms, Rooms>();

        enumMap.put(Rooms.C, Rooms.D);
        enumMap.put(Rooms.E, Rooms.A);

        Assertions.assertEquals(JSON.toJSONString(enumMap, SerializerFeature.WriteNonStringKeyAsString), "{\"C\":\"D\",\"E\":\"A\"}");

    }




//    public void testParsed(){
//
//        String oldStyleJson = "{1:'abc', 2:'cde'}";
//
//        Gson gson = new Gson();
//
//        Map fromJson = gson.fromJson(oldStyleJson, Map.class);
//
//        Assert.assertNull(fromJson.get(1));
//
//        Assert.assertEquals(fromJson.get("1"), "abc" );
//
//        Map parsed = JSON.parseObject(oldStyleJson, Map.class, Feature.IgnoreAutoType, Feature.DisableFieldSmartMatch);
//
//
//        Assert.assertNull(parsed.get(1));
//
//        Assert.assertEquals(parsed.get("1"), "abc" );
//
//    }
//
//    public void testParsed_jackson() throws Exception {
//
//        String oldStyleJson = "{1:\"abc\", 2:\"cde\"}";
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map fromJson = objectMapper.readValue(oldStyleJson, Map.class);
//        Assert.assertNull(fromJson.get(1));
//    }
}
