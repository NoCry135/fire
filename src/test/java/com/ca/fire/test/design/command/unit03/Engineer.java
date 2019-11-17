package com.ca.fire.test.design.command.unit03;

public class Engineer implements Command {
    @Override
    public void execute() {
        System.out.println("Engineer do someThing");
    }
}
