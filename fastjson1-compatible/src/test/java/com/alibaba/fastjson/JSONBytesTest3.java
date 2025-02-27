package com.alibaba.fastjson;


import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONBytesTest3 {
    @Test
    public void test_codec() throws Exception {
        Model model = new Model();
        model.value = "𠜎𠜱𠝹𠱓𠱸𠲖𠳏𠳕𠴕𠵼𠵿𠸎𠸏𠹷𠺝𠺢𠻗𠻹𠻺𠼭𠼮𠽌𠾴𠾼𠿪𡁜𡁯𡁵𡁶𡁻𡃁𡃉𡇙𢃇𢞵𢫕𢭃𢯊𢱑𢱕𢳂𢴈𢵌𢵧𢺳𣲷𤓓𤶸𤷪𥄫𦉘𦟌𦧲𦧺𧨾𨅝𨈇𨋢𨳊𨳍𨳒𩶘";

        byte[] bytes = JSON.toJSONBytes(model);
        Model model2 = JSON.parseObject(bytes, 0, bytes.length, Charset.forName("UTF8").newDecoder(), Model.class);

        assertEquals(model.value.length(), model2.value.length());
        for (int i = 0; i < model.value.length(); ++i) {
            char c1 = model.value.charAt(i);
            char c2 = model2.value.charAt(i);

            assertEquals(c1, c2);
        }
    }

    public static class Model {
        public String value;
    }
}
