// ICallback.aidl
package com.liuqunshan.mylibrary;

// Declare any non-default types here with import statements

interface ICallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onValueReturn(boolean end, String val);
}
