@Create
Feature: Test dao create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the entity creation
		Given an initialized "<entity>" storage
		When a data entry
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |
		Then the latest data entry matches
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |

		Examples:
			| entity                              |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be created
		Given an initialized "<entity>" storage
		Then fail to create a data entry
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |



