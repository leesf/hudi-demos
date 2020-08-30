package com.leesf.multiversion.commits;

import com.leesf.multiversion.MultiVersionDemo;

import java.util.Map;

public abstract class CommitStrategyMultiVersion extends MultiVersionDemo {
    public CommitStrategyMultiVersion(Map<String, String> properties, String basePath) {
        super(properties, basePath);
    }
}
