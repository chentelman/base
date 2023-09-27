@Delete
Feature: Test service delete operation
	I want to be able to delete persisted base entities

	@Positive
	Scenario Outline: Verify the entity delete on entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And a "<entity>" service entry
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entry matches
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		Then delete the latest service entry

		Examples:
			| entity                                         | storage                             |
			| BaseTestJpaConvertTestService                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultTestService                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Delete non existing entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And query the "<entity>" service entry with id "1"
		When delete the service entry with id "1"
		Then fail to query the service entry with id "1"

		Examples:
			| entity                                         | storage                             |
			| BaseTestJpaConvertTestService                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultTestService                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be deleted
		Given an initialized "<storage>" storage
		Then fail to delete the "<entity>" service entry with id "1"

		Examples:
			| entity                                         | storage                             |
			| BaseTestEnumTestService                        | BaseTestEnumTestDao                 |
			| BaseTestMemAccessConvertTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |



