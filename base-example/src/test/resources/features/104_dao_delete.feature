@Delete
Feature: Test dao delete operation
	I want to be able to delete persisted base entities

	@Positive
	Scenario Outline: Verify the entity delete on entities implemeting the delete interface
		Given an initialized "<entity>" storage
		And a data entry
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entry matches
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		Then delete the latest data entry

		Examples:
			| entity                              |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Delete non existing entities implemeting the delete interface
		Given an initialized "<entity>" storage
		And query the data entry with id "1"
		When delete the data entry with id "1"
		Then fail to query the data entry with id "1"

		Examples:
			| entity                              |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be deleted
		Given an initialized "<entity>" storage
		Then fail to delete the data entry with id "1"

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |



