package com.lyncode.choiceprops;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PluralPropertyTest {
    @Test
    public void oneChoice() throws Exception {
        String result = PluralProperty.translate(1, "(0) No items | (1) One item | ]1, INF[ %count% items", new HashMap<String, Object>() {{
            put("%count%", 1);
        }});

        assertEquals("One item", result);
    }

    @Test
    public void moreThenOne() throws Exception {
        String result = PluralProperty.translate(10, "(0) No items | (1) One item | ]1, INF[ %count% items", new HashMap<String, Object>() {{
            put("%count%", 10);
        }});

        assertEquals("10 items", result);
    }
}