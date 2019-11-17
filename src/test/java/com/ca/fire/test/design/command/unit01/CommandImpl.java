package com.ca.fire.test.design.command.unit01;

public class CommandImpl extends Command {
    public CommandImpl(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.receive();

    }
}
