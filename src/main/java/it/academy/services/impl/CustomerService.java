package it.academy.services.impl;

import it.academy.dto.CustomerDto;
import it.academy.dto.auth.RoleDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CustomerMapper;
import it.academy.mappers.impl.PageableMapper;
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

import static it.academy.utils.DataAuth.ROLE_CUSTOMER;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository repository
            = new CustomerRepository();

    private final Mapper<Customer, CustomerDto> mapper = new CustomerMapper();

    private final Mapper<Pageable<Customer>, Pageable<CustomerDto>> mapperP =
            new PageableMapper<>(mapper);

    private final IUserService userService = new UserService();

    private final IRoleService roleService = new RoleService();

    @Override
    public void createCustomer(String login, String password, CustomerDto customerDto) {
        RoleDto roleCustomer = roleService.findRoleByName(ROLE_CUSTOMER);
        Set<RoleDto> roles = new HashSet<>();
        roles.add(roleCustomer);
        User user = userService.createUser(login, password, roles);
        Customer customer = mapper.dtoToEntity(customerDto);

        if (user != null) {
            customer.setUser(user);
            repository.save(customer);
        }
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = mapper.dtoToEntity(customerDto);

        Customer findCustomer = repository.getById(customer.getId());
        customer.setUser(findCustomer.getUser());

        customer = repository.update(customer);
        mapper.entityToDto(customer);
    }

    @Override
    public CustomerDto findCustomerById(Serializable id) {
        Customer customer = repository.getById(id);
        return mapper.entityToDto(customer);
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
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public CustomerDto getCustomerByLoginUser(String login) {
        Customer customer = repository.getCustomerByLoginUser(login);
        return customer == null ? null : mapper.entityToDto(customer);
    }

    @Override
    public Pageable<CustomerDto> getPageableRecords(Pageable<CustomerDto> pageableDto) {
        Pageable<Customer> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
