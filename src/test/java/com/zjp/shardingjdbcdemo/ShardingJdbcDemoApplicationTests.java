package com.zjp.shardingjdbcdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.javafaker.Faker;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.zjp.shardingjdbcdemo.entity.Dict;
import com.zjp.shardingjdbcdemo.entity.User;
import com.zjp.shardingjdbcdemo.entity.UserItemVo;
import com.zjp.shardingjdbcdemo.mapper.UserItemMapper;
import com.zjp.shardingjdbcdemo.service.IDictService;
import com.zjp.shardingjdbcdemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Slf4j
@SpringBootTest
public class ShardingJdbcDemoApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private IDictService dictService;
    @Autowired
    private UserItemMapper userItemMapper;

    private static final Faker FAKER = new Faker(new Locale("zh-CN"));
    private static final Random RANDOM = new Random();

    /**
     * 测试精确分片、行表达式分片、复合分片和不分片
     */
    @Test
    public void testSaveUser() {
        Date birthday = FAKER.date().birthday(18, 70);
        User user = new User()
                .setName(FAKER.name().fullName())
                .setAge(Year.now().getValue() - birthday.getYear() - 1900)
                .setSalary((RANDOM.nextInt(500000) + 500000) / 100.0)
                .setBirthday(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userService.save(user);
    }

    /**
     * 测试范围分片
     */
    @Test
    public void testGetUserByRange() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(User::getId, 1468549684894968489L);
        List<User> list = userService.list(wrapper);
        log.info("查询结果为：{}", list);
    }

    /**
     * 测试复合分片
     */
    @Test
    public void testGetUserByComplex() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(User::getId, 1468549684894968489L);
        wrapper.between(User::getAge, 40, 60);
        List<User> list = userService.list(wrapper);
        log.info("查询结果为：{}", list);
    }

    /**
     * 测试hint分片
     */
    @Test
    public void testHint() {
        Date birthday = FAKER.date().birthday(18, 100);
        // 创建一个HintManager对象, 确保线程内只存在一个HintManager对象，否则会抛出异常"Hint has previous value, please clear first."
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        // 根据数据库hint分片规则选取数据库
        hintManager.addDatabaseShardingValue("t_user", 3);
        // 根据数据表hint分片规则选取数据表
        hintManager.addTableShardingValue("t_user", 1);
        User user = new User()
                .setName(FAKER.name().fullName())
                .setAge(Year.now().getValue() - birthday.getYear() - 1900)
                .setSalary((RANDOM.nextInt(500000) + 500000) / 100.0)
                .setBirthday(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userService.save(user);
        // HintManager存放在线程变量中, 所以需要清除
        HintManager.clear();
    }

    /**
     * 测试广播表
     */
    @Test
    public void testSaveDict() {
        Dict dict = new Dict()
                .setCode("001")
                .setValue("男")
                .setType("性别");
        dictService.save(dict);
    }

    /**
     * 测试绑定表
     */
    @Test
    public void testBinding() {
        UserItemVo result = userItemMapper.selectList();
        log.info("查询结果为：{}", result);
    }

    /**
     * Range方法测试
     */
    public static void main(String[] args) {
        // 创建一个闭区间 [1, 5]
        Range<Integer> closedRange = Range.closed(1, 5);
        System.out.println("Closed range: " + closedRange);

        // 创建一个开区间 (1, 5)
        Range<Integer> openRange = Range.open(1, 5);
        System.out.println("Open range: " + openRange);

        // 创建一个半闭区间 [1, 5)
        Range<Integer> closedOpenRange = Range.closedOpen(1, 5);
        System.out.println("Closed-open range: " + closedOpenRange);

        // 创建一个半闭区间 (1, 5]
        Range<Integer> openClosedRange = Range.openClosed(1, 5);
        System.out.println("Open-closed range: " + openClosedRange);

        // 创建一个大于给定值的区间 (3, +∞)
        Range<Integer> greaterThanRange = Range.greaterThan(3);
        System.out.println("Greater than range: " + greaterThanRange);

        // 创建一个小于给定值的区间 (-∞, 3)
        Range<Integer> lessThanRange = Range.lessThan(3);
        System.out.println("Less than range: " + lessThanRange);

        // 创建一个大于或等于给定值的区间 [3, +∞)
        Range<Integer> atLeastRange = Range.atLeast(3);
        System.out.println("At least range: " + atLeastRange);

        // 创建一个小于或等于给定值的区间 (-∞, 3]
        Range<Integer> atMostRange = Range.atMost(3);
        System.out.println("At most range: " + atMostRange);

        // 创建一个包含所有可能值的区间 (-∞, +∞)
        Range<Integer> allRange = Range.all();
        System.out.println("All range: " + allRange);

        // 检查区间是否包含给定值
        boolean contains = closedRange.contains(3);
        System.out.println("Contains 3: " + contains);

        // 检查一个区间是否完全包含另一个区间
        boolean encloses = closedRange.encloses(openRange);
        System.out.println("Encloses open range: " + encloses);

        // 检查两个区间是否有交集或相邻
        boolean isConnected = closedRange.isConnected(openRange);
        System.out.println("Is connected: " + isConnected);

        // 返回两个区间的交集
        Range<Integer> intersection = closedRange.intersection(openRange);
        System.out.println("Intersection: " + intersection);

        // 返回两个区间的并集
        Range<Integer> span = closedRange.span(openRange);
        System.out.println("Span: " + span);

        // 检查区间是否有下界和上界
        boolean hasLowerBound = closedRange.hasLowerBound();
        boolean hasUpperBound = closedRange.hasUpperBound();
        System.out.println("Has lower bound: " + hasLowerBound);
        System.out.println("Has upper bound: " + hasUpperBound);

        // 返回区间的下界和上界
        int lowerEndpoint = closedRange.lowerEndpoint();
        int upperEndpoint = closedRange.upperEndpoint();
        System.out.println("Lower endpoint: " + lowerEndpoint);
        System.out.println("Upper endpoint: " + upperEndpoint);

        // 返回区间的下界类型和上界类型
        BoundType lowerBoundType = closedRange.lowerBoundType();
        BoundType upperBoundType = closedRange.upperBoundType();
        System.out.println("Lower bound type: " + lowerBoundType);
        System.out.println("Upper bound type: " + upperBoundType);
    }

    /**
     * 测试集群保存
     */
    @Test
    public void testClusterSave() {
        Date birthday = FAKER.date().birthday(18, 100);
        User user = new User()
                .setName(FAKER.name().fullName())
                .setAge(Year.now().getValue() - birthday.getYear() - 1900)
                .setSalary((RANDOM.nextInt(500000) + 500000) / 100.0)
                .setBirthday(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userService.save(user);
    }

    /**
     * 测试集群读取
     */
    @Test
    public void testClusterGet() {
        List<User> list = userService.list();
        log.info("查询结果为：{}", list);
    }

    /**
     * 测试集群强制读主库
     */
    @Test
    public void testClusterHint() {
        try (HintManager hintManager = HintManager.getInstance();) {
            hintManager.setMasterRouteOnly();
            List<User> list = userService.list();
            log.info("查询结果为：{}", list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
