Feature: Register
  Scenario: User Register
    Given The user accesses the home page
    When The home page appears
    And The user clicks on the registration option
    And The register page appears
    And The user enters information into the form
      | name | lastname | phone | email       | address | city    | state   | codePostal  | country   | user    | password  | confirmPassword |
      | alex | oyuela   | 123   | ao@mail.com | calle 1 | armenia | quindio | 123         | COLOMBIA  | aoyuela | aoyuela   | aoyuela         |
    And The user clicks on submit
    Then The record is generated in the system

