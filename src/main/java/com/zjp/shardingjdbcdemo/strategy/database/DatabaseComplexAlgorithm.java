package com.zjp.shardingjdbcdemo.strategy.database;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: DatabaseComplexAlgorithm
 * Package: com.zjp.shadingjdbcdemo.strategy.database
 * Description:
 * 复合分片
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 16:48
 */
public class DatabaseComplexAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    /**
     * 复合分片
     *
     * @param collection               数据源集合
     * @param complexKeysShardingValue 分片键的值集合
     * @return 需要查找的数据源集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Integer> complexKeysShardingValue) {
        // 自定义复合分片规则
        // 1.获取分片键的值
        // 等于号 in 的值
        Map<String, Collection<Integer>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        // 实现  >，>=, <=，<  和 BETWEEN AND 等操作
        Map<String, Range<Integer>> columnNameAndRangeValuesMap = complexKeysShardingValue.getColumnNameAndRangeValuesMap();
        // 2. 获取逻辑表名
        String logicTableName = complexKeysShardingValue.getLogicTableName();
        // 获取age的值
        Collection<Integer> ageValues = columnNameAndShardingValuesMap.get("age");
        Range<Integer> ageRange = columnNameAndRangeValuesMap.get("age");
        // 获取id的值
        Collection<Integer> idValues = columnNameAndShardingValuesMap.get("id");
        Range<Integer> idRange = columnNameAndRangeValuesMap.get("id");
        // 3.获取数据库名称，并排序
        List<String> dbNames = collection.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        // 4.根据分片键的值，计算出对应的数据源名称
        if (CollectionUtils.isNotEmpty(ageValues)) {
            // 如果 ageValues 不为空，根据 age 值对 dbNames 进行取模操作
            return ageValues.stream()
                    .map(age -> dbNames.get(age % 2))
                    .collect(Collectors.toSet());
        } else if (ageRange != null) {
            // 如果 ageRange 不为空，根据 age 范围判断属于哪个分区
            int numPartitions = dbNames.size();
            int maxAge = 150;
            int partitionSize = (maxAge + 1) / numPartitions;

            // 遍历每个分区
            Set<String> resultDbs = new HashSet<>();
            for (int i = 0; i < numPartitions; i++) {
                int start = i * partitionSize;
                int end = (i + 1) * partitionSize - 1;
                if (i == numPartitions - 1) {
                    end = maxAge;
                }
                try {
                    // 检查 ageRange 是否与当前分区范围有交集,如果两个区间没有交集，该方法将抛出IllegalArgumentException。
                    ageRange.intersection(Range.closed(start, end));
                    resultDbs.add(dbNames.get(i));
                } catch (IllegalArgumentException e) {
                }
            }
            return resultDbs;
        }

        // 默认情况下返回 collection
        return collection;
    }
}
