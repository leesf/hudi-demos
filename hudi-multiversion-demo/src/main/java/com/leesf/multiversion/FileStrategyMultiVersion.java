package com.leesf.multiversion;

import java.util.Map;

public abstract class FileStrategyMultiVersion extends MultiVersionDemo {

    public FileStrategyMultiVersion(Map<String, String> properties, String basePath) {
        super(properties, basePath);
    }
}
