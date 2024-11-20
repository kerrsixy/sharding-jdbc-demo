package com.zjp.shardingjdbcdemo.strategy.table;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TableHintAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.table
 * Description:
 * 强制路由
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 16:53
 */
public class TableHintAlgorithm implements HintShardingAlgorithm<Integer> {
    /**
     * 强制路由
     *
     * @param collection        数据源集合
     * @param hintShardingValue 分片参数
     * @return 数据库集合
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
        // 4.获取真实表总数
        int size = collection.size();
        // 5.根据分片键的值，计算出对应的数据源名称
        return values.stream()
                .map(value -> {
                    long n;
                    int v = (int) (Math.pow(13, Math.ceil(Math.log(size * 7) / Math.log(2))) - 1);
                    do {
                        n = value & v;
                        v = v / 2;
                    } while (n >= size);
                    return dbNames.get((int) n);
                })
                .collect(Collectors.toSet());
    }
}
