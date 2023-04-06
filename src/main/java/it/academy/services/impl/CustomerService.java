package it.academy.services.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.auth.RoleDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CreditCardMapper;
import it.academy.mappers.impl.CustomerMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.impl.CustomerRepository;
import it.academy.services.ICustomerService;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.RoleService;
import it.academy.services.auth.UserService;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static it.academy.utils.Data.ROLE_CUSTOMER;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository repository
            = new CustomerRepository();

    private final Mapper<Customer, CustomerDto> customerMapper = new CustomerMapper();

    private final Mapper<CreditCard, CreditCardDto> creditCardMapper = new CreditCardMapper();

    private final Mapper<Pageable<Customer>, Pageable<CustomerDto>> mapperP =
            new PageableMapper<>(customerMapper);

    private final IUserService userService = new UserService();

    private final IRoleService roleService = new RoleService();

    @Override
    public CustomerDto createCustomer(String login, String password, CustomerDto customerDto) {
        RoleDto roleCustomer = roleService.findByRoleName(ROLE_CUSTOMER);
        Set<RoleDto> roles = new HashSet<>();
        roles.add(roleCustomer);
        User user = userService.createUser(login, password, roles);
        Customer customer = customerMapper.dtoToEntity(customerDto);

        if (user != null) {
            customer.setUser(user);
            customer = repository.save(customer);
        }

        return customerMapper.entityToDto(customer);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.dtoToEntity(customerDto);

        Customer findCustomer = repository.getById(customer.getId());
        customer.setUser(findCustomer.getUser());

        customer = repository.update(customer);
        customerMapper.entityToDto(customer);
    }

    @Override
    public CustomerDto findCustomerById(Serializable id) {
        Customer customer = repository.getById(id);
        return customerMapper.entityToDto(customer);
    }

    @Override
    public void deleteCustomerById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = repository.getAll();
        return customers
                .stream()
                .map(customerMapper::entityToDto)
                .toList();
    }

    @Override
    public Pageable<CustomerDto> getPageableRecords(Pageable<CustomerDto> pageableDto) {
        Pageable<Customer> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public CustomerDto getCustomerByLoginUser(String login) {
        Customer customer = repository.getCustomerByLoginUser(login);
        return customer == null ? null : customerMapper.entityToDto(customer);
    }

    @Override
    public List<CreditCardDto> getCreditCards(Serializable id) {
        List<CreditCard> creditCards = repository.getCreditCards(id);
        return creditCards
                .stream()
                .map(creditCardMapper::entityToDto)
                .toList();
    }
}
