package it.academy.services.impl;

import it.academy.dto.CustomerDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CustomerMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.impl.CustomerRepository;
import it.academy.services.ICustomerService;
import it.academy.services.auth.IUserAuthService;
import it.academy.services.auth.UserAuthService;

import java.io.Serializable;

public class CustomerService implements ICustomerService {
    private final Mapper<Customer, CustomerDto> mapper = new CustomerMapper();

    private final Mapper<Pageable<Customer>, Pageable<CustomerDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final ICustomerRepository repository
            = new CustomerRepository();

    private final IUserAuthService userAuthService = new UserAuthService();

    @Override
    public CustomerDto createCustomer(String login, String password, CustomerDto customerDto) {
        User user = userAuthService.createCustomerUser(login, password);

        Customer entity = mapper.dtoToEntity(customerDto);
        entity.setUser(user);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer entity = mapper.dtoToEntity(customerDto);

        Customer findCustomer = repository.getById(entity.getId());
        entity.setUser(findCustomer.getUser());

        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public CustomerDto findCustomerById(Serializable id) {
        Customer entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteCustomerById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<CustomerDto> getPageableRecords(Pageable<CustomerDto> pageableDto) {
        Pageable<Customer> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
