package com.zjp.shardingjdbcdemo.strategy.masterslave;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: MyMasterSlaveLoadBalanceAlgorithm
 * Package: com.zjp.shardingjdbcdemo.strategy.masterslave
 * Description:
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/22 8:40
 */
@Slf4j
@Getter
@Setter
public class MyMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {
    private Properties properties = new Properties();
    private static final AtomicInteger CURRENT_INDEX = new AtomicInteger(0);

    @Override
    public String getDataSource(String s, String s1, List<String> list) {
        log.info("自定义负载均衡算法");
        log.info("s:{},s1:{},list:{},properties:{}", s, s1, list, properties); // s: master,s1: master, list:[slave1, slave2],properties: prop里的参数

        if (list == null || list.isEmpty()) {
            log.warn("数据源列表为空");
            return null;
        }

        // 获取当前索引的数据源
        int index = CURRENT_INDEX.getAndIncrement() % list.size();
        if (index < 0) {
            index += list.size();
        }

        return list.get(index);
    }

    @Override
    public String getType() {
        return "MY_LOAD_BALANCE";
    }
}
