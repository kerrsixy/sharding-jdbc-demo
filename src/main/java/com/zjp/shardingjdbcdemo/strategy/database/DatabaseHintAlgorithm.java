package com.zjp.shardingjdbcdemo.strategy.database;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DatabaseHintAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.database
 * Description:
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/18 14:50
 */
public class DatabaseHintAlgorithm implements HintShardingAlgorithm<Integer> {
    /**
     * 执行自定义Hint分片逻辑。
     *
     * @param collection        数据库名称集合
     * @param hintShardingValue 分片值对象，包含分片键和逻辑表名
     * @return 分片后的数据源名称集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Integer> hintShardingValue) {
        // 自定义Hint分片方法
        // 1.获取分片键的值
        Collection<Integer> values = hintShardingValue.getValues();
        // 2.获取逻辑表名
        String logicTableName = hintShardingValue.getLogicTableName();
        // 3.获取数据库名称，并排序
        List<String> dbNames = collection.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        // 4.根据分片键的值，计算出对应的数据源名称
        return values.stream()
                .map(value -> dbNames.get((int) (value % 2)))
                .collect(Collectors.toSet());
    }
}
