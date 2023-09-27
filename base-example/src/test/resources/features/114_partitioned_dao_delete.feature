@Delete
Feature: Test partitioned dao delete operation
	I want to be able to delete persisted base entities

	Background:
		Given an initialized partitioned "BaseTestMemPartitionedTestDao" storage

	@Positive
	Scenario: Verify the delete on duplicate ids deletes the correct one
		When deleting the data entry with id "4" from "ja" partition
		Then query all data entries with id "4"
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |

	@Positive
	Scenario: Verify the deletion of the whole partition
		When deleting all data entries in "ja" partition
		Then query all data entries with id "4"
		And the latest data entries contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		And query all data entries with id "5"
		And the latest data entries contain
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |
		And the latest data entries do not contain
			| field | value |
			|    id |     5 |
			|  code |    ja |
			|  name |    go |

	@Positive
	Scenario: Verify the deletion of all ids
		When delete the data entry with id "4"
		Then query all data entries in "en" partition
		And the latest data entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    en |
			|  name |  four |
		And the latest data entries contain
			| field | value |
			|    id |     5 |
			|  code |    en |
			|  name |  five |
		Then query all data entries in "ja" partition
		And the latest data entries do not contain
			| field | value |
			|    id |     4 |
			|  code |    ja |
			|  name |   yon |
		And the latest data entries contain
			| field | value |
			|    id |     5 |
			|  code |    ja |
			|  name |    go |



