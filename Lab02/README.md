# 📝 Tasks

1. **Create a `Customer` class** with at least 5 attributes of your choice and a corresponding `CustomerDTO` with at least 3 attributes.
2. **Implement necessary layers**:
    - `Repository` for data access.
    - `Service` for business logic.
    - `Controller` for handling REST API requests with:
        - `GET /customers` → Retrieve all customers.
        - `GET /customers/{id}` → Retrieve a customer by ID.
        - `GWT /customers/name/{name}` → Retrieve a customer by name.
        - `POST /customers` → Create a new customer.
        - `DELETE /customers/{id}` → Delete a customer by ID.
3. **Create a `CustomerCommand` class** that includes:
    - `@Email` annotation for email validation.
    - `@Digits` annotation for numeric validation.
    - `@Future` annotation to enforce future dates.
