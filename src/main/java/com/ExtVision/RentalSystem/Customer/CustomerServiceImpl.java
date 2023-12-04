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
    public boolean canRent(String customerID) {
        // Customer can rent if account is active and balance is not negative
        // This is simply to be used for checks and to stop weird things like negative balances renting etc.
        
        CustomerClass customer = customerMap.get(customerID);
        return customer != null && "Active".equals(customer.getAccountStatus()) && customer.getBalance() >= 0;
    }

    @Override
    public void rentItem(String customerID, int itemID) {
        CustomerClass customer = customerMap.get(customerID);
        if (customer != null && canRent(customerID)) {
            customer.getCurrentRentals().add(itemID);
        }
    }

    @Override
    public void setCanRent(String customerID) {
        CustomerClass customer = customerMap.get(customerID);
        customer.setActive(true);
    }

    @Override
    public void returnItem(String customerID, int itemID) {
        CustomerClass customer = customerMap.get(customerID);
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
    public void payFees(String customerID, float amount) {
        CustomerClass customer = customerMap.get(customerID);
        if (customer != null && amount > 0) {
            customer.setBalance(customer.getBalance() - amount);
        }
    }

    @Override
    public CustomerClass getCustomerDetails(String customerID) {
        return customerMap.get(customerID);
    }

    @Transactional
    public void updateCustomerProfile(String customerID, String name, String address, double phoneNum, String email) {
        CustomerClass customer = customerMap.get(customerID);
        if (customer != null) {
            customer.setName(name);
            customer.setAddress(address);
            customer.setphoneNum(phoneNum);
            customer.setEmail(email);
        }
    }
    @Transactional
    public void updateCustomer(CustomerClass customer) {
        CustomerClass existingCustomerClass = customerRepository.findById(customer.getCustomerID())
        .orElseThrow(() -> new RuntimeException("DVDGame not found with ID: " + customer.getCustomerID()));

        if (customer != null) {
            existingCustomerClass.setName(customer.getName());
            existingCustomerClass.setAddress(customer.getAddress());
            existingCustomerClass.setphoneNum(customer.getPhoneNum());
            existingCustomerClass.setEmail(customer.getEmail());
        }
    }
    

    public CustomerClass findById(int customerID) {
        // Find and return customer by ID
        return customerRepository.findById(customerID).orElse(null);

    }
    @Transactional
    public void save(CustomerClass customerlocal) {
        if (customerlocal.getCustomerID() == 0) {
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
