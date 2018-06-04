package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Every action to user request is represented by a command.
 * Every command implements {@code Command}
 */
public interface Command {

    String execute(HttpServletRequest req) throws CommandException;
}
