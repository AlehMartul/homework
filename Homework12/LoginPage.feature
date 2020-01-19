Feature: Test LoginPage
 Scenario Outline: Test positive
   Given I am on main page
   When I login to e-mail
   Then I see logout link
   When I add letter to spam
   Then I see spam alert
   When I return letter from spam
   Then I see return from spam alert
   When I mark <numbersOfSelectLetters> letters
   Then I see selected <numbersOfSelectLetters> letters are marked
   When I deflag all letters
   Then I see all letters are deflaged
   When I send letter for group
   Then I see alert that letters are sent
Examples:
      | numbersOfSelectLetters |
      | 3                      |
