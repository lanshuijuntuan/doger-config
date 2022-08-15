package com.doger.stream.controller;

class MyTestClass {
    public static void main(String[] args) {
        TestClass myTestClass=new SubTestClass();
//        myTestClass.doSomeThing();

        myTestClass.hello();
        myTestClass.hello2();

        SubTestClass subTestClass=new SubTestClass();
        subTestClass.doSomeThing();

        subTestClass.hello();
        subTestClass.hello2();


    }


}

abstract class TestClass  {
    private void doSomeThing() {
        System.out.println("super do it \n");
    }


    public abstract void hello();


    public void hello2(){
        System.out.println("TestClass Say hello2");
    }


}

class SubTestClass extends TestClass {
    public void doSomeThing() {
        System.out.println("child do it \n");
    }

    @Override
    public void hello() {
        System.out.println("SubTestClass Say hello");
    }


    @Override
    public void hello2(){
        super.hello2();
        System.out.println("SubTestClass Say hello2");
    }
}




