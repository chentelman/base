@Delete
Feature: Test json request delete operation
	I want to be able to delete persisted base entities

	@Positive
	Scenario Outline: Verify the entity delete on entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And a "<entity>" json request entry
			|    id | string |     4 |
			|  code | string |    en |
			|  name | string |  four |
		When delete the latest json request entry
		Then fail to query the json request entry with id "4"

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

	@Positive
	Scenario Outline: Verify the entity delete on partitioned entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And a "<entity>" json request entry
			|    id | string |     4 |
			|  code | string |    en |
			|  name | string |  four |
		When delete the latest json request entry
		And query the json request entry with id "4"
		Then the number of latest json request entries are 0

		Examples:
			| entity                                                | storage                             |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Delete non existing entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And query the "<entity>" json request entry with id "1"
		When delete the json request entry with id "1"
		Then fail to query the json request entry with id "1"

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

	@Negative
	Scenario Outline: Delete non existing partitioned entities implemeting the delete interface
		Given an initialized "<storage>" storage
		And query the "<entity>" json request entry with id "1"
		When delete the json request entry with id "1"
		Then query the json request entry with id "1"
		And the number of latest json request entries are 0

		Examples:
			| entity                                                | storage                             |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be deleted
		Given an initialized "<storage>" storage
		Then fail to delete the "<entity>" json request entry with id "1"

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



