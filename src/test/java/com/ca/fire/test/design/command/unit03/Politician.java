package com.ca.fire.test.design.command.unit03;

public class Politician implements Command {
    @Override
    public void execute() {
        System.out.println("Politician do someThing");
    }
}
