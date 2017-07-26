package com.shengsiyuan.decoratordemo;

/**
 * Created by yinhao on 2017/7/26.
 * 装饰模式：具体构件角色
 */
public class ConcreteComponent implements AbstractComponent {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
