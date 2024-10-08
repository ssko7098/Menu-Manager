# Menu Manager Application

This project is a Menu Manager application developed in Java. It provides both general users and administrators with distinct features for managing and interacting with menu items. This application is developed using agile tools and processes.

## Login Details for Admin:
* Admin Username: `admin`
* Admin Password: `1234`

## Table of Contents
- [Application Overview](#application-overview)
- [Features](#features)
  - [Menu Ordering](#menu-ordering)
  - [Order History](#order-history)
  - [Admin Dashboard](#admin-dashboard)
- [Setup and Installation](#setup-and-installation)
- [Contributing](#contributing)
- [License](#license)

## Application Overview
The Menu Manager application allows general users to browse and order menu items, view their order history, and place new orders. Administrators have additional privileges, including adding, updating, and removing menu items. The application is designed with simplicity and ease of use in mind.

## Features

### Menu Ordering
General users can:
- Browse through a predefined list of menu items, categorized for easy navigation.
- View item details, such as names, descriptions, and prices.
- Add items to an order cart, adjust item quantities, and remove items as needed.
- Complete the checkout process, selecting either delivery or pickup.
- Note: No login is required for users; all users share the same limited (non-admin) account.

### Order History
- Users can view their order history, which includes:
  - Order date.
  - List of ordered items.
  - Total amount.
- Users can search their order history by order number.
- Order data is securely stored.

### Admin Dashboard
Administrators have full access to manage the menu and orders. They can:
- Perform all user operations (order browsing, placing orders, viewing order history).
- Add new menu items with details such as name, description, and price.
- Update existing menu items (e.g., correcting prices or descriptions).
- Remove outdated or irrelevant menu items.
- View the total number of orders processed.

## Setup and Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/ssko7098/Menu-Manager.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Menu-Manager
   ```
3. Build the application using Gradle:
   ```bash
   ./gradlew clean build
   ```
4. Run the application:
   ```bash
   ./gradlew clean run
   ```

## Contributing
We welcome contributions to improve this application! Feel free to fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.



