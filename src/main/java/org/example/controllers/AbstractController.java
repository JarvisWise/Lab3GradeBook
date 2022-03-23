package org.example.controllers;

import org.example.entities.User;
import org.example.tools.strings.SessionAttributeName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {
    protected void setBaseSessionVariables(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(false);
        session.setAttribute(
                SessionAttributeName.CURRENT_USER_ID.getSessionAttributeName(),
                user.getUserId()
        );
        session.setAttribute(
                SessionAttributeName.CURRENT_USERNAME.getSessionAttributeName(),
                user.getFullName()
        );
        session.setAttribute(
                SessionAttributeName.CURRENT_USER_ROLE.getSessionAttributeName(),
                user.getRole()
        );
    }

    //protected ModelAndView createHeader()
}
