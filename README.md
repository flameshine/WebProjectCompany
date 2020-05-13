# WebProjectITCompany
<h3>Web application that implements customer and IT company interaction. In the personal account, the authorized user can create orders, see the progress of his orders and his notifications.
The manager can confirm the user’s order, indicating the price, or reject, indicating the reason. Confirmed orders are handed over to the worker, who can indicate the progress of the order.
The user tracks the progress of his orders.</h3>
<h2>Overview:</h2>
<ul>
  <li><h2>Structure</h2>
    This application based on the MVC principle:
    <ul>
      <li>Model: connection pool, database and all database interactions.</li>
      <li>View: JSP pages and styles.</li>
      <li>Controller: Servlets.</li>
    </ul>
<li><h2>View</h2>
All interactions take place in a web browser, directly on the website of this web application.
First, the user gets to the login page, where he can log in, or go to the registration page, where he can create an account.
There are many tabs on the site, which are the navigation menu. The menu consists of the following tabs: home, notifications, create order, orders, about, log out.
Each section is a separate page with its appearance and its capabilities.
<li><h2>Tools</h2>
<ul>
<li>Java + HTML + CSS + JS</li>
<li>Servlets API</li>
<li>JSP + JSTL</li>
<li>JDBC API</li>
<li>MySQL</li>
<li>log4j</li>
<li>JUnit + Mockito</li>
<li>Tomcat</li>
</ul>
</li>
<li><h2>Database</h2>
Consists of: a list of users(userID, username, password); from a list of orders(orderID, username, orderName, orderPrice, orderStatusID);
from a list of order statuses(statusID, statusMeaning); from a list of notifications(notificationID, orderID, username, notificationText, orderStatusID).
<li><h2>Additions</h2>
This is my first web project, which will serve as the basis for further growth in this direction.
Also I'd like to mention my dear friend https://github.com/KostiaLeowho who gave me an idea for this assignment and helped to master these technologies.
</li>
</ul>
