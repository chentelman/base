@Update
Feature: Test partitioned json request update operations
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the update on duplicate ids updates the correct one
		Given an initialized partitioned "<storage>" storage
		And all "<entity>" json request entries with id "4"
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    en |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    en |
			|  name | string |  four |
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    ja |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    ja |
			|  name | string |   yon |
		When update the json request entry <update>
			|    id |   long |     4 |
			|  code | string |    ja |
			|  name | string |   shi |
		Then all json request entries with id "4"
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    en |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    en |
			|  name | string |  four |
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    ja |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    ja |
			|  name | string |   shi |

		Examples:
			| entity                                                | storage                             | update                        | should contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4"                   | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4"                   |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4"                   | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4"                   |        contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       | with id "4" in "ja" partition |        contain |



