package com.ca.fire.test.design.command.unit03;

public class Programmer implements Command {
    @Override
    public void execute() {
        System.out.println("Programmer do someThing");
    }
}
