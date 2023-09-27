@Create
Feature: Test service create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the entity creation
		Given an initialized "<storage>" storage
		When a "<entity>" service entry
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |
		Then the latest service entry matches
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |

		Examples:
			| entity                                         | storage                             |
			| BaseTestJpaConvertTestService                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultTestService                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be created
		Given an initialized "<storage>" storage
		Then fail to create a "<entity>" service entry
			| field | value |
			|    id |     4 |
			|  code | value |
			|  name |  four |

		Examples:
			| entity                                         | storage                             |
			| BaseTestEnumTestService                        | BaseTestEnumTestDao                 |
			| BaseTestMemAccessConvertTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |



