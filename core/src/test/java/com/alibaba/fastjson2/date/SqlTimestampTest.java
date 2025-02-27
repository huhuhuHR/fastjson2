package com.alibaba.fastjson2.date;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlTimestampTest {
    @Test
    public void test() {
        String str = "{\"birthday\":\"2022-05-03 15:26:05\"}";
        Student student = JSON.parseObject(str, Student.class);
        assertEquals(str, JSON.toJSONString(student));

        Student student1 = JSON.parseObject(str).toJavaObject(Student.class);
        assertEquals(student.birthday, student1.birthday);

        Student student2 = JSONB.parseObject(
                JSONB.toBytes(JSON.parseObject(str)), Student.class);
        assertEquals(student.birthday, student2.birthday);
    }

    public static class Student {
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        public java.sql.Timestamp birthday;
    }

    @Test
    public void test1() {
        String str = "{\"birthday\":\"2022-05-03T15:26:05\"}";
        Student1 student = JSON.parseObject(str, Student1.class);
        String str2 = JSON.toJSONString(student);
        Student1 student1 = JSON.parseObject(str2, Student1.class);
        assertEquals(student.birthday.getYear(), student1.birthday.getYear());
        assertEquals(student.birthday.toInstant().toEpochMilli(), student1.birthday.toInstant().toEpochMilli());
    }

    @Test
    public void test1_null() {
        String str = "{\"birthday\":null}";
        Student1 student = JSON.parseObject(str, Student1.class);
        assertEquals(str, JSON.toJSONString(student, JSONWriter.Feature.WriteNulls));
    }

    public static class Student1 {
        @JSONField(format = "iso8601")
        public java.sql.Timestamp birthday;
    }

    @Test
    public void test2() {
        String str = "{\"birthday\":\"2022-05-03 15:26:05\"}";
        Student2 student = JSON.parseObject(str, Student2.class);
        assertEquals(str, JSON.toJSONString(student, "yyyy-MM-dd HH:mm:ss"));
    }

    public static class Student2 {
        public java.sql.Timestamp birthday;
    }


    @Test
    public void test3() {
        long millis = System.currentTimeMillis();
        String str = "{\"birthday\":" + millis + "}";
        Student3 student = JSON.parseObject(str, Student3.class);
        String str2 = JSON.toJSONString(student);
        Student3 student1 = JSON.parseObject(str2, Student3.class);
        assertEquals(student.birthday.toInstant().getEpochSecond(), student1.birthday.toInstant().getEpochSecond());

        Student3 student2 = JSONB.parseObject(
                JSONB.toBytes(JSON.parseObject(str)), Student3.class);
        assertEquals(student.birthday, student2.birthday);
    }

    public static class Student3 {
        @JSONField(format = "millis")
        public java.sql.Timestamp birthday;
    }

    @Test
    public void test4() {
        long seconds = System.currentTimeMillis() / 1000;
        String str = "{\"birthday\":" + seconds + "}";
        Student4 student = JSON.parseObject(str, Student4.class);
        String str2 = JSON.toJSONString(student);
        Student4 student1 = JSON.parseObject(str2, Student4.class);
        assertEquals(student.birthday.toInstant().getEpochSecond(),
                student1.birthday.toInstant().getEpochSecond()
        );

        Student4 student2 = JSONB.parseObject(
                JSONB.toBytes(JSON.parseObject(str)), Student4.class);
        assertEquals(student.birthday, student2.birthday);
    }

    public static class Student4 {
        @JSONField(format = "unixtime")
        public java.sql.Timestamp birthday;
    }
}
