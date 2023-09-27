@Access
Feature: Test partitioned dao access operations
	I want to be able to persist base entities

	Background:
		Given an initialized partitioned "BaseTestMemPartitionedTestDao" storage

	@All
	@Positive
	Scenario Outline: Find all entities by id
		When querying for all data entries with id "<id>"
		Then the number of latest data entries are 2
		And the latest data entries contain
			| field | value |
			|    id |  <id> |
			|  code |    en |
			|  name |  <en> |
		And the latest data entries contain
			| field | value |
			|    id |  <id> |
			|  code |    ja |
			|  name |  <ja> |

		Examples:
			| id |   en |   ja |
			|  4 | four |  yon |
			|  5 | five |   go |
			|  6 |  six | roku |

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		When querying for page 0 of size 2 for data entries with id "<id>"
		Then the number of latest data entries are 2
		And the latest data entries contain
			| field | value |
			|    id |  <id> |
			|  code |    en |
			|  name |  <en> |
		And the latest data entries contain
			| field | value |
			|    id |  <id> |
			|  code |    ja |
			|  name |  <ja> |

		Examples:
			| id |   en |   ja |
			|  4 | four |  yon |
			|  5 | five |   go |
			|  6 |  six | roku |

	@Page
	@Positive
	Scenario: Find second page of entities
		When querying for page 1 of size 2 for data entries with id "4"
		Then the number of latest data entries are 0

	@All
	@Positive
	Scenario Outline: Find all entities by partition
		When querying for all data entries in partition "<partition>"
		Then the number of latest data entries are 6
		And the latest data entries contain
			| field |       value |
			|    id |           4 |
			|  code | <partition> |
			|  name |         <4> |
		And the latest data entries contain
			| field |       value |
			|    id |           5 |
			|  code | <partition> |
			|  name |         <5> |
		And the latest data entries contain
			| field |       value |
			|    id |           6 |
			|  code | <partition> |
			|  name |         <6> |

		Examples:
			| partition |    4 |    5 |    6 |
			|  en       | four | five |  six |
			|  ja       |  yon |   go | roku |

	@Page
	@Positive
	Scenario Outline: Find first page of entities by partition
		When querying for page 0 of size 2 for data entries in partition "<partition>"
		Then the number of latest data entries are 2
		And the latest data entries contain
			| field |       value |
			|    id |           1 |
			|  code | <partition> |
			|  name |         <1> |
		And the latest data entries contain
			| field |       value |
			|    id |           2 |
			|  code | <partition> |
			|  name |         <2> |
		And the latest data entries do not contain
			| field |       value |
			|    id |           3 |
			|  code | <partition> |
			|  name |         <3> |

		Examples:
			| partition |    1 |   2 |     3 |
			|  en       |  one | two | three |
			|  ja       | ichi |  ni |   san |

	@Page
	@Positive
	Scenario Outline: Find second page of entities by partition
		When querying for page 1 of size 2 for data entries in partition "<partition>"
		Then the number of latest data entries are 2
		And the latest data entries do not contain
			| field |       value |
			|    id |           1 |
			|  code | <partition> |
			|  name |         <1> |
		And the latest data entries do not contain
			| field |       value |
			|    id |           2 |
			|  code | <partition> |
			|  name |         <2> |
		And the latest data entries contain
			| field |       value |
			|    id |           3 |
			|  code | <partition> |
			|  name |         <3> |

		Examples:
			| partition |    1 |   2 |     3 |
			|  en       |  one | two | three |
			|  ja       | ichi |  ni |   san |

	@One
	@Positive
	Scenario Outline: Find entity by id and partition
		When querying for the data entry with id "<id>" in partition "<code>"
		Then the latest data entry matches
			| field |  value |
			|    id |   <id> |
			|  code | <code> |
			|  name | <name> |

		Examples:
			| id | code | name |
			|  4 |   en | four |
			|  5 |   en | five |
			|  6 |   en |  six |
			|  4 |   ja |  yon |
			|  5 |   ja |   go |
			|  6 |   ja | roku |

	@One
	@Negative
	Scenario: Find non existing entity by id and partition
		Then fail to query the data entry with id "10" in partition "de"



