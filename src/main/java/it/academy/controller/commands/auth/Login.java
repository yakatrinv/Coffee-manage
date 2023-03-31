package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.services.ICustomerService;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.Data.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.Data.ATTR_LOGGED_ROLE;
import static it.academy.utils.Data.ATTR_LOGGED_USER;
import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_MESSAGE;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.ATTR_USER_ROLES;
import static it.academy.utils.Data.ERROR_AUTH;
import static it.academy.utils.Data.ERROR_JSP;
import static it.academy.utils.Data.ERROR_ROLE;
import static it.academy.utils.Data.LOGIN_JSP;
import static it.academy.utils.Data.MAIN_JSP;
import static it.academy.utils.Data.ROLE_ADMINISTRATOR;
import static it.academy.utils.Data.ROLE_MANAGER;

public class Login implements Command {
    IUserService userService = new UserService();

    ICustomerService customerService = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(ATTR_LOGGED_USER);

        if (userDto != null) {
            return MAIN_JSP;
        } else {
            String login = request.getParameter(ATTR_LOGIN);
            String password = request.getParameter(ATTR_PASSWORD);

            if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
                return LOGIN_JSP;
            } else {
                UserDto authUser = userService.findAuthUser(login, password);
                session.setAttribute(ATTR_LOGGED_USER, authUser);

                if (authUser == null) {
                    request.setAttribute(ATTR_MESSAGE, ERROR_AUTH);
                    return ERROR_JSP;
                } else {
                    CustomerDto customer = customerService.getCustomerByLoginUser(authUser.getLogin());
                    session.setAttribute(ATTR_LOGGED_CUSTOMER, customer);

                    if (CollectionUtils.isEmpty(authUser.getRoles())) {
                        request.setAttribute(ATTR_MESSAGE, ERROR_ROLE);
                        return ERROR_JSP;
                    } else {
                        session.setAttribute(ATTR_USER_ROLES, authUser.getRoles());

                        RoleDto roleAdmin= RoleDto
                                .builder()
                                .roleName(ROLE_ADMINISTRATOR)
                                .build();

                        RoleDto roleManager= RoleDto
                                .builder()
                                .roleName(ROLE_MANAGER)
                                .build();

                        if (authUser.getRoles().contains(roleAdmin)) {
                            session.setAttribute(ATTR_LOGGED_ROLE, roleAdmin);
                        } else if (authUser.getRoles().contains(roleManager)) {
                            session.setAttribute(ATTR_LOGGED_ROLE, roleManager);
                        } else {
                            session.setAttribute(ATTR_LOGGED_ROLE, authUser.getRoles().iterator().next());
                        }

                        return MAIN_JSP;
                    }
                }
            }
        }
    }

}
