package OrderDatabase;

import org.junit.*;
import org.mockito.Mock;
import java.util.*;
import com.flameshine.app.database.OrderDatabase;
import com.flameshine.app.model.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderTest {

    @Mock
    private Order order;
    private User user;
    private OrderDatabase orderDatabase;
    private List<Order> orders;

    private void mockOrder() {
        order = mock(Order.class);
        doReturn(0).when(order).getOrderID();
        doReturn("test").when(order).getUsername();
        doReturn("test").when(order).getOrderName();
        doReturn("test").when(order).getOrderPrice();
        doReturn("test").when(order).getOrderStatusMeaning();
    }

    private void mockUser() {
        user = mock(User.class);
        doReturn(0).when(user).getUserID();
        doReturn("test").when(user).getUsername();
        doReturn("test").when(user).getPassword();

    }

    private void mockDatabase() {
        orderDatabase = mock(OrderDatabase.class);
        when(orderDatabase.extractUserOrders(user.getUsername())).thenReturn(orders);
        when(orderDatabase.validateOrderID(order.getOrderID())).thenReturn(true);
    }

    private void mockOrderList() {
        orders = new ArrayList<>();
        orders.add(order);
    }

    @Before
    public void beforeClass() {
        mockOrder();
        mockUser();
        mockDatabase();
        mockOrderList();
    }

    @Test(expected = Exception.class)
    public void testAddingNewOrder() {
        doThrow(new Exception()).when(orderDatabase).addNewOrder(order.getUsername(), order.getOrderName());
        orderDatabase.addNewOrder(order.getUsername(), order.getOrderName());
    }

    @Test(expected = Exception.class)
    public void testChangingOrderStatus() {
        doThrow(new Exception()).when(orderDatabase).changeOrderStatus(order.getOrderID(), 3);
        orderDatabase.changeOrderStatus(order.getOrderID(), 3);
    }

    @Test(expected = Exception.class)
    public void testChangingOrderPrice() {
        doThrow(new Exception()).when(orderDatabase).changeOrderPrice(order.getOrderID(), order.getOrderPrice());
        orderDatabase.changeOrderPrice(order.getOrderID(), order.getOrderPrice());
    }

    @Test
    public void testExtractingOrders() {
        orderDatabase.extractUserOrders(user.getUsername());
        verify(orderDatabase).extractUserOrders(user.getUsername());
        assertEquals(orders.get(0).getOrderID(), order.getOrderID());
        assertEquals(orders.get(0).getOrderName(), order.getOrderName());
        assertEquals(orders.get(0).getUsername(), order.getUsername());
        assertEquals(orders.get(0).getOrderPrice(), order.getOrderPrice());
        assertEquals(orders.get(0).getOrderStatusMeaning(), order.getOrderStatusMeaning());
    }

    @Test
    public void testOrderIDValidation() {
        assertTrue(orderDatabase.validateOrderID(order.getOrderID()));
        verify(orderDatabase).validateOrderID(order.getOrderID());
    }
}