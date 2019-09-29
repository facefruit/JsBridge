package org.devlang.browser.jsbridge;

import org.devlang.browser.intf.NativeCallback;

import java.util.LinkedHashMap;

public class NativeCallbackMap {

    private static LinkedHashMap<Integer, NativeCallback> mCallbackMap = new LinkedHashMap<>();
    private static int id = 1;

    private NativeCallbackMap() {

    }

    public synchronized static int put(NativeCallback callback) {
        if (callback != null) {
            mCallbackMap.put(id, callback);
            return id++;
        }
        return 0;
    }

    public static NativeCallback pop(int id) {
        return mCallbackMap.remove(id);
    }
}
