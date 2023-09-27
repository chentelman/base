@Delete
Feature: Test partitioned json request delete operation
	I want to be able to delete persisted base entities

	@Positive
	Scenario: Verify the delete on duplicate ids deletes the correct one
		Given an initialized partitioned "<storage>" storage
		And latest json request "<entity>"
		When deleting the json request entry with id "4" from "ja" partition
		Then query all json request entries with id "4"
		And the latest json request entries contain
			|   id |   long |    4 |
			| code | string |   en |
		And the latest json request entries <should contain>
			|   id |   long |    4 |
			| code | string |   en |
			| name | string | four |
		And the latest json request entries do not contain
			|   id |   long |    4 |
			| code | string |   ja |

		Examples:
			| entity                                                | storage                             | should contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |

	@Positive
	Scenario: Verify the deletion of the whole partition
		Given an initialized partitioned "<storage>" storage
		And latest json request "<entity>"
		When deleting all json request entries in "ja" partition
		Then query all json request entries with id "4"
		And the latest json request entries contain
			|   id |   long |    4 |
			| code | string |   en |
		And the latest json request entries <should contain>
			|   id |   long |    4 |
			| code | string |   en |
			| name | string | four |
		And the latest json request entries do not contain
			|   id |   long |    4 |
			| code | string |   ja |
		And query all json request entries with id "5"
		And the latest json request entries contain
			|   id |   long |    5 |
			| code | string |   en |
		And the latest json request entries <should contain>
			|   id |   long |    5 |
			| code | string |   en |
			| name | string | five |
		And the latest json request entries do not contain
			|   id |   long |    5 |
			| code | string |   ja |

		Examples:
			| entity                                                | storage                             | should contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |

	@Positive
	Scenario: Verify the deletion of all ids
		Given an initialized partitioned "<storage>" storage
		And latest json request "<entity>"
		When delete the json request entry with id "4"
		Then query all json request entries in "en" partition
		And the latest json request entries do not contain
			|   id |   long |    4 |
			| code | string |   en |
		And the latest json request entries contain
			|   id |   long |    5 |
			| code | string |   en |
		And the latest json request entries <should contain>
			|   id |   long |    5 |
			| code | string |   en |
			| name | string | five |
		Then query all json request entries in "ja" partition
		And the latest json request entries do not contain
			|   id |   long |    4 |
			| code | string |   ja |
		And the latest json request entries contain
			|   id |   long |    5 |
			| code | string |   ja |
		And the latest json request entries <should contain>
			|   id |   long |    5 |
			| code | string |   ja |
			| name | string |   go |

		Examples:
			| entity                                                | storage                             | should contain |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       | do not contain |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |        contain |



