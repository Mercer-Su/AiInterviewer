package org.example.enums;

/**
 * @Desc: 延迟市场枚举
 */
public enum DelayTimes {
    first(1, 5 * 60 * 1000),                // 5分钟
    second(2, 15 * 60 * 1000),              // 15分钟
    third(3, 30 * 60 * 1000),               // 30分钟
    fourth(4, 60 * 60 * 1000),              // 60分钟
    fifth(5, 3 * 60 * 60 * 1000),           // 3小时
    sixth(6, 6 * 60 * 60 * 1000),           // 6小时
    seventh(7, 12 * 60 * 60 * 1000),        // 12小时
    eighth(8, 24 * 60 * 60 * 1000),         // 24小时
    ninth(9, 2 * 24 * 60 * 60 * 1000);      // 2天

    public final Integer counts;
    public final Integer times;

    DelayTimes(Integer counts, Integer times) {
        this.counts = counts;
        this.times = times;
    }

    public static Integer getDelayTimes(Integer counts) {
        DelayTimes[] delayTimes = DelayTimes.values();
        for (DelayTimes dt :delayTimes) {
            if (dt.counts == counts) {
                return dt.times;
            }
        }
        // 如果没有匹配的，则使用默认的5分钟
        return delayTimes[delayTimes.length-1].times;
    }
}
