package com.zjp.shardingjdbcdemo.strategy.table;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TableRangeAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.table
 * Description:
 * 范围分片
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 15:23
 */
public class TableRangeAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {
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
        // 1.获取分片键值
        Long value = preciseShardingValue.getValue();
        // 2.获取逻辑表名
        String logicTableName = preciseShardingValue.getLogicTableName();
        // 3.获取真实表总数
        int size = collection.size();
        // 4.计算表名
        long n;
        int v = (int) (Math.pow(13, Math.ceil(Math.log(size * 7) / Math.log(2))) - 1);
        do {
            n = value & v;
            v = v / 2;
        } while (n >= size);
        // 5.获取真实表名称，并排序
        List<String> dbNames = collection.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        // 6.返回真实表
        return dbNames.get((int) n);
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
        //逻辑表名称
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
        return collection;
    }
}