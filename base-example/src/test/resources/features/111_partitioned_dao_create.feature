@Create
Feature: Test partitioned dao create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the duplicate id creation
		Given an initialized "<entity>" storage
		When a data entry
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And a data entry
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |
		Then query all data entries with id "4"
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |

		Examples:
			| entity                        |
			| BaseTestMemPartitionedTestDao |



