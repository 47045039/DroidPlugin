// IService.aidl
package com.liuqunshan.base;

// Declare any non-default types here with import statements

import com.liuqunshan.mylibrary.ICallback;

interface IService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int getValueSync();

    void getValueAsync(in ICallback cbk);
}
