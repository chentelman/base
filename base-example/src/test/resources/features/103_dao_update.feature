@Update
Feature: Test dao update operation
	I want to be able to update persisted base entities

	@Positive
	Scenario Outline: Verify the entity update on entities implemeting the update interface
		Given an initialized "<entity>" storage
		And a data entry
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entry matches
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		Then update the data entry
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |
		And the latest data entry matches
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   shi |

		Examples:
			| entity                              |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |

	@Positive
	Scenario Outline: Update non existing entities implemeting the update interface
		Given an initialized "<entity>" storage
		Then update the data entry
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |
		And the latest data entry matches
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |

		Examples:
			| entity                              |
			| BaseTestJpaTestDao                  |
			| BaseTestMemTestDao                  |
			| BaseTestMemPartitionedTestDao       |

	@Negative
	Scenario Outline: Verify that read only entities cannot be updated
		Given an initialized "<entity>" storage
		Then fail to update the data entry
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |

		Examples:
			| entity                              |
			| BaseTestEnumTestDao                 |
			| BaseTestMemAccessTestDao            |
			| BaseTestMemPartitionedAccessTestDao |



