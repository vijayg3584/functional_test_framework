package com.autoeverything.functestfrmwrk.testdata.utility;

import java.util.HashMap;
import java.util.Map;

public class TestSuiteContext {

    private final SeleniumHelper seleniumHelper;

    private final Map<String, Object> suiteWideInfoMap;

    public TestSuiteContext() {
        suiteWideInfoMap = new HashMap<String, Object>();
        seleniumHelper = new SeleniumHelper();
    }

    public SeleniumHelper getSeleniumHelper() {
        return seleniumHelper;
    }

    public void storeInfo(String key, Object value) {
        suiteWideInfoMap.put(key, value);
    }

    public Object retrieveStoredInfo(String key) {
        return suiteWideInfoMap.get(key);
    }

    public Object removeStoredInfo(String key) {
        return suiteWideInfoMap.remove(key);
    }

    public void dispose() {
        seleniumHelper.dispose();
        clearStorage();
    }

    private void clearStorage() {
        suiteWideInfoMap.clear();
    }

}
