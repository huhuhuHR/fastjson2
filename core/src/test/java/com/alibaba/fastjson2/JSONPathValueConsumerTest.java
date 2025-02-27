package com.alibaba.fastjson2;

import com.alibaba.fastjson2.reader.ValueConsumer;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONPathValueConsumerTest {
    @Test
    public void test_extract_int() {
        JSONPath path = JSONPath.of("$.id");
        AtomicInteger integer = new AtomicInteger();
        path.extract(JSONReader.of("{\"id\":123}"), new ValueConsumer() {
            @Override
            public void accept(Number val) {
                integer.set(val.intValue());
            }
        });
        assertEquals(123, integer.get());
    }

    @Test
    public void test_extract_int_utf8() {
        JSONPath path = JSONPath.of("$.id");
        AtomicInteger integer = new AtomicInteger();
        path.extract(JSONReader.of("{\"id\":123}".getBytes(StandardCharsets.UTF_8)), new ValueConsumer() {
            @Override
            public void accept(Number val) {
                integer.set(val.intValue());
            }
        });
        assertEquals(123, integer.get());
    }

    @Test
    public void test_extract_str() {
        JSONPath path = JSONPath.of("$.id");
        AtomicReference ref = new AtomicReference();
        path.extract(
                JSONReader.of("{\"id\":\"123\"}"),
                 new ValueConsumer() {
                    @Override
                    public void accept(String val) {
                        ref.set(val);
                    }
                });
        assertEquals("\"123\"", ref.get());
    }

    @Test
    public void test_extract_str_1() {
        JSONPath path = JSONPath.of("$.id");
        AtomicReference ref = new AtomicReference();
        path.extract(JSONReader.of("{\"id\":'123'}"), new ValueConsumer() {
            @Override
            public void accept(String val) {
                ref.set(val);
            }
        });
        assertEquals("\"123\"", ref.get());
    }

    @Test
    public void test_extract_true() {
        JSONPath path = JSONPath.of("$.id");
        AtomicBoolean ref = new AtomicBoolean();
        path.extract(JSONReader.of("{\"id\":true}"), new ValueConsumer() {
            @Override
            public void accept(boolean val) {
                ref.set(val);
            }
        });
        assertEquals(true, ref.get());
    }

    @Test
    public void test_extract_false() {
        JSONPath path = JSONPath.of("$.id");
        AtomicBoolean ref = new AtomicBoolean();
        path.extract(JSONReader.of("{\"id\":false}"), new ValueConsumer() {
            @Override
            public void accept(boolean val) {
                ref.set(val);
            }
        });
        assertEquals(false, ref.get());
    }

    @Test
    public void test_extract_null() {
        JSONPath path = JSONPath.of("$.id");
        AtomicReference ref = new AtomicReference();
        ref.set(new Object());
        path.extract(JSONReader.of("{\"id\":null}"), new ValueConsumer() {
            @Override
            public void acceptNull() {
                ref.set(null);
            }
        });
        assertEquals(null, ref.get());
    }

    @Test
    public void test_extract_array() {
        JSONPath path = JSONPath.of("$.id");
        AtomicReference ref = new AtomicReference();
        path.extract(JSONReader.of("{\"id\":[]}"), new ValueConsumer() {
            @Override
            public void accept(List array) {
                ref.set(array);
            }
        });
        assertEquals(new JSONArray(), ref.get());
    }

    @Test
    public void test_extract_object() {
        JSONPath path = JSONPath.of("$.id");
        AtomicReference ref = new AtomicReference();
        path.extract(JSONReader.of("{\"id\":{}}"), new ValueConsumer() {
            @Override
            public void accept(Map object) {
                ref.set(object);
            }
        });
        assertEquals(new JSONObject(), ref.get());
    }
}
