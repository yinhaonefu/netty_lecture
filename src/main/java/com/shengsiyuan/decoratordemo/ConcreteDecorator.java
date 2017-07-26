package com.shengsiyuan.decoratordemo;

/**
 * Created by yinhao on 2017/7/26.
 * 装饰模式：
 */
public class ConcreteDecorator extends AbstractDecorator{

    public ConcreteDecorator(AbstractComponent abstractComponent){
        super(abstractComponent);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        doAnotherThing();//附加功能
    }

    private void doAnotherThing(){
        System.out.println("功能B");
    }
}
