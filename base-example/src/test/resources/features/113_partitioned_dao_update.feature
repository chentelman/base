@Update
Feature: Test partitioned dao update operations
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the update on duplicate ids updates the correct one
		Given an initialized partitioned "<entity>" storage
		And all data entries with id "4"
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		When update the data entry
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |
		Then all data entries with id "4"
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




