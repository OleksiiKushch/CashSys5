# Cash System (Pet Project) (01/10/2021 - 01/01/2022)

## Project

### Description
The project involves the development of components for a point-of-sale information system (payment and cash service). The primary business process consists of creating orders with subsequent receipt formation. Additionally, this platform provides extra features, such as a multi-role security system, product inventory handling, order management with cancellation options, generation of various reports, receipt property management, and so on.

### Key technologies
- Core: Java;
- Testing: Junit, Mockito, PowerMockito;
- Backend: Java EE, Java Servlet API (Jakarta Servlet), Apache Tomcat;
- Frontend: JavaScript, jQuery, Vue.js, JSP, JSTL, CSS, Bootstrap;
- Persistence: JDBC, MySQL;

### Additional information
Link to ERR-Diagram: [here](src/main/resources/db/ERR-Diagram.PNG)

## Product (system)

### Description

#### CashSys5

This information system provides simple and convenient accounting of funds during transactional operations. It includes a specific domain called "Supermarket," where the main units of data are products. In the future, a combination of products forms an order or, in other words, a receipt.

This information system encompasses three roles: cashier, senior cashier, and goods manager.

The cashier is responsible for the main business process: creating a receipt (its formation and subsequent closure). The process begins with the cashier creating an order to which products will be added later. The cashier then adds products to the order, specifying the desired quantities. If necessary, the cashier can modify the quantity of a particular product in the order or remove it entirely. To obtain a receipt, the cashier performs a closing (confirmation) operation on the order, which, upon successful completion, becomes valid.

The senior cashier can view the catalog of receipts and has the ability to cancel receipts by canceling all the products in a specific receipt or by canceling a specific product from a receipt if needed. The senior cashier can also access the catalog of employees (system users). Additionally, the senior cashier can generate certain reports, such as top-performing cashiers or popular products. The senior cashier is also responsible for setting, editing, or resetting global properties of a receipt.

The goods manager can create new products in the system, edit them, and perform actions such as specifying the quantity available in stock or deleting products altogether.


## Demo
Video on YouTube: https://www.youtube.com/watch?v=s2wnTKnNMOs
