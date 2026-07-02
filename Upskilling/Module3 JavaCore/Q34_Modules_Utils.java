// module-info.java
module com.utils {
    exports com.utils;
}

// Utils.java
package com.utils;
public class Utils {
    public static String getGreeting() { return "Hello from module!"; }
}