package com.br.lp2.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camila
 */
public interface Command {
    public void init(HttpServletRequest request,
            HttpServletResponse response);
    public void execute();
    public String getResponsePage();
}
