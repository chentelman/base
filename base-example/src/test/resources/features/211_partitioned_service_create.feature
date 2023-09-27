@Create
Feature: Test partitioned service create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the duplicate id creation
		Given an initialized "<storage>" storage
		When a "<entity>" service entry
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And a service entry
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |
		Then query all service entries with id "4"
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |

		Examples:
			| entity                                         | storage                             |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       |



