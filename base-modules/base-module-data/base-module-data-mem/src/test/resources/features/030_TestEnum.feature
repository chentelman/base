Feature: Enum Data CRUD operations
	I want to be able to handle enum's as in memory data structures

	Scenario: Test find all operations
		When all "TestEnum" data entries
		Then the count of latest "TestEnum" data entries is 4

	Scenario Outline: Test find page operations
		When page <page> of size <size> for "TestEnum" data entries
		Then the count of latest "TestEnum" data entries is <count>

		Examples:
			| page | size | count |
			|    0 |    2 |     2 |
			|    0 |   10 |     4 |
			|    0 |    3 |     3 |
			|    1 |    2 |     2 |
			|    1 |   10 |     0 |
			|    1 |    3 |     1 |

	Scenario Outline: Test find one operation
		When the "TestEnum" data entry with id "<id>"
		Then the latest data entry matches
			| field |    value |
			|  code |     <id> |
			|  name | name<id> |

		Examples:
			| id |
			|  1 |
			|  2 |
			|  3 |
			|  4 |



