package com.shengsiyuan.decoratordemo;

/**
 * Created by yinhao on 2017/7/26.
 * 装饰模式：抽象装饰角色 满足 1 实现或继承抽象构件角色 2 持有抽象构件角色的引用
 */
public class AbstractDecorator implements AbstractComponent {

    private AbstractComponent abstractComponent;

    public AbstractDecorator(AbstractComponent abstractComponent){
        this.abstractComponent = abstractComponent;
    }

    @Override
    public void doSomething() {
        abstractComponent.doSomething();
    }

}
