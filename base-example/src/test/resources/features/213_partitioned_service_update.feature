@Update
Feature: Test partitioned service update operations
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the update on duplicate ids updates the correct one
		Given an initialized partitioned "<storage>" storage
		And all "<entity>" service entries with id "4"
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest service entries contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		When update the service entry <update>
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |
		Then all service entries with id "4"
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
			| entity                                         | storage                             | update                        |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       | with id "4"                   |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       | with id "4"                   |
			| BaseTestMemPartitionedConvertTestService       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition |
			| BaseTestMemPartitionedDefaultTestService       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition |



