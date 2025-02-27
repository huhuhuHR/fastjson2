package com.alibaba.fastjson2.codec;

import com.alibaba.fastjson2.reader.ObjectReader;

import java.util.Locale;

public class FieldInfo {
    public static final long VALUE_MASK = 1L << 48;
    public static final long UNWRAPPED_MASK = 1L << 49;

    public String fieldName;
    public String format;
    public int ordinal;
    public long features;
    public boolean ignore;
    public String[] alternateNames;
    public Class<?> writeUsing;
    public Class<?> readUsing;
    public boolean fieldClassMixIn;
    public boolean isTransient;
    public String defaultValue;
    public Locale locale;
    public String schema;

    public ObjectReader getInitReader() {
        if (readUsing != null && ObjectReader.class.isAssignableFrom(readUsing)) {
            try {
                return (ObjectReader) readUsing.newInstance();
            } catch (Exception ignored) {
                // ignored
            }
            return null;
        }
        return null;
    }

    public void init() {
        fieldName = null;
        format = null;
        ordinal = 0;
        features = 0;
        ignore = false;
        alternateNames = null;
        writeUsing = null;
        readUsing = null;
        fieldClassMixIn = false;
        isTransient = false;
        defaultValue = null;
        locale = null;
        schema = null;
    }
}
