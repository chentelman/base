@Create
Feature: Test json request create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the entity creation
		Given an initialized "<storage>" storage
		When a "<entity>" json request entry
			|    id |   long |     4 |
			|  code | string | value |
			|  name | string |  four |
		Then the latest json request entry matches
			|    id |   long |     4 |
			|  code | string | value |
			|  name | string |  four |

		Examples:
			| entity                                                | storage                             |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be created
		Given an initialized "<storage>" storage
		Then fail to create a "<entity>" json request entry
			|    id |   long |     4 |
			|  code | string | value |
			|  name | string |  four |

		Examples:
			| entity                                                | storage                             |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |



