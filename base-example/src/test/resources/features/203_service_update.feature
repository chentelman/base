@Update
Feature: Test service update operation
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the entity update on entities implemeting the update interface
		Given an initialized "<storage>" storage
		When update the "<entity>" service entry with id "2"
			| field |        value |
			|    id |            2 |
			|  code |            2 |
			|  name | The option 2 |
		Then query the service entry with id "2"
		And the latest service entry matches
			| field |        value |
			|    id |            2 |
			|  code |            2 |
			|  name | The option 2 |

		Examples:
			| entity                                         | storage                             |
			| BaseTestJpaConvertTestService                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultTestService                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be updated
		Given an initialized "<storage>" storage
		Then fail to update the "<entity>" service entry with id "2"
			| field |        value |
			|    id |            2 |
			|  code |            2 |
			|  name | The option 2 |

		Examples:
			| entity                                         | storage                             |
			| BaseTestEnumTestService                        | BaseTestEnumTestDao                 |
			| BaseTestMemAccessConvertTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |

	@Negative
	Scenario Outline: Update non existing entities implemeting the update interface
		Given an initialized "<storage>" storage
		Then fail to update the "<entity>" service entry with id "5"
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |

		Examples:
			| entity                                         | storage                             |
			| BaseTestEnumTestService                        | BaseTestEnumTestDao                 |
			| BaseTestJpaConvertTestService                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultTestService                  | BaseTestJpaTestDao                  |
			| BaseTestMemAccessConvertTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultTestService            | BaseTestMemAccessTestDao            |
			| BaseTestMemConvertTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultTestService                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |



