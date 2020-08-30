package com.leesf.partitions;

import com.leesf.data.CustomDataGenerator;
import com.leesf.data.OpType;
import org.apache.hudi.hive.MultiPartKeysValueExtractor;
import org.apache.hudi.keygen.ComplexKeyGenerator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

/**
 * multi partition fields demo.
 */
public class MultiPartitionDemo extends PartitionDemo {
    private static final String BASE_PATH = "file:/tmp/hudi-partitions/multiPartitionDemo";
    private static final String TABLE_NAME = "multiPartitionDemo";
    private static final String PARTITION_FILED = "location,sex";
    private static final String KEY_GENERATOR = ComplexKeyGenerator.class.getName();
    private static final String EXTRACTOR_CLASS = MultiPartKeysValueExtractor.class.getName();

    public MultiPartitionDemo(ConfigBuilder configBuilder) {
        super(configBuilder);
    }

    public static void main(String[] args) {
        ConfigBuilder configBuilder = new ConfigBuilder();
        configBuilder
                .basePath(BASE_PATH)
                .saveMode(SaveMode.Overwrite)
                .tableName(TABLE_NAME)
                .partitionFields(PARTITION_FILED)
                .keyGenerator(KEY_GENERATOR)
                .hivePartitionFields(PARTITION_FILED)
                .hivePartitionExtractorClass(EXTRACTOR_CLASS);

        PartitionDemo partitionDemo = new MultiPartitionDemo(configBuilder);

        Dataset<Row> dataset = CustomDataGenerator.getCustomDataset(10, OpType.INSERT, spark);

        partitionDemo.writeHudi(dataset);
    }
}
