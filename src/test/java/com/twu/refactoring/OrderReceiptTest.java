package com.twu.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OrderReceiptTest {
    @Test
    public void shouldPrintCustomerInformationOnOrder() {
        Order order = new Order("Mr X", "Chicago, 60601", new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();
        Assertions.assertTrue(output.contains("Mr X"));
        Assertions.assertTrue(output.contains("Chicago, 60601"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, lineItems));

        String output = receipt.printReceipt();

        Assertions.assertTrue(output.contains("milk\t10.0\t2\t20.0\n"));
        Assertions.assertTrue(output.contains("biscuits\t5.0\t5\t25.0\n"));
        Assertions.assertTrue(output.contains("chocolate\t20.0\t1\t20.0\n"));
        Assertions.assertTrue(output.contains("Sales Tax\t6.5"));
        Assertions.assertTrue(output.contains("Total Amount\t71.5"));
    }

}
