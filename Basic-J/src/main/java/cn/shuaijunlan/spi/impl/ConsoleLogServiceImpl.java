package cn.shuaijunlan.spi.impl;

import cn.shuaijunlan.spi.ILogService;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:14 PM 2018/08/04.
 */
public class ConsoleLogServiceImpl implements ILogService {
    @Override
    public void warn(String msg) {
        System.out.println("Console log:"+ msg + "!");
    }
}
