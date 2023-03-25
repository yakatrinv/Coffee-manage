package it.academy.utils;

import it.academy.models.Address;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;

public class Data {
    //auth
    public static final String SECURE_ALG = "SHA1PRNG";

    public static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int KEY_LENGTH = 128;

    public static final int RADIX = 16;

    public static final int SALT_LENGTH = 16;

    public static final int ITERATIONS = 10000;

    public static final String ATTR_LOGGED_USER = "loggedUser";

    public static final String ATTR_USER_ROLES = "userRoles";

    public static final String ATTR_USERS = "users";

    public static final String MAIN_JSP = "view/general/main.jsp";

    public static final String LOGIN_JSP = "view/auth/login.jsp";

    public static final String ERROR_JSP = "view/auth/error.jsp";

    public static final String REGISTRATION_JSP = "view/auth/registration.jsp";

    public static final String GET_LOGIN_PAGE = "getLoginPage";

    public static final String GET_REG_PAGE = "getRegPage";

    public static final String LOGIN_COMMAND = "login";

    public static final String LOGOUT_COMMAND = "logout";

    public static final String REGISTRATION = "registration";

    //general
    public static final String ATTR_COMMAND = "command";
    public static final String PERSISTENCE_NAME = "property";

    public static final String STRING_FROM = "FROM ";

    public static final String PERCENT_STRING = "%";

    public static final Class<Long> LONG_CLASS = Long.class;

    public static final Class<Address> ADDRESS_CLASS = Address.class;

    public static final String UTF_8 = "UTF-8";

    public static final String ATTR_ID = "id";

    public static final int FIRST_PAGE = 1;

    public static final int DEFAULT_PAGE_SIZE = 5;


    //users and roles
    public static final String ROLE_ADMIN = "Administrator";

    public static final String ROLE_MANAGER = "Manager";

    public static final String ROLE_CUSTOMER = "Customer";

    public static final String ROLE = "role";

    public static final String USER_ROLE = "user_role";

    public static final String ATTR_LOGIN = "login";

    public static final String ATTR_PASSWORD = "password";

    public static final String ATTR_USER_ID = "user_id";

    public static final String ATTR_ROLE_ID = "role_id";

    public static final String ROLE_NAME = "roleName";

    public static final Class<Role> ROLE_CLASS = Role.class;

    public static final Class<User> USER_CLASS = User.class;

    public static final int FIRST_INDEX = 0;

    //pageable
    public static final String PAGEABLE = "pageable";

    public static final String PAGE_NUMBER = "pageNumber";

    public static final String PAGE_SIZE = "pageSize";

    public static final String PREV_URL = "prevURL";

    //addresses entity
    public static final String ATTR_CITY = "city";

    public static final String ATTR_STREET = "street";

    public static final String ATTR_SEARCH_CITY = "searchCity";

    public static final String ATTR_SEARCH_STREET = "searchStreet";

    public static final String COMMAND_ADDRESSES = "addresses";
    public static final String ATTR_ADDRESS = "address";

    public static final String ADD_ADDRESS = "createAddress";

    public static final String SAVE_ADDRESS = "saveAddress";

    public static final String EDIT_ADDRESS = "editAddress";

    public static final String UPDATE_ADDRESS = "updateAddress";

    public static final String DELETE_ADDRESS = "deleteAddress";

    public static final String ADDRESSES_JSP = "view/address/addresses.jsp";

    public static final String ADD_ADDRESS_JSP = "view/address/addAddress.jsp";

    public static final String EDIT_ADDRESS_JSP = "view/address/editAddress.jsp";
}
