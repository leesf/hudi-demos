package com.leesf.multiversion.commits;

import com.leesf.multiversion.CommitStrategyMultiVersion;
import com.leesf.multiversion.MultiVersionDemo;
import com.leesf.multiversion.utils.CustomDataGenerator;
import com.leesf.multiversion.utils.OpType;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import java.util.HashMap;
import java.util.Map;

public class MergeOnReadCommitStrategyDemo extends CommitStrategyMultiVersion {
    private static String basePath = "/tmp/multiversion/copyonwrite/";

    public MergeOnReadCommitStrategyDemo(Map<String, String> properties) {
        super(properties, basePath);
    }

    public static void main(String[] args) {
        Map<String, String> config = new HashMap<>();
        config.put("hoodie.keep.max.commits", "3");
        config.put("hoodie.keep.min.commits", "2");
        config.put("hoodie.cleaner.commits.retained", "1");
        MultiVersionDemo cowMultiVersionDemo = new MergeOnReadCommitStrategyDemo(config);

        Dataset<Row> dataset = CustomDataGenerator.getCustomDataset(10, OpType.INSERT, spark);

        cowMultiVersionDemo.writeHudi(dataset, SaveMode.Overwrite);

        for (int i = 0; i < 10; i++) {
            dataset = CustomDataGenerator.getCustomDataset(10, OpType.UPDATE, i, "shanghai", spark);
            cowMultiVersionDemo.writeHudi(dataset, SaveMode.Append);
        }
    }

    @Override
    public String tableType() {
        return "COPY_ON_WRITE";
    }
}
