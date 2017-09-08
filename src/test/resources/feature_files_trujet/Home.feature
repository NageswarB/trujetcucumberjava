Feature: Check home page of the Lyris LM administration UI
		 In order to test home screens
		 As a user I should have Lyris LM application in running state
Background:
    Given user navigates to URL : "${application.url}" application
    Given that I logged in with "${username}" and "${password}"
