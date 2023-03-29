package it.academy.services.impl;

import it.academy.dto.CustomerDto;
import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CustomerMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.auth.UserMapper;
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

    private final Mapper<Pageable<Customer>, Pageable<CustomerDto>> mapperP =
            new PageableMapper<>(customerMapper);

    private final IUserService userService = new UserService();

    private final IRoleService roleService = new RoleService();

    private final Mapper<User, UserDto> userMapper = new UserMapper();

    @Override
    public void createCustomer(String login, String password, CustomerDto customerDto) {
        RoleDto roleCustomer = roleService.findByRoleName(ROLE_CUSTOMER);
        Set<RoleDto> roles = new HashSet<>();
        roles.add(roleCustomer);
        UserDto user = userService.createUser(login, password, roles);

        if (user != null) {
            Customer customer = customerMapper.dtoToEntity(customerDto);
            customer.setUser(userMapper.dtoToEntity(user));
            customer = repository.save(customer);
            customerMapper.entityToDto(customer);
        }
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
}
