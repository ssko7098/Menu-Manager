# Lab29-Huaicheng-Group1-A1

## Introduction
`ItsAllEats` is a robust menu manager software application in Java. ItsAllEats purpose is to revolutionise
the ability of Restaurant Owners and Franchisers to manage and view their inventory, orders, and customers.

Our software provides a centralised location where admin users such as owners and operators are able to
efficiently view the resturants current offerings, view order history, change the current menu and more.


## Contributors

* Ethan Acevski `520406429`
* Sebstian Skontos `520466010`
* Alex Photios `520454833`
* Dmitry Khachumov `520442472`
* Ryan Tanevski `520466098`

## Running the Program
- To run the program, use the command: "./gradlew clean run"
- To see our test results, use the command "./gradlew test jacocoTestReport" and locate the jacoco report in build/reports/jacoco/test/html/index.html
### Expectations
* When Running
  - The user is taken to a Login screen. If the user is an admin, they can access the admin features. Otherwise the User presses "Not an Admin?" without logging in to access the ordering system.
* When Testing
  - A robot will take control of the screen for approximately 1.5min, displaying and testing various functionalities of the program.
## Login Details

When logging into the Menu Manager you have the option to login in as a guest or as an admin

### Login Details for Admin:

* Admin Username: "admin"
* Admin Password: "1234"

### Logging as Guest:

* To login as guest simply click the `not an admin?` button located below the `SIGN IN` button

## Instructions on Collaborating/Contributing on GitHub Repository

1. Perform Git clone - git clone : https://github.sydney.edu.au/SOFT2412-COMP9412-2023S2/Lab29-Huaicheng-Group1-A1.git
2. Create branch on local repo: git switch -c "Branch Name"
3. Set Upstream branch to push changes to: git branch --set-upstream-to origin/"Branch Name"
4. Add changes to the local git repo stage: git add -a "file name"
5. Commit changes to local repo: git commit -m "enter message"
6. Push changes to GitHub Branch: git push
7. Review and merge changes with Main branch: Go to respective Branch --> Complete Pull Request



