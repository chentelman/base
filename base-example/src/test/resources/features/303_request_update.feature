@Update
Feature: Test json request update operation
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the entity update on entities implemeting the update interface
		Given an initialized "<storage>" storage
		When update the "<entity>" json request entry with id "2"
			|   id |   long |            2 |
			| code | string |            2 |
			| name | string | The option 2 |
		Then query the json request entry with id "2"
		And the latest json request entry matches
			|   id |   long |            2 |
			| code | string |            2 |
			| name | string | The option 2 |

		Examples:
			| entity                                                | storage                             |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |

	@Positive
	Scenario Outline: Verify the entity update on entities implemeting the update interface
		Given an initialized "<storage>" storage
		When update the "<entity>" json request entry with id "2"
			|   id |   long |            2 |
			| code | string |            2 |
			| name | string | The option 2 |
		Then query the json request entry with id "2"
		And the latest json request entry at index 0 matches
			|   id |   long |            2 |
			| code | string |            2 |

		Examples:
			| entity                                                | storage                             |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be updated
		Given an initialized "<storage>" storage
		Then fail to update the "<entity>" json request entry with id "2"
			|   id |   long |            2 |
			| code | string |            2 |
			| name | string | The option 2 |
		And the latest json request entry matches
			| code | int    |         400 |
			| text | string | BAD_REQUEST |

		Examples:
			| entity                                                | storage                             |
			| BaseTestEnumConvertJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestEnumDefaultJsonRequest                        | BaseTestEnumTestDao                 |
			| BaseTestMemAccessConvertConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessConvertDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultConvertJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemAccessDefaultDefaultJsonRequest            | BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessConvertConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessConvertDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultConvertJsonRequest | BaseTestMemPartitionedAccessTestDao |
			| BaseTestMemPartitionedAccessDefaultDefaultJsonRequest | BaseTestMemPartitionedAccessTestDao |

	@Negative
	Scenario Outline: Update non existing entities implemeting the update interface
		Given an initialized "<storage>" storage
		When fail to update the "<entity>" json request entry with id "5"
			|   id |   long |     5 |
			| code | string |    en |
			| name | string |  five |
		Then the latest json request entry matches
			| code | int    |       404 |
			| text | string | NOT_FOUND |

		Examples:
			| entity                                                | storage                             |
			| BaseTestMemConvertConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemConvertDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultConvertJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemDefaultDefaultJsonRequest                  | BaseTestMemTestDao                  |
			| BaseTestMemPartitionedConvertConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedConvertDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultConvertJsonRequest       | BaseTestMemPartitionedTestDao       |
			| BaseTestMemPartitionedDefaultDefaultJsonRequest       | BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Update non existing entities implemeting the update interface
		Given an initialized "<storage>" storage
		When update the "<entity>" json request entry with id "5"
			|   id |   long |     5 |
			| code | string |    en |
			| name | string |  five |
		And query the json request entry with id "5"
		Then the latest json request entry matches
			|   id |   long |     5 |
			| code | string |    en |
			| name | string |  five |

		Examples:
			| entity                                                | storage                             |
			| BaseTestJpaConvertConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaConvertDefaultJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultConvertJsonRequest                  | BaseTestJpaTestDao                  |
			| BaseTestJpaDefaultDefaultJsonRequest                  | BaseTestJpaTestDao                  |



