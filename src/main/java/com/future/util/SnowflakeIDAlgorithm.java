package com.future.util;

public class SnowflakeIDAlgorithm {
    // 起始时间戳，以毫秒为单位（设定为2023年3月24日00:00:00）
    private static final long START_TIMESTAMP = 1670000000000L;

    // worker id 和 datacenter id 所占用的位数
    private static final long WORKER_ID_BITS = 5L; // 机器标识位数 5
    private static final long DATACENTER_ID_BITS = 5L; // 数据中心标识位数 5

    // worker id 和 datacenter id 的最大值
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS); // 机器 ID 的最大值 31
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS); // 数据中心 ID 的最大值 31

    // 序列号所占用的位数
    private static final long SEQUENCE_BITS = 12L; // 毫秒内自增位 12

    // worker id 和 datacenter id 向左移动的位数
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS; // 12
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    // 序列号的掩码
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    // 当前 worker id 和 datacenter id
    private final long workerId;
    private final long datacenterId;

    // 上次生成 ID 的时间戳和序列号
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    /**
     * 构造函数
     * @param datacenterId 数据中心 ID
     * @param workerId 机器 ID
     */
    public SnowflakeIDAlgorithm(long datacenterId, long workerId) {
        // 判断 worker id 和 datacenter id 是否在有效范围内
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个 ID
     * @return long
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上次生成 ID 的时间戳，说明系统时钟回退过，抛出运行时异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果当前时间等于上次生成 ID 的时间戳，则将序列号加一，最多只能达到4096，因为 SEQUENCE_BITS=12
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // 如果序列号已经达到最大值，则等待下一个毫秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 如果当前时间大于上次生成 ID 的时间戳，序列号重置为 0
            sequence = 0L;
        }

        // 更新上次生成 ID 的时间戳
        lastTimestamp = timestamp;

        // 生成新的 ID，并返回
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    /**
     * 等待下一个毫秒
     * @param lastTimestamp 上次生成 ID 的时间戳
     * @return long
     */
    // 当前时间与 lastTimestamp 相等时，调用此方法等待下一个时间戳
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}

