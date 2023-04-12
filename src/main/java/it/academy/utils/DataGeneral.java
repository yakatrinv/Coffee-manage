package it.academy.utils;

import it.academy.models.Address;
import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.Discount;
import it.academy.models.Machine;
import it.academy.models.Model;
import it.academy.models.Product;
import it.academy.models.Purchase;
import it.academy.models.TypePayment;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DataGeneral {
    public static final String PERSISTENCE_NAME = "property";

    //classes
    public static final Class<Long> LONG_CLASS = Long.class;

    public static final Class<Float> FLOAT_CLASS = Float.class;

    public static final Class<String> STRING_CLASS = String.class;

    public static final Class<String[]> STRING_ARRAY_CLASS = String[].class;

    public static final Class<LocalDateTime[]> DATE_ARRAY_CLASS = LocalDateTime[].class;

    public static final Class<Float[]> FLOAT_ARRAY_CLASS = Float[].class;

    public static final Class<Role> ROLE_CLASS = Role.class;

    public static final Class<User> USER_CLASS = User.class;

    public static final Class<Address> ADDRESS_CLASS = Address.class;

    public static final Class<CreditCard> CREDIT_CARD_CLASS = CreditCard.class;

    public static final Class<Customer> CUSTOMER_CLASS = Customer.class;

    public static final Class<Discount> DISCOUNT_CLASS = Discount.class;

    public static final Class<Machine> MACHINE_CLASS = Machine.class;

    public static final Class<Product> PRODUCT_CLASS = Product.class;

    public static final Class<Model> MODEL_CLASS = Model.class;

    public static final Class<TypePayment> TYPE_PAYMENT_CLASS = TypePayment.class;

    public static final Class<Purchase> PURCHASE_CLASS = Purchase.class;

    //general
    public static final int VALUE_ZERO = 0;

    public static final String UTF_8 = "UTF-8";

    public static final String ATTR_ID = "id";

    public static final String STRING_FROM = "FROM ";

    public static final String PERCENT_STRING = "%";

    public static final String ON = "on";

    public static final LocalDateTime START_DATE = LocalDateTime.of(LocalDate.ofYearDay(2023,1), LocalTime.MIN);

    //for work with jsp
    public static final String ATTR_COMMAND = "command";

    public static final String PREV_URL = "prevURL";

    public static final String ATTR_MESSAGE = "message";

    //error message
    public static final String ERROR_AUTH = "Ошибка авторизации. Неверный логин или пароль.";

    public static final String ERROR_ROLE = "Отсутствуют роли";

    public static final String ERROR_REG = "Пользователь уже существует";

    //jsp
    public static final String MAIN_JSP = "view/general/main.jsp";

    public static final String ERROR_JSP = "auth/error.jsp";

    //commands
    public static final String GET_HOME_PAGE = "getHomePage";
}
