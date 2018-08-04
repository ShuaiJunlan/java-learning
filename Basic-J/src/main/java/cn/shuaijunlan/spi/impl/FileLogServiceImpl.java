package cn.shuaijunlan.spi.impl;

import cn.shuaijunlan.spi.ILogService;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:15 PM 2018/08/04.
 */
public class FileLogServiceImpl implements ILogService {
    @Override
    public void warn(String msg) {
        System.out.println("File log:" + msg +"!");
    }
}
