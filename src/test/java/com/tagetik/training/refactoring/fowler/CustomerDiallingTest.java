package com.tagetik.training.refactoring.fowler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDiallingTest {

    private static final String CUSTOMER_NAME = "Sbrefildo Sbrefildi";
    private static final String HOME_PREFIX = "050";
    private static final String HOME_NUMBER = "532532";
    private static final String OFFICE_PREFIX = "0583";
    private static final String OFFICE_NUMBER = "987654";

    @Mock
    private Dialler dialler;

    @Test
    public void callingCustomerAtHomeUsesPrefixAndNumber() throws Exception {
        Customer customer = createCustomer();
        customer.setHomePrefix(HOME_PREFIX);
        customer.setHomeNumber(HOME_NUMBER);

        customer.dialHome(dialler);

        ArgumentCaptor<String> dialledNumberCaptor = ArgumentCaptor.forClass(String.class);
        verify(dialler).dial(dialledNumberCaptor.capture());

        assertThat(dialledNumberCaptor.getValue(), containsString(HOME_PREFIX));
        assertThat(dialledNumberCaptor.getValue(), containsString(HOME_NUMBER));
    }

    @Test
    public void callingCustomerAtOfficeUsesPrefixAndNumber() throws Exception {
        Customer customer = createCustomer();
        customer.setOfficePrefix(OFFICE_PREFIX);
        customer.setOfficeNumber(OFFICE_NUMBER);

        customer.dialOffice(dialler);

        ArgumentCaptor<String> dialledNumberCaptor = ArgumentCaptor.forClass(String.class);
        verify(dialler).dial(dialledNumberCaptor.capture());

        assertThat(dialledNumberCaptor.getValue(), containsString(OFFICE_PREFIX));
        assertThat(dialledNumberCaptor.getValue(), containsString(OFFICE_NUMBER));
    }

    private Customer createCustomer() {
        return new Customer(CUSTOMER_NAME);
    }
}
