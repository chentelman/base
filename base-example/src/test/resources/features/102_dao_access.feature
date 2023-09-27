@Access
Feature: Test dao access operations
	I want to be able to retrieve persisted base entities

	@All
	@Positive
	Scenario Outline: Find all entities
		Given an initialized "<entity>" storage
		When querying for all data entries
		Then the number of latest data entries are 3
		And the latest data entry at index 0 matches
			| field |            value |
			|    id |                1 |
			|  code |                1 |
			|  name | The first option |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		Given an initialized "<entity>" storage
		When querying for page 0 of size 2 for data entries
		Then the number of latest data entries are 2
		And the latest data entry at index 0 matches
			| field |            value |
			|    id |                1 |
			|  code |                1 |
			|  name | The first option |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@Page
	@Positive
	Scenario Outline: Find second page of entities
		Given an initialized "<entity>" storage
		When querying for page 1 of size 2 for data entries
		Then the number of latest data entries are 1
		And the latest data entry at index 0 matches
			| field |            value |
			|    id |                3 |
			|  code |                3 |
			|  name | The third option |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@Page
	@Positive
	Scenario Outline: Find huge page of entities
		Given an initialized "<entity>" storage
		When querying for page 0 of size 100 for data entries
		Then the number of latest data entries are 3
		And the latest data entry at index 2 matches
			| field |            value |
			|    id |                3 |
			|  code |                3 |
			|  name | The third option |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@Page
	@Positive
	Scenario Outline: Find out of bound page of entities
		Given an initialized "<entity>" storage
		When querying for page 99 of size 10 for data entries
		Then the number of latest data entries are 0

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@Page
	@Positive
	Scenario Outline: Get the number of entities
		Given an initialized "<entity>" storage
		Then querying for page 99 of size 10 for data entries

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@One
	@Positive
	Scenario Outline: Find entity by id
		Given an initialized "<entity>" storage
		When querying for the data entry with id "2"
		Then the latest data entry matches
			| field |             value |
			|    id |                 2 |
			|  code |                 2 |
			|  name | The second option |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |

	@One
	@Negative
	Scenario Outline: Find non existing entity by id
		Given an initialized "<entity>" storage
		Then fail to query the data entry with id "5"

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |



