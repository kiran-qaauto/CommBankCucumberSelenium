Feature: This feature is to verify the home loan repayment calculator

  Scenario Outline: Verify the home loan repayment calculator
    Given I have the application url
    When I click on Repayments calculator
    Then I verify the "Repayments Calculator" page is open
    When I input the borrowed amount as "<amount>"
    And I input the borrowed period as "<period>"
    And I input the repayment type as "<repaymentType>"
    And I input the interest rate as "<interestRate>"
    And I click on calculate button
    Then I verify the total loan repayment amount is "<totalRepaymentAmount>"
    Then I verify the total interest charged amount is "<totalInterestCharged>"
    Examples:
      | amount | period | repaymentType         | interestRate                                | totalRepaymentAmount | totalInterestCharged |
      | 200000 | 20     | Interest only 2 years | 4.34% p.a. Standard Variable Rate Home Loan | $294,909             | $94,909              |
      | 100000 | 30     | Interest only 4 years | 3.69% p.a. Extra Home Loan 10% deposit      | $161,033             | $61,033              |