Feature: Curso de automation

Scenario Outline: Search a book

Given I enter my search <search>
And I click the search button
And I sort the list of results by <order>
When I click the first product
Then The corresponding desctiption screen should be displayed


Examples:

|search 					| order 			 |
| Sailor moon tomo 7   		| price_low_high	 |
| Carlos moon tomo 4   		| price_high_low	 |
| Sailor moon tomo 5   		| price_low_high	 |
| Sailer moon tomo 6   		| price_high_low	 |