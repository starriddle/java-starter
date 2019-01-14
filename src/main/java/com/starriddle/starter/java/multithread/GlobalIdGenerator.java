package com.starriddle.starter.java.multithread;

/**
 * snowflake：twitter 开源的分布式 id 生成算法<p/>
 * 一个 64 位的 long 型的 id：<br>
 *     1 个 bit 是不用的，因为生成的 id 都是正数，所以第一个 bit 统一都是 0；<br>
 *     用 41 bit 作为毫秒数，可以标识 2^41 - 1 个毫秒值，换算成年就是表示69年的时间<br>
 *     用 10 bit 作为工作机器 id，5 个 bit 代表机房 id，5 个 bit 代表机器 id<br>
 *     12 bit 作为序列号，用来记录同一个毫秒内产生的不同 id，
 *
 * @author CYL
 * @date 2019-01-14
 */
public class GlobalIdGenerator {

    /**
     * 机器 id 位数 5 bit
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 机房 id 位数 5 bit
     */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 序列号 id 位数 12 bit
     */
    private static final long SEQUENCE_ID_BITS = 12L;

    /**
     * 二进制运算，5 bit最多只能有32个数字，机器id最多只能是32以内
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 二进制运算，5 bit最多只能有32个数字，机房id最多只能是32以内
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 二进制运算，12 bit最多只能有4096个数字，序列号最多只能是4096以内
     */
    private static final long MAX_SEQUENCE_ID = ~(-1L << SEQUENCE_ID_BITS);

    /**
     * 机器 id 左移 12 位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_ID_BITS;

    /**
     * 机房 id 左移 12 + 5 = 17 位
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_ID_BITS + WORKER_ID_BITS;

    /**
     * 时间戳 左移 12 + 5+ 5 = 22 位
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_ID_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 系统标记时间(纪元时间)：ID内毫秒数 = 当前系统时间 - 标记时间
     */
    private static final long EPOCH = 1532007000000L;

    /**
     * 机房 id
     */
    private long dataCenterId;

    /**
     * 机器 id
     */
    private long workerId;

    /**
     * 序列 id
     */
    private long sequenceId;

    /**
     * 记录最近一次生成id使用的时间戳
     */
    private long lastTimestamp = -1L;

    public GlobalIdGenerator(long dataCenterId, long workerId, long sequenceId) {
        // 合理性检查：要求传递进来的机房id和机器id不能超过32，不能小于0
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        System.out.printf("worker starting. \n" +
                "timestamp left shift %d, dataCenter id bits %d, worker id bits %d, sequence id bits %d. \n" +
                "Maximum: dataCenterId %d, workerId %d, sequenceId %d. \n" +
                "Current: dataCenterId %d, workerId %d, sequenceId %d. \n",
                TIMESTAMP_LEFT_SHIFT, DATA_CENTER_ID_BITS, WORKER_ID_BITS, SEQUENCE_ID_BITS,
                MAX_DATA_CENTER_ID, MAX_WORKER_ID, MAX_SEQUENCE_ID, dataCenterId, workerId, sequenceId);

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequenceId = sequenceId;
    }

    public synchronized long nextId() {

        // 获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        // 系统时间异常
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 同个毫秒内再次获取Id
        if (lastTimestamp == timestamp) {
            // 位运算：一个毫秒内最多只能有4096个数字
            // 无论你传递多少进来，这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequenceId = (sequenceId + 1) & MAX_SEQUENCE_ID;
            if (sequenceId == 0) {
                // 同个毫秒内序列号用完，重置为0，要获取新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳不一致，序列号重置为 0
            sequenceId = 0;
        }

        // 记录最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        // 将时间戳左移，放到 41bit 那儿；
        // 将机房 id 左移，放到 5bit 那儿；
        // 将机器 id 左移，放到 5bit 那儿；
        // 将序列号，放到最后 12bit；
        // 最后拼接成一个 64bit 的二进制数字，转换成 10 进制就是个 long 型
        return ((timestamp - EPOCH) << TIMESTAMP_LEFT_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT) | sequenceId;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        GlobalIdGenerator generator = new GlobalIdGenerator(0, 0, 0);
        for (int i = 0; i < 30; i++) {
            System.out.println(generator.nextId());
        }
    }

}
