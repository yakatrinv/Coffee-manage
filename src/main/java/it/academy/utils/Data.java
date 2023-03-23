package it.academy.utils;

import it.academy.models.Address;

public class Data {
    //general
    public static final String PERSISTENCE_NAME = "property";

    public static final String STRING_FROM = "FROM ";

    public static final String PERCENT_STRING = "%";

    public static final Class<Long> LONG_CLASS = Long.class;

    public static final Class<Address> ADDRESS_CLASS = Address.class;

    public static final String UTF_8 = "UTF-8";

    public static final String ATTR_ID = "id";

    public static final int FIRST_PAGE = 1;

    public static final int DEFAULT_PAGE_SIZE = 5;

    public static final String ATTR_COMMAND = "command";

    //pageable
    public static final String PAGEABLE = "pageable";

    public static final String PAGE_NUMBER = "pageNumber";

    public static final String PAGE_SIZE = "pageSize";

    public static final String PREV_URL="prevURL";

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

    public static final String ADDRESSES_JSP = "address/addresses.jsp";

    public static final String ADD_ADDRESS_JSP = "address/addAddress.jsp";

    public static final String EDIT_ADDRESS_JSP = "address/editAddress.jsp";
}
