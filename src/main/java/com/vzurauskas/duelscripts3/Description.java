package com.vzurauskas.duelscripts3;

import java.util.HashMap;
import java.util.Map;

public final class Description {
    private final Map<String, String> facts = new HashMap<>();

    public void remember(String key, int value) {
        facts.put(key, String.valueOf(value));
    }

    public void remember(String key, String value) {
        facts.put(key, value);
    }

    public boolean knows(String key) {
        return facts.containsKey(key);
    }

    public int intValue(String key) {
        return Integer.valueOf(facts.get(key));
    }

    public String stringValue(String key) {
        return facts.get(key);
    }
}


