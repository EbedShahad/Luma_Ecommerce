# Luma_Ecommerce - Software Testing Sandbox

This repository documents the testing efforts and related materials for the Luma ecommerce website, accessible at [magento.softwaretestingboard.com](magento.softwaretestingboard.com).

## Introduction

The Luma ecommerce website, built on Magento, provides a robust and realistic platform for software testing. It encompasses a wide array of functionalities found in typical online shopping experiences, making it an ideal sandbox for various testing types, including functional, usability, performance, and security testing.

## Purpose

This repository serves as a central hub for the Rames Software Testing team's work on the Luma ecommerce platform. It includes:

* Test plans and strategies
* Test cases and scripts
* Bug reports and issue tracking
* Documentation of testing results
* Automation scripts (if applicable)

## User Stories

### User Story 2: Cart Management

**Objective:** Allow users to add, modify, and remove items in their shopping cart.

**Acceptance Criteria:**

1.  Users can add items to the cart from product pages, category pages, or wishlists.
2.  Users can update item quantities (e.g., increase/decrease).
3.  Cart displays accurate item details (price, quantity, total).
4.  Items persist in the cart after logging out or closing the browser (if cookies are enabled).
5.  Users can remove single or all items from the cart.

**Example Test Scenarios:**

1.  **Add to Cart:** Add items from multiple sources (product page, wishlist).
2.  **Update Quantity:** Increase/decrease quantity and verify total price.
3.  **Remove Items:** Remove single items or clear the entire cart.
4.  **Cart Persistence:** Verify cart contents after logout/login or browser restart.
5.  **Stock Validation:** Test adding out-of-stock items or exceeding available quantity.

### User Story 4: Login Functionality

**Objective:** Ensure registered users can securely log in to access personalized features.

**Acceptance Criteria:**

1.  Users can log in with valid credentials.
2.  Users cannot log in with invalid credentials (empty fields, incorrect email/password).
3.  Password reset functionality works for forgotten passwords.
4.  Users are redirected to the dashboard or a post-login page.
5.  Session terminates correctly after logout.

**Example Test Scenarios:**

1.  **Valid Login:** Test login with correct email/password.
2.  **Invalid Credentials:** Test empty username/password, incorrect email, or wrong password.
3.  **Password Recovery:** Test "Forgot Password" flow (email submission, reset link).
4.  **Logout:** Test session termination after logout.

### User Story 5: Wishlist Management

**Objective:** Allow users to save items to a wishlist for future access.

**Acceptance Criteria:**

1.  Users can add/remove items to/from the wishlist from product pages or the cart.
2.  Wishlist displays accurate item details (name, price, image).
3.  Items persist in the wishlist after logging out and back in.
4.  Users can share the wishlist via email or link (if supported).

**Example Test Scenarios:**

1.  **Add to Wishlist:** Add items from product pages, category pages, and cart.
2.  **View Wishlist:** Verify item count, details, and persistence after relogin.
3.  **Remove Items:** Delete single/multiple items from the wishlist.
4.  **Share Wishlist:** Test email/link sharing functionality (if applicable).

## Getting Started

1.  Clone this repository to your local machine: `git clone [repository URL]`
2.  Access the Luma ecommerce website at [magento.softwaretestingboard.com](magento.softwaretestingboard.com).
3.  Refer to the specific test plans and test cases within this repository for detailed testing instructions.
4.  Report any bugs or issues found using the repository's issue tracking system.

## Contributing

Contributions to this repository are welcome. Please follow the standard Git workflow:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Commit your changes.
4.  Push your changes to your fork.
5.  Submit a pull request.

## Contact

For any questions or inquiries, please contact the Rames Software Testing team.
