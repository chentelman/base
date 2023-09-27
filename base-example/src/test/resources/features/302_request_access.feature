@Access
Feature: Test json request access operations
	I want to be able to retrieve persisted base entities

	@All
	@Positive
	Scenario Outline: Find all entities
		Given an initialized "<storage>" storage
		When querying for all "<entity>" json request entries
		Then the number of latest json request entries are 3
		And the latest json request entry at index 0 matches
			|    id |   <id> |                1 |
			|  code | string |                1 |
		And the latest json request entry at index 0 <should match>
			|    id |   <id> |                1 |
			|  code | string |                1 |
			|  name | string | The first option |

		Examples:
			| entity                                                | storage                             |     id |   should match |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 | string | does not match |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 | string |        matches |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		Given an initialized "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" json request entries
		Then the number of latest json request entries are 2
		And the latest json request entry at index 0 matches
			|    id |   <id> |                1 |
			|  code | string |                1 |
		And the latest json request entry at index 0 <should match>
			|    id |   <id> |                1 |
			|  code | string |                1 |
			|  name | string | The first option |

		Examples:
			| entity                                                | storage                             |     id |   should match |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 | string | does not match |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 | string |        matches |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |

	@Page
	@Positive
	Scenario Outline: Find second page of entities
		Given an initialized "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" json request entries
		Then the number of latest json request entries are 1
		And the latest json request entry at index 0 matches
			|    id |   <id> |                3 |
			|  code | string |                3 |
		And the latest json request entry at index 0 <should match>
			|    id |   <id> |                3 |
			|  code | string |                3 |
			|  name | string | The third option |

		Examples:
			| entity                                                | storage                             |     id |   should match |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 | string | does not match |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 | string |        matches |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |

	@Page
	@Positive
	Scenario Outline: Find huge page of entities
		Given an initialized "<storage>" storage
		When querying for page 0 of size 100 for "<entity>" json request entries
		Then the number of latest json request entries are 3
		And the latest json request entry at index 2 matches
			|    id |   <id> |                3 |
			|  code | string |                3 |
		And the latest json request entry at index 2 <should match>
			|    id |   <id> |                3 |
			|  code | string |                3 |
			|  name | string | The third option |

		Examples:
			| entity                                                | storage                             |     id |   should match |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 | string | does not match |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 | string |        matches |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |   long | does not match |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |        matches |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |   long | does not match |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |        matches |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |   long | does not match |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |        matches |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long | does not match |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |        matches |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long | does not match |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |        matches |

	@Page
	@Positive
	Scenario Outline: Find out of bound page of entities
		Given an initialized "<storage>" storage
		When querying for page 99 of size 10 for "<entity>" json request entries
		Then the number of latest json request entries are 0

		Examples:
			| entity                                                | storage                             |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@One
	@Positive
	Scenario Outline: Find entity by id
		Given an initialized "<storage>" storage
		When querying for the "<entity>" json request entry with id "2"
		Then the latest json request entry matches
			|    id |   <id> |                 2 |
			|  code | string |                 2 |
			|  name | string | The second option |

		Examples:
			| entity                                                | storage                             |     id |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 | string |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 | string |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |   long |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |   long |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |   long |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |   long |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |   long |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |   long |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |   long |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |   long |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |   long |

	@One
	@Positive
	Scenario Outline: Find partitioned entity by id
		Given an initialized "<storage>" storage
		When querying for the "<entity>" json request entry with id "2"
		Then the latest json request entry at index 0 matches
			|    id |   <id> | 2 |
			|  code | string | 2 |

		Examples:
			| entity                                                | storage                             |     id |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |   long |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |   long |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |   long |

	@One
	@Negative
	Scenario Outline: Find non existing entity by id
		Given an initialized "<storage>" storage
		Then fail to query the "<entity>" json request entry with id "5"

		Examples:
			| entity                                                | storage                             |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |

	@One
	@Negative
	Scenario Outline: Find non existing partition entity by id
		Given an initialized "<storage>" storage
		When query the "<entity>" json request entry with id "5"
		Then the number of latest json request entries are 0

		Examples:
			| entity                                                | storage                             |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |



