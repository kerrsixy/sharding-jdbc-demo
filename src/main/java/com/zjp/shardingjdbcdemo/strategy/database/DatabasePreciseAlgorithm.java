package com.zjp.shardingjdbcdemo.strategy.database;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DatabasePreciseAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.database
 * Description:
 * 精确分片
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 13:27
 */
public class DatabasePreciseAlgorithm implements PreciseShardingAlgorithm<Long> {
    /**
     * 精确分片
     *
     * @param collection           数据源集合
     * @param preciseShardingValue 分片参数
     * @return 数据库
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        // 自定义分片方法
        // 1.获取分片键的值
        Long value = preciseShardingValue.getValue();
        // 2.获取逻辑表名
        String logicTableName = preciseShardingValue.getLogicTableName();
        // 3.获取数据库名称，并排序
        List<String> dbNames = collection.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        // 4.根据分片键的值，计算出对应的数据源名称
        return dbNames.get((int) (value % 2));
    }
}
