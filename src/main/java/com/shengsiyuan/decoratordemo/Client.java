package com.shengsiyuan.decoratordemo;

/**
 * Created by yinhao on 2017/7/26.
 */
public class Client {
    public static void main(String[] args) {
        AbstractComponent component = new ConcreteDecorator(new ConcreteComponent());
        component.doSomething();
    }
}
