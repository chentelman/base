@Access
Feature: Test partitioned service access operations
	I want to be able to persist base entities

	@All
	@Positive
	Scenario Outline: Find all entities by id
		Given an initialized partitioned "<storage>" storage
		When querying for all "<entity>" service entries with id "<id>"
		Then the number of latest service entries are 2
		And the latest service entries contain
			| field | value |
			|    id |  <id> |
			|  code |    en |
			|  name |  <en> |
		And the latest service entries contain
			| field | value |
			|    id |  <id> |
			|  code |    ja |
			|  name |  <ja> |

		Examples:
			| entity                                         | storage                             | id |   en |   ja |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  5 | five |   go |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  5 | five |   go |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		Given an initialized partitioned "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" service entries with id "<id>"
		Then the number of latest service entries are 2
		And the latest service entries contain
			| field | value |
			|    id |  <id> |
			|  code |    en |
			|  name |  <en> |
		And the latest service entries contain
			| field | value |
			|    id |  <id> |
			|  code |    ja |
			|  name |  <ja> |

		Examples:
			| entity                                         | storage                             | id |   en |   ja |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  5 | five |   go |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  5 | five |   go |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |

	@Page
	@Positive
	Scenario Outline: Find second page of entities
		Given an initialized partitioned "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" service entries with id "4"
		Then the number of latest service entries are 0

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@All
	@Positive
	Scenario Outline: Find all entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for all "<entity>" service entries in partition "<partition>"
		Then the number of latest service entries are 6
		And the latest service entries contain
			| field |       value |
			|    id |           4 |
			|  code | <partition> |
			|  name |         <4> |
		And the latest service entries contain
			| field |       value |
			|    id |           5 |
			|  code | <partition> |
			|  name |         <5> |
		And the latest service entries contain
			| field |       value |
			|    id |           6 |
			|  code | <partition> |
			|  name |         <6> |

		Examples:
			| entity                                         | storage                             | partition |    4 |    5 |    6 |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku |

	@Page
	@Positive
	Scenario Outline: Find first page of entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" service entries in partition "<partition>"
		Then the number of latest service entries are 2
		And the latest service entries contain
			| field |       value |
			|    id |           1 |
			|  code | <partition> |
			|  name |         <1> |
		And the latest service entries contain
			| field |       value |
			|    id |           2 |
			|  code | <partition> |
			|  name |         <2> |
		And the latest service entries do not contain
			| field |       value |
			|    id |           3 |
			|  code | <partition> |
			|  name |         <3> |

		Examples:
			| entity                                         | storage                             | partition |    1 |    2 |    3 |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |

	@Page
	@Positive
	Scenario Outline: Find second page of entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" service entries in partition "<partition>"
		Then the number of latest service entries are 2
		And the latest service entries do not contain
			| field |       value |
			|    id |           1 |
			|  code | <partition> |
			|  name |         <1> |
		And the latest service entries do not contain
			| field |       value |
			|    id |           2 |
			|  code | <partition> |
			|  name |         <2> |
		And the latest service entries contain
			| field |       value |
			|    id |           3 |
			|  code | <partition> |
			|  name |         <3> |

		Examples:
			| entity                                         | storage                             | partition |    1 |    2 |    3 |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |

	@One
	@Positive
	Scenario Outline: Find entity by id and partition
		Given an initialized partitioned "<storage>" storage
		When querying for the "<entity>" service entry with id "<id>" in partition "<code>"
		Then the latest service entry matches
			| field |  value |
			|    id |   <id> |
			|  code | <code> |
			|  name | <name> |

		Examples:
			| entity                                         | storage                             | id | code | name |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  4 |   ja |  yon |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  4 |   ja |  yon |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  5 |   ja |   go |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  5 |   ja |   go |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |  6 |   ja | roku |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |  6 |   ja | roku |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  4 |   en | four |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  4 |   en | four |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  5 |   en | five |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  5 |   en | five |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  6 |   en |  six |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  6 |   en |  six |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |

	@One
	@Negative
	Scenario Outline: Find non existing entity by id and partition
		Given an initialized partitioned "<storage>" storage
		Then fail to query the "<entity>" service entry with id "10" in partition "de"

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedAccessConvertTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultTestService | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |



