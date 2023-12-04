package com.ExtVision.RentalSystem.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ExtVision.RentalSystem.CustomerRepository;
import com.ExtVision.RentalSystem.DVD.DVDGame;
import com.ExtVision.RentalSystem.DVD.DVDGameObserver;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<String, CustomerClass> customerMap = new HashMap<>();
    private final CustomerRepository customerRepository;
    private final List<CustomerObserver> observers = new ArrayList<>();

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean canRent(String accountId) {
        // Customer can rent if account is active and balance is not negative
        // This is simply to be used for checks and to stop weird things like negative balances renting etc.
        
        CustomerClass customer = customerMap.get(accountId);
        return customer != null && "Active".equals(customer.getAccountStatus()) && customer.getBalance() >= 0;
    }

    @Override
    public void rentItem(String accountId, int itemID) {
        CustomerClass customer = customerMap.get(accountId);
        if (customer != null && canRent(accountId)) {
            customer.getCurrentRentals().add(itemID);
        }
    }

    @Override
    public void setCanRent(String accountId) {
        CustomerClass customer = customerMap.get(accountId);
        customer.setActive(true);
    }

    @Override
    public void returnItem(String accountId, int itemID) {
        CustomerClass customer = customerMap.get(accountId);
        if (customer != null && customer.getCurrentRentals().contains(itemID)) {
            customer.getCurrentRentals().remove(itemID);
        }
    }

    
    public List<CustomerClass> findActiveCustomers() {
        return customerRepository.findByAccountStatus("ACTIVE");
    }

    public List<CustomerClass> findInActiveCustomers() {
        return customerRepository.findByAccountStatus("INACTIVE");
    }

    @Override
    public void payFees(String accountId, float amount) {
        CustomerClass customer = customerMap.get(accountId);
        if (customer != null && amount > 0) {
            customer.setBalance(customer.getBalance() - amount);
        }
    }

    @Override
    public CustomerClass getCustomerDetails(String accountId) {
        return customerMap.get(accountId);
    }

    @Transactional
    public void updateCustomerProfile(String accountId, String name, String address, double phoneNum, String email) {
        CustomerClass customer = customerMap.get(accountId);
        if (customer != null) {
            customer.setName(name);
            customer.setAddress(address);
            customer.setphoneNum(phoneNum);
            customer.setEmail(email);
        }
    }
    @Transactional
    public void updateCustomer(CustomerClass customer) {
        CustomerClass existingCustomerClass = customerRepository.findById(customer.getaccountId())
        .orElseThrow(() -> new RuntimeException("DVDGame not found with ID: " + customer.getaccountId()));

        if (customer != null) {
            existingCustomerClass.setName(customer.getName());
            existingCustomerClass.setAddress(customer.getAddress());
            existingCustomerClass.setphoneNum(customer.getPhoneNum());
            existingCustomerClass.setEmail(customer.getEmail());
        }
    }
    

    public CustomerClass findById(Integer customerID) {
        // Find and return customer by ID
        return customerRepository.findById(customerID).orElse(null);

    }
    @Transactional
    public void save(CustomerClass customerlocal) {
        if (customerlocal.getaccountId() == 0) {
            customerlocal.setAccountStatus("AVAILABLE");
             
        }
        customerRepository.save(customerlocal);
        notifyObservers(customerlocal);
    }
    
    public void registerObserver(CustomerObserver observer) {
        observers.add(observer);
    }
    private void notifyObservers(CustomerClass customer) {
        for (CustomerObserver observer : observers) {
            observer.update(customer);
        }
    }

    public void delete(Integer customerID) {
        Optional<CustomerClass> customerlocal = customerRepository.findById(customerID);
        customerlocal.ifPresent(customer -> {
            customerRepository.deleteById(customerID);
            
        });
    }
}
