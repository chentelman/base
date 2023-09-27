Feature: In memory Data Entity CRUD operations
	I want to be able to persist in memory entities

	Background:
		Given all "TestEntity" data entries
		And the count of latest data entries is 0
		And 0 data entries
		And a data entry
			| field | value |
			|    id |     1 |
			|  data | smile |
		And the latest data entry matches
			| field | value |
			|    id |     1 |
			|  data | smile |

	Scenario: Test basic operations on latest entity
		When updating the latest data entry
			| field | value |
			|  data |   cry |
		And the latest data entry matches
			| field | value |
			|    id |     1 |
			|  data |   cry |
		Then deleting the latest data entry
		And the latest data entry cannot be found

	Scenario: Test basic operations by id
		When the data entry with id "1"
		And updating the data entry
			| field | value |
			|    id |     1 |
			|  data |   cry |
		And the latest data entry matches
			| field | value |
			|    id |     1 |
			|  data |   cry |
		And deleting the data entry with id "1"
		Then the data entry with id "1" cannot be found

	Scenario: Test count operation
		When creating a data entry
			| field | value |
			|    id |     2 |
			|  data | data2 |
		And the number of data entries are 2
		And creating a data entry
			| field | value |
			|    id |     3 |
			|  data | data3 |
		And the number of data entries are 3
		Then delete the data entry with id "3"
		And the number of data entries are 2
		Then delete the data entry with id "2"
		And the number of data entries are 1
		Then delete the data entry with id "1"
		And the number of data entries are 0

	Scenario: Test find all operation
		And a data entry
			| field | value |
			|    id |     2 |
			|  data | data2 |
		And query all data entries
		And the count of latest data entries is 2
		And a data entry
			| field | value |
			|    id |     3 |
			|  data | data3 |
		When querying for all data entries
		And the count of latest data entries is 3
		Then delete the data entry with id "3"
		And the number of data entries are 2
		And the count of latest data entries is 3
		And delete the data entry with id "2"
		And the number of data entries are 1
		And the count of latest data entries is 3
		And delete the data entry with id "1"
		And the number of data entries are 0
		And the count of latest data entries is 3



