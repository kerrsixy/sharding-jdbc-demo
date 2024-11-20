package com.zjp.shardingjdbcdemo.strategy.database;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: DatabaseRangeAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.database
 * Description:
 * 范围分片
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 15:22
 */
public class DatabaseRangeAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
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

    /**
     * 范围分片
     *
     * @param collection         数据源集合
     * @param rangeShardingValue 分片参数
     * @return 直接返回源
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        // 1.获取分片键名
        String shardingColumn = rangeShardingValue.getColumnName();
        // 2.获取逻辑表名
        String logicTableName = rangeShardingValue.getLogicTableName();
        // 3.获取分片键值范围
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        // 4.根据分片键值范围，进行范围匹配
        long lower = 0L;
        long upper = Long.MAX_VALUE;
        // 存在下限
        if (valueRange.hasLowerBound()) {
            lower = valueRange.lowerEndpoint();
        }
        // 存在上限
        if (valueRange.hasUpperBound()) {
            upper = valueRange.upperEndpoint();
        }
        // 5.过滤并返回
        return collection;
    }
}