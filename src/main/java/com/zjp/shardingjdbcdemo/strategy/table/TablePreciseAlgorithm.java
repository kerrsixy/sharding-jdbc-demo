package com.zjp.shardingjdbcdemo.strategy.table;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TablePreciseAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.table
 * Description:
 * 精确分片
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 13:43
 */
public class TablePreciseAlgorithm implements PreciseShardingAlgorithm<Long> {
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
}
