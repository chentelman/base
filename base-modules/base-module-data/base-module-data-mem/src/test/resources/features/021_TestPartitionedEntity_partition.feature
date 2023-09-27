Feature: Mem Data Entity CRUD operations
	I want to be able to persist in memory entities

	Background: Setup data
		Given a clean "TestPartitionedEntity" data repository
		And a data entry
			|     field | value |
			|        id |     1 |
			| partition | slot1 |
			|      data | data1 |
		And a data entry
			|     field | value |
			|        id |     2 |
			| partition | slot1 |
			|      data | data2 |
		And a data entry
			|     field | value |
			|        id |     1 |
			| partition | slot2 |
			|      data | data3 |
		And a data entry
			|     field | value |
			|        id |     2 |
			| partition | slot2 |
			|      data | data4 |
		And a data entry
			|     field | value |
			|        id |     1 |
			| partition | slot3 |
			|      data | data5 |
		And a data entry
			|     field | value |
			|        id |     2 |
			| partition | slot3 |
			|      data | data6 |
		And a data entry
			|     field | value |
			|        id |     3 |
			| partition | slot3 |
			|      data | data7 |

	Scenario Outline: Test partitioned find all by id operation
		When querying for all data entries with id "<id>"
		Then the number of latest data entries are <count>

		Examples:
			| id | count |
			|  1 |   3   |
			|  2 |   3   |
			|  3 |   1   |

	Scenario Outline: Test partitioned find all by id operation
		When querying for all data entries in partition "<partition>"
		And the number of latest data entries are <before>
		Then create a data entry
			|     field | value |
			|        id |    10 |
			| partition | slot1 |
			|      data | data8 |
		And the number of data entries in partition "<partition>" are <after>

		Examples:
			| partition | before | after |
			|     slot1 |    2   |   3   |
			|     slot2 |    2   |   2   |
			|     slot3 |    3   |   3   |

	Scenario Outline: Test partitioned find by id operation
		Then <operation> for the data entry with id "<id>" in partition "<partition>"

		Examples:
			| id | partition |        operation |
			|  1 |     slot1 |         querying |
			|  2 |     slot2 |         querying |
			|  3 |     slot3 |         querying |
			|  4 |     slot4 | failing to query |

	Scenario: Test partitioned delete by id operation
		Then the number of data entries are 7
		And delete the data entry with id "2"
		And the number of data entries are 4
		And delete the data entry with id "1"
		And the number of data entries are 1
		And delete the data entry with id "3"
		And the number of data entries are 0

	Scenario: Test partitioned delete by partition operation
		Then the number of data entries are 7
		And delete all data entries in "slot1" partition
		And the number of data entries are 5
		And delete all data entries in "slot2" partition
		And the number of data entries are 3
		And delete all data entries in "slot3" partition
		And the number of data entries are 0

	Scenario: Test partitioned delete by id operation
		Then the number of data entries are 7
		And delete the data entry with id "1" from "slot1" partition
		And the number of data entries are 6
		And delete the data entry with id "1" from "slot2" partition
		And the number of data entries are 5
		And delete the data entry with id "1" from "slot3" partition
		And the number of data entries are 4



