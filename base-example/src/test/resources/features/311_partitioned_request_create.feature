@Create
Feature: Test partitioned json request create operation
	I want to be able to persist base entities

	@Positive
	Scenario Outline: Verify the duplicate id creation
		Given an initialized "<storage>" storage
		When a "<entity>" json request entry
			|    id |   long |     4 |
			|  code | string |    en |
			|  name | string |  four |
		And a json request entry
			|    id |   long |     4 |
			|  code | string |    ja |
			|  name | string |   shi |
		Then query all json request entries with id "4"
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    en |
		And the latest json request entries contain
			|    id |   long |     4 |
			|  code | string |    ja |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    en |
			|  name | string |  four |
		And the latest json request entries <should contain>
			|    id |   long |     4 |
			|  code | string |    ja |
			|  name | string |   shi |

		Examples:
			| entity                                                | storage                             | should contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |



