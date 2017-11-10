package com.br.lp2.controller.command;

import com.br.lp2.model.dao.Userlp2DAO;
import com.br.lp2.model.javabeans.Userinfo;
import com.br.lp2.model.javabeans.Userlp2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Camila
 */
public class Userlp2Command implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;
    private Userlp2DAO dao;
    private String username;
    private String password;
    private Userlp2 user;
    private String password2;
    private String firstname;
    private String lastname;
    private String email;
    private String bday;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        dao = new Userlp2DAO();
        switch (action) {
            case "login":
                username = request.getParameter("username");
                password = request.getParameter("password");

                user = dao.findByUsername(username);
                if (user == null) {
                    request.getSession().setAttribute("errormsg", "You shall not pass! User not found!");
                    responsePage = "error.jsp";
                } else if (password.equals(user.getPassword())) {
                    request.getSession().setAttribute("user", user);
                    responsePage = "home.jsp";

                    String remember = request.getParameter("remember");
                    int maxage = 0;
                    if (remember != null) {
                        maxage = 60 * 60 * 24 * 7;
                    }

                    Cookie c1 = new Cookie("username", username);
                    c1.setMaxAge(maxage);
                    response.addCookie(c1);

                    Cookie c2 = new Cookie("password", password);
                    c2.setMaxAge(maxage);
                    response.addCookie(c2);

                } else {
                    request.getSession().setAttribute("errormsg", "You shall not pass! Wrong password!");
                    responsePage = "error.jsp";
                }
                break;
            case "register":
                username = request.getParameter("username");
                password = request.getParameter("password");
                password2 = request.getParameter("password2");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                email = request.getParameter("email");
                bday = request.getParameter("birthday");//yyyy-MM-dd

                Date birthday = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    //y - ano; M - mês; d - dia; h/H - hora; m - minutos; s - segundos
                    birthday = sdf.parse(bday);
                } catch (ParseException ex) {
                    Logger.getLogger(Userlp2Command.class.getName()).log(Level.SEVERE, null, ex);
                }

                user = dao.findByUsername(username);
                if (user != null) {
                    request.getSession().setAttribute("errormsg", "Username already in use.");
                    responsePage = "error.jsp";
                } else {
                    user = dao.findByEmail(email);
                    if (user != null) {
                        request.getSession().setAttribute("errormsg", "Email already in use.");
                        responsePage = "error.jsp";
                    } else if (!password.equals(password2)) {
                        request.getSession().setAttribute("errormsg", "Passwords don't match.");
                        responsePage = "error.jsp";
                    } else {
                        user = new Userlp2();
                        user.setUsername(username);
                        user.setPassword(password);

                        Userinfo ui = new Userinfo();
                        ui.setFirstname(firstname);
                        ui.setLastname(lastname);
                        ui.setEmail(email);
                        ui.setBirthday(birthday);

                        user.setUserinfo(ui);
                        dao.insert(user);
                        responsePage = "index.jsp";
                    }
                }
                break;
            case "logout":
                request.getSession().invalidate();
                responsePage = "index.jsp";
                break;
            case "update":
                user = (Userlp2) request.getSession().getAttribute("user");

                password = request.getParameter("password");
                password2 = request.getParameter("password2");
                firstname = request.getParameter("firstname");
                lastname = request.getParameter("lastname");
                bday = request.getParameter("birthday");//yyyy-MM-dd

                Date birthday2 = new Date();
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    //y - ano; M - mês; d - dia; h/H - hora; m - minutos; s - segundos
                    birthday2 = sdf2.parse(bday);
                } catch (ParseException ex) {
                    Logger.getLogger(Userlp2Command.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (password.equals("") && password2.equals("")) {
                    password = user.getPassword();
                } else if (!password.equals(password2)) {
                    request.getSession().setAttribute("errormsg", "Passwords don't match.");
                    responsePage = "error.jsp";
                }

                user.setPassword(password);

                Userinfo ui = user.getUserinfo();
                ui.setFirstname(firstname);
                ui.setLastname(lastname);
                ui.setBirthday(birthday2);

                user.setUserinfo(ui);
                dao.modify(user);
                responsePage = "home.jsp";

                break;
            case "remove":
                user = (Userlp2) request.getSession().getAttribute("user");
                dao.remove(user);
                request.getSession().invalidate();
                responsePage = "index.jsp";

                Cookie c1 = new Cookie("username", username);
                c1.setMaxAge(0);
                response.addCookie(c1);

                Cookie c2 = new Cookie("password", password);
                c2.setMaxAge(0);
                response.addCookie(c2);
                break;
        }

    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }

}
