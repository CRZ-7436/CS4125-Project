
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ExtVision.RentalSystem.Controllers.registerController;
import com.ExtVision.RentalSystem.Customer.CustomerClass;
import com.ExtVision.RentalSystem.Customer.CustomerServiceImpl;
import com.ExtVision.RentalSystem.LoginFunc.LoginClassInterface;
import com.ExtVision.RentalSystem.LoginFunc.LoginStateFactory.LoginState;

public class RegisterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private LoginClassInterface loginClass;

    @InjectMocks
    private registerController registerController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/registerCustomer"))
               .andExpect(status().isOk())
               .andExpect(view().name("register"))
               .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void testSuccessfulRegister() throws Exception {
        when(loginClass.registerAccount(anyString(), anyString(), anyBoolean())).thenReturn(LoginState.LOGGED_IN.getMessage());

        mockMvc.perform(post("/registerCustomer/register")
                .param("username", "testUser")
                .param("password", "password")
                .param("address", "123 Test St")
                .param("phoneNum", "1234567890")
                .param("email", "test@test.com")
                .param("admin", "false"))
               .andExpect(redirectedUrl("/index"));

        verify(customerServiceImpl).save(any(CustomerClass.class));
    }

    @Test
    public void testFailedRegister() throws Exception {
        when(loginClass.registerAccount(anyString(), anyString(), anyBoolean())).thenReturn(LoginState.LOGIN_FAILED.getMessage());

        mockMvc.perform(post("/registerCustomer/register")
                .param("username", "invalidUser")
                .param("password", "wrongpassword")
                .param("address", "123 Test St")
                .param("phoneNum", "1234567890")
                .param("email", "invalid@test.com")
                .param("admin", "false"))
               .andExpect(view().name("register"))
               .andExpect(model().attributeExists("error"));

        verify(customerServiceImpl, never()).save(any(CustomerClass.class));
    }

    @Test
    public void testAddOrUpdateCustomer() throws Exception {
        mockMvc.perform(post("/registerCustomer/add")
                .flashAttr("customer", new CustomerClass()))
               .andExpect(redirectedUrl("/index"));

        verify(customerServiceImpl).save(any(CustomerClass.class));
    }

    @Test
    public void testUpdateExistingCustomer() throws Exception {
        CustomerClass existingCustomer = new CustomerClass();
        existingCustomer.setaccountId(1);

        mockMvc.perform(post("/registerCustomer/add")
                .flashAttr("customer", existingCustomer))
               .andExpect(redirectedUrl("/index"));

        verify(customerServiceImpl).updateCustomer(any(CustomerClass.class));
    }
}

 
