/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.testdata.utility;

import java.util.HashMap;
import java.util.Map;

import com.automatewebtesting.framework.exception.InputParseException;

/**
 *
 * @author h.das
 */
public class InputParserUtil {

    private static final String END_OF_LINE_CHARACTER;
    private static final String KEY_VALUE_SEPERATOR;

    static {
        END_OF_LINE_CHARACTER = "~";
        KEY_VALUE_SEPERATOR = "\\||#";
    }

    public static Map<String, String> parseStringAsMap(String input, String lineMarker, String keyValueSeperator) throws InputParseException {
        Map<String, String> outMap = new HashMap<String, String>();
        try {
            String[] lines = input.split(lineMarker);
            for (String line : lines) {
                String[] pair = line.split(keyValueSeperator);
                outMap.put(pair[0], pair[1]);
            }
        } catch (Exception e) {
            throw new InputParseException("Exception while parsing the input " + input, e);
        }
        return outMap;
    }

    public static Map<String, String> parseStringAsMap(String input) throws InputParseException {
        return parseStringAsMap(input, END_OF_LINE_CHARACTER, KEY_VALUE_SEPERATOR);
    }

    public static Map<String, String> parseStringAsMap(String input, String keyValueSeperator) throws InputParseException {
        return parseStringAsMap(input, END_OF_LINE_CHARACTER, keyValueSeperator);
    }
}
