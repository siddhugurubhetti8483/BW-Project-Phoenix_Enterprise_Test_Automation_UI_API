package com.phoenix.core;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
     private static final ThreadLocal<Map<String, String>> context = ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, String value) {
        context.get().put(key, value);
    }

    public static String get(String key) {
        return context.get().get(key);
    }

    public static void clear() {
        context.remove();
    }
}
