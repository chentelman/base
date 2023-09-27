@Access
Feature: Test service access operations
	I want to be able to retrieve persisted base entities

	@All
	@Positive
	Scenario Outline: Find all entities
		Given an initialized "<storage>" storage
		When querying for all "<entity>" service entries
		Then the number of latest service entries are 3
		And the latest service entry at index 0 matches
			| field |            value |
			|    id |                1 |
			|  code |                1 |
			|  name | The first option |

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

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		Given an initialized "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" service entries
		Then the number of latest service entries are 2
		And the latest service entry at index 0 matches
			| field |            value |
			|    id |                1 |
			|  code |                1 |
			|  name | The first option |

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

	@Page
	@Positive
	Scenario Outline: Find second page of entities
		Given an initialized "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" service entries
		Then the number of latest service entries are 1
		And the latest service entry at index 0 matches
			| field |            value |
			|    id |                3 |
			|  code |                3 |
			|  name | The third option |

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

	@Page
	@Positive
	Scenario Outline: Find huge page of entities
		Given an initialized "<storage>" storage
		When querying for page 0 of size 100 for "<entity>" service entries
		Then the number of latest service entries are 3
		And the latest service entry at index 2 matches
			| field |            value |
			|    id |                3 |
			|  code |                3 |
			|  name | The third option |

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

	@Page
	@Positive
	Scenario Outline: Find out of bound page of entities
		Given an initialized "<storage>" storage
		When querying for page 99 of size 10 for "<entity>" service entries
		Then the number of latest service entries are 0

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

	@Page
	@Positive
	Scenario Outline: Get the number of entities
		Given an initialized "<storage>" storage
		Then querying for page 99 of size 10 for "<entity>" service entries

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

	@One
	@Positive
	Scenario Outline: Find entity by id
		Given an initialized "<storage>" storage
		When querying for the "<entity>" service entry with id "2"
		Then the latest service entry matches
			| field |             value |
			|    id |                 2 |
			|  code |                 2 |
			|  name | The second option |

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

	@One
	@Negative
	Scenario Outline: Find non existing entity by id
		Given an initialized "<storage>" storage
		Then fail to query the "<entity>" service entry with id "5"

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



