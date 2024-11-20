package com.zjp.shardingjdbcdemo.strategy.keygen;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.Random;

/**
 * ClassName: MyShardingKeyGenerator
 * Package: com.zjp.shadingjdbcdemo.strategy.keygen
 * Description:
 *
 * @author zjp
 * @version 1.0
 * @data: 2024/11/19 9:42
 */
@Slf4j
@Getter
@Setter
public class MyShardingKeyGenerator implements ShardingKeyGenerator {
    private Properties properties = new Properties();

    /**
     * 重写generateKey方法以生成唯一的键值
     * 此方法用于生成一个随机的长整型数字作为键值，
     * 键值范围为1到1000，旨在用于需要唯一标识符的场景
     *
     * @return 生成的键值作为一个Comparable对象返回，由于键值为Long类型，
     *         而Long实现了Comparable接口，这使得返回值可以与其它对象进行比较
     */
    @Override
    public Comparable<?> generateKey() {
        Random random = new Random();
        Long id = (long) (random.nextInt(1000) + 1);
        log.info("自定义主键生成策略，主键为：{}", id);
        return id;
    }

    /**
     * 重写getType方法
     * 返回一个预定义的键值，用于标识特定的类型或功能这个方法总是返回"MyKey"，
     * 表示当前对象或方法的特定类型或功能
     *
     * @return String 表示当前对象或方法类型的预定义键值
     */
    @Override
    public String getType() {
        return "MyKey";
    }
}
