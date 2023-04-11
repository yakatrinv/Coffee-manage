package it.academy.utils;

public class DataAuth {
    //fields and parameters
    public static final String ATTR_ROLE = "role";

    public static final String ATTR_ROLE_ID = "role_id";

    public static final String ATTR_DB_ROLE_NAME = "role_name";

    public static final String ATTR_ROLE_NAME = "roleName";

    public static final String ATTR_USER = "user";

    public static final String ATTR_USER_ID = "user_id";

    public static final String ATTR_LOGIN = "login";

    public static final String ATTR_PASSWORD = "password";

    public static final String USER_ROLE = "user_role";

    //roles
    public static final String ROLE_ADMINISTRATOR = "Administrator";

    public static final String ROLE_MANAGER = "Manager";

    public static final String ROLE_CUSTOMER = "Customer";

    //parameters for work with jsp
    public static final String ATTR_LOGGED_USER = "loggedUser";

    public static final String ATTR_LOGGED_ROLE = "loggedRole";

    public static final String ATTR_LOGGED_CUSTOMER = "loggedCustomer";

    public static final String ATTR_SEARCH_ROLE_NAME = "searchRoleName";

    public static final String ATTR_SEARCH_LOGIN = "searchLogin";

    public static final String ATTR_USER_ROLES = "userRoles";

    public static final String ATTR_ROLES = "roles";

    public static final String ATTR_CHECK_ROLES = "checkRoles";

    public static final String ATTR_OLD_PASSWORD = "oldPassword";

    //jsp
    public static final String LOGIN_JSP = "auth/login.jsp";

    public static final String REGISTRATION_JSP = "auth/registration.jsp";

    //jsp roles
    public static final String ROLES_JSP = "view/role/roles.jsp";

    public static final String ADD_ROLE_JSP = "view/role/addRole.jsp";

    public static final String EDIT_ROLE_JSP = "view/role/editRole.jsp";

    //jsp users
    public static final String USERS_JSP = "view/user/users.jsp";

    public static final String ADD_USER_JSP = "view/user/addUser.jsp";

    public static final String EDIT_PASS_USER_JSP = "view/user/editPassUser.jsp";

    public static final String EDIT_ROLES_USER_JSP = "view/user/editRolesUser.jsp";

    public static final String EDIT_PROFILE_JSP = "general/editProfile.jsp";

    //commands auth
    public static final String GET_LOGIN_PAGE = "getLoginPage";

    public static final String GET_REG_PAGE = "getRegPage";

    public static final String LOGIN_COMMAND = "login";

    public static final String LOGOUT_COMMAND = "logout";

    public static final String REGISTRATION = "registration";

    public static final String CHANGE_ROLE = "changeRole";

    //commands users
    public static final String COMMAND_USERS = "users";

    public static final String ADD_USERS = "createUser";

    public static final String SAVE_USERS = "saveUser";

    public static final String EDIT_PASS_USER = "editPassUser";

    public static final String EDIT_PASS_CURRENT_USER = "editPassCurrentUser";

    public static final String UPDATE_PASS_USER = "updatePassUser";

    public static final String EDIT_ROLES_USER = "editRolesUser";

    public static final String UPDATE_ROLES_USER = "updateRolesUser";

    public static final String DELETE_USER = "deleteUser";

    //commands roles
    public static final String COMMAND_ROLES = "roles";

    public static final String ADD_ROLE = "createRole";

    public static final String SAVE_ROLE = "saveRole";

    public static final String EDIT_ROLE = "editRole";

    public static final String UPDATE_ROLE = "updateRole";

    public static final String DELETE_ROLE = "deleteRole";
}
