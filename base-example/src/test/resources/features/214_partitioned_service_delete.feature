@Delete
Feature: Test partitioned service delete operation
	I want to be able to delete persisted base entities

	@Positive
	Scenario: Verify the delete on duplicate ids deletes the correct one
		Given an initialized partitioned "<storage>" storage
		And latest service "<entity>"
		When deleting the service entry with id "4" from "ja" partition
		Then query all service entries with id "4"
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Positive
	Scenario: Verify the deletion of the whole partition
		Given an initialized partitioned "<storage>" storage
		And latest service "<entity>"
		When deleting all service entries in "ja" partition
		Then query all service entries with id "4"
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		And query all service entries with id "5"
		And the latest service entries contain
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |
		And the latest service entries do not contain
			| field | value |
			|    id |     5 |
			|  code |    ja |
			|  name |    go |

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |

	@Positive
	Scenario: Verify the deletion of all ids
		Given an initialized partitioned "<storage>" storage
		And latest service "<entity>"
		When delete the service entry with id "4"
		Then query all service entries in "en" partition
		And the latest service entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entries contain
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |
		Then query all service entries in "ja" partition
		And the latest service entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		And the latest service entries contain
			| field | value |
			|    id |     5 |
			|  code |    ja |
			|  name |    go |

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |



