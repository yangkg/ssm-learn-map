package com.common.utils;

;
;import com.alibaba.druid.sql.visitor.functions.Now;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.sound.midi.Soundbank;

import static org.joda.time.DateTime.now;

/**
 * @author：Kyle.yangkg
 * @create：2017-12-21 上午 10:46
 * ©copyright：www.aisino.com
 */
public class LongAndlong {
    public static void main(String[] args) {
        final DateTime begin = now();
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i ;
        }
        System.out.println(sum);
        System.out.println(new Duration(begin, now()).getMillis());

        final DateTime begin1 = now();
        Long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i ;
        }
        System.out.println(sum1);
        System.out.println(new Duration(begin1, now()).getMillis());

    }
}
