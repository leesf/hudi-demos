package com.leesf.multiversion;

import java.util.Map;

public abstract class CommitStrategyMultiVersion extends MultiVersionDemo {
    public CommitStrategyMultiVersion(Map<String, String> properties, String basePath) {
        super(properties, basePath);
    }
}
