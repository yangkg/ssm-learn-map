package com.logstash;

import org.apache.log4j.Logger;

/**
 * 发送日志到logstash
 *
 * @author：Kyle.yangkg
 * @create：2017-08-16 下午 3:15
 * ©copyright：www.aisino.com
 */
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {

        for (int i = 0; i < 15000; i++) {
            switch (i%15) {
                case 0:
                    LOGGER.info("收到app请求报文为：[" + i + "].");
                    break;
                case 1:
                    LOGGER.info("登陆校验失败：[" + i + "].");
                    break;
                case 2:
                    LOGGER.info("设备验证业务异常：[" + i + "].");
                    break;
                case 3:
                    LOGGER.info("库存信息查询 异常：[" + i + "].");
                    break;
                case 4:
                    LOGGER.info("发票开具异常：[" + i + "].");
                    break;
                case 5:
                    LOGGER.info("接口处理数据失败,错误码：[" + i + "].");
                    break;
                case 6:
                    LOGGER.info("单张发票查询接口异常：[" + i + "].");
                    break;
                case 7:
                    LOGGER.info("订单列表查询接口异常：[" + i + "].");
                    break;
                case 8:
                    LOGGER.info("单张订单取消接口异常：[" + i + "].");
                    break;
                case 9:
                    LOGGER.info("税控设备无可用发票：[" + i + "].");
                    break;
                case 10:
                    LOGGER.info("税控设备已到离线期：[" + i + "].");
                    break;
                case 11:
                    LOGGER.info("税控设备已到报税期：[" + i + "].");
                    break;
                case 12:
                    LOGGER.info("税控设备已到锁死期：[" + i + "].");
                    break;
                case 13:
                    LOGGER.info("接口编码错误：[" + i + "].");
                    break;
                case 14:
                    LOGGER.info("为其他错误：[" + i + "].");
                    break;
                default:
                    LOGGER.info("未知异常：[" + i + "].");
                    break;
            }

            try {

                Thread.sleep(50);

            } catch (InterruptedException e) {

                e.printStackTrace();
                LOGGER.info("Info log [" + i + "].Exception:"+e.toString());

            }
        }
    }
}
