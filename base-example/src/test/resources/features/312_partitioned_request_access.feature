@Access
Feature: Test partitioned json request access operations
	I want to be able to persist base entities

	@All
	@Positive
	Scenario Outline: Find all entities by id
		Given an initialized partitioned "<storage>" storage
		When querying for all "<entity>" json request entries with id "<id>"
		Then the number of latest json request entries are 2
		And the latest json request entries contain
			|    id |   long | <id> |
			|  code | string |   en |
		And the latest json request entries <should contain>
			|    id |   long | <id> |
			|  code | string |   en |
			|  name | string | <en> |
		And the latest json request entries contain
			|    id |   long | <id> |
			|  code | string |   ja |
		And the latest json request entries <should contain>
			|    id |   long | <id> |
			|  code | string |   ja |
			|  name | string | <ja> |

		Examples:
			| entity                                                | storage                             | id |   en |   ja | should contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |        contain |

	@Page
	@Positive
	Scenario Outline: Find first page of entities
		Given an initialized partitioned "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" json request entries with id "<id>"
		Then the number of latest json request entries are 2
		And the latest json request entries contain
			|    id |   long | <id> |
			|  code | string |   en |
		And the latest json request entries <should contain>
			|    id |   long | <id> |
			|  code | string |   en |
			|  name | string | <en> |
		And the latest json request entries contain
			|    id |   long | <id> |
			|  code | string |   ja |
		And the latest json request entries <should contain>
			|    id |   long | <id> |
			|  code | string |   ja |
			|  name | string | <ja> |

		Examples:
			| entity                                                | storage                             | id |   en |   ja | should contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 | five |   go |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 | four |  yon |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 | five |   go |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |  six | roku |        contain |

	@Page
	@Positive
	Scenario Outline: Find second page of entities
		Given an initialized partitioned "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" json request entries with id "4"
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

	@All
	@Positive
	Scenario Outline: Find all entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for all "<entity>" json request entries in partition "<partition>"
		Then the number of latest json request entries are 6
		And the latest json request entries contain
			|    id |   long |           4 |
			|  code | string | <partition> |
		And the latest json request entries contain
			|    id |   long |           5 |
			|  code | string | <partition> |
		And the latest json request entries contain
			|    id |   long |           6 |
			|  code | string | <partition> |
		And the latest json request entries <should contain>
			|    id |   long |           4 |
			|  code | string | <partition> |
			|  name | string |         <4> |
		And the latest json request entries <should contain>
			|    id |   long |           5 |
			|  code | string | <partition> |
			|  name | string |         <5> |
		And the latest json request entries <should contain>
			|    id |   long |           6 |
			|  code | string | <partition> |
			|  name | string |         <6> |

		Examples:
			| entity                                                | storage                             | partition |    4 |    5 |    6 | should contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       | four | five |  six |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       |  yon |   go | roku |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       | four | five |  six |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       |  yon |   go | roku |        contain |

	@Page
	@Positive
	Scenario Outline: Find first page of entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for page 0 of size 2 for "<entity>" json request entries in partition "<partition>"
		Then the number of latest json request entries are 2
		And the latest json request entries contain
			|    id |   long |           1 |
			|  code | string | <partition> |
		And the latest json request entries contain
			|    id |   long |           2 |
			|  code | string | <partition> |
		And the latest json request entries do not contain
			|    id |   long |           3 |
			|  code | string | <partition> |
		And the latest json request entries <should contain>
			|    id |   long |           1 |
			|  code | string | <partition> |
			|  name | string |         <1> |
		And the latest json request entries <should contain>
			|    id |   long |           2 |
			|  code | string | <partition> |
			|  name | string |         <2> |

		Examples:
			| entity                                                | storage                             | partition |    1 |   2 |     3 | should contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |        contain |

	@Page
	@Positive
	Scenario Outline: Find second page of entities by partition
		Given an initialized partitioned "<storage>" storage
		When querying for page 1 of size 2 for "<entity>" json request entries in partition "<partition>"
		Then the number of latest json request entries are 2
		And the latest json request entries do not contain
			|    id |   long |           1 |
			|  code | string | <partition> |
		And the latest json request entries do not contain
			|    id |   long |           2 |
			|  code | string | <partition> |
		And the latest json request entries contain
			|    id |   long |           3 |
			|  code | string | <partition> |
		And the latest json request entries <should contain>
			|    id |   long |           3 |
			|  code | string | <partition> |
			|  name | string |         <3> |

		Examples:
			| entity                                                | storage                             | partition |    1 |   2 |     3 | should contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  en       |  one | two | three |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  ja       | ichi |  ni |   san |        contain |

	@One
	@Positive
	Scenario Outline: Find entity by id and partition
		Given an initialized partitioned "<storage>" storage
		When querying for the "<entity>" json request entry with id "<id>" in partition "<code>"
		Then the latest json request entry matches
			|    id |   long |   <id> |
			|  code | string | <code> |
			|  name | string | <name> |

		Examples:
			| entity                                                | storage                             | id | code | name |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  4 |   en | four |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  5 |   en | five |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |  6 |   en |  six |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  4 |   ja |  yon |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  5 |   ja |   go |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |  6 |   ja | roku |

	@One
	@Negative
	Scenario Outline: Find non existing entity by id and partition
		Given an initialized partitioned "<storage>" storage
		Then fail to query the "<entity>" json request entry with id "10" in partition "de"
		And the latest json request entry matches
			| code |    int |       404 |
			| text | string | NOT_FOUND |

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



