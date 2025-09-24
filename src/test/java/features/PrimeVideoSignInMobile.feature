@PVMobileNativeRegression
Feature: User should be able to launch prime Video Application

  Background:
    Given User launches the prime Video Application

  Scenario: Verify user should see main activity
    Then User should be displayed the apps home page
