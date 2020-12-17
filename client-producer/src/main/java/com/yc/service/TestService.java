package com.yc.service;

/**
 *
 * @Author yucheng
 * @Date 2020/12/16 16:37
 */
public interface TestService {

    public String testOOM(int number);

    public void testDeadLock();

    public void testBlock();

}
