package UserDatabase;

import org.junit.*;
import org.mockito.Mock;
import java.util.*;
import com.flameshine.app.database.UserDatabase;
import com.flameshine.app.model.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserTest {

    @Mock
    private Notification notification;
    private User user;
    private Order order;
    private UserDatabase userDatabase;
    private List<Notification> notifications;

    public void mockNotification() {
        notification = mock(Notification.class);
        doReturn(0).when(notification).getNotificationID();
        doReturn("test").when(notification).getOrderName();
        doReturn("test").when(notification).getUsername();
        doReturn("test").when(notification).getNotificationText();
        doReturn("test").when(notification).getOrderStatusMeaning();
    }

    public void mockUser() {
        user = mock(User.class);
        doReturn(0).when(user).getUserID();
        doReturn("test").when(user).getUsername();
        doReturn("test").when(user).getPassword();
    }

    public void mockOrder() {
        order = mock(Order.class);
        doReturn(0).when(order).getOrderID();
        doReturn("test").when(order).getUsername();
        doReturn("test").when(order).getOrderName();
        doReturn("test").when(order).getOrderPrice();
        doReturn("test").when(order).getOrderStatusMeaning();
    }

    public void mockDatabase() {
        userDatabase = mock(UserDatabase.class);
        when(userDatabase.extractUserNotifications(user.getUsername())).thenReturn(notifications);
        when(userDatabase.validateLoginData(user.getUsername(), user.getPassword())).thenReturn(true);
        when(userDatabase.parseUsernameMatches(user.getUsername())).thenReturn(true);
    }

    public void mockNotificationList() {
        notifications = new ArrayList<>();
        notifications.add(notification);
    }

    @Before
    public void beforeClass() {
        mockNotification();
        mockUser();
        mockOrder();
        mockDatabase();
        mockNotificationList();
    }

    @Test(expected = Exception.class)
    public void testRegisterUser() {
        doThrow(new Exception()).when(userDatabase).registerUser(user.getUsername(), user.getPassword());
        userDatabase.registerUser(user.getUsername(), user.getPassword());
    }

    @Test
    public void testExtractingNotifications() {
        userDatabase.extractUserNotifications(user.getUsername());
        verify(userDatabase).extractUserNotifications(user.getUsername());
        assertEquals(notifications.get(0).getNotificationID(), notification.getNotificationID());
        assertEquals(notifications.get(0).getOrderName(), notification.getOrderName());
        assertEquals(notifications.get(0).getUsername(), notification.getUsername());
        assertEquals(notifications.get(0).getNotificationText(), notification.getNotificationText());
        assertEquals(notifications.get(0).getOrderStatusMeaning(), notification.getOrderStatusMeaning());
    }

    @Test(expected = Exception.class)
    public void testNotifyingUser() {
        doThrow(new Exception()).when(userDatabase).notifyUser(order.getOrderID(), notification.getNotificationText(), 1);
        userDatabase.notifyUser(order.getOrderID(), notification.getNotificationText(), 1);
    }

    @Test
    public void testLoginDataValidation() {
        assertTrue(userDatabase.validateLoginData(user.getUsername(), user.getPassword()));
        verify(userDatabase).validateLoginData(user.getUsername(), user.getPassword());
    }

    @Test
    public void testParsingUsernameMatches() {
        assertTrue(userDatabase.parseUsernameMatches(user.getUsername()));
        verify(userDatabase).parseUsernameMatches(user.getUsername());
    }
}