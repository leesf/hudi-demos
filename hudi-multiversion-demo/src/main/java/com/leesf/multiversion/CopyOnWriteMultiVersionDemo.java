package com.leesf;

import java.util.Map;

public class CopyOnWriteMultiVersionDemo extends MultiVersionDemo {
    private static String basePath = "/tmp/multiversion/copyonwrite/";

    public CopyOnWriteMultiVersionDemo(Map<String, String> properties) {
        super(properties, basePath);
    }

    public static void main(String[] args) {

    }

    @Override
    public String tableType() {
        return "COPY_ON_WRITE";
    }
}
