package com.ca.fire.test.design.command.unit02;

public class Invoker {
    //什么命令
    private Command command;

    //客户发出命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //执行客户的命令
    public void action() {
        this.command.execute();
    }

    public static void main(String[] args) {
        //定义我们的接头人
        Invoker xiaoSan = new Invoker(); //接头人就是我小三
        //客户要求增加一项需求
        System.out.println("-------------客户要求增加一项需求-----------------");
            //客户给我们下命令来
//        Command command = new AddRequirementCommand();
            //接头人接收到命令
//        xiaoSan.setCommand(command);
//            //接头人执行命令
//        xiaoSan.action();

        System.out.println("-------------客户要求删除一个页面-----------------");
        //客户给我们下命令来
        Command command = new DeletePageCommand();
        //接头人接收到命令
        xiaoSan.setCommand(command);
//接头人执行命令
        xiaoSan.action();
    }
}
