Feature: S1
  This feature checks that we're able to load and walk an iTunes "XML" library.

  Scenario Outline: Loading
    Given the iTunes file <filename>
    Then there should be <albumcount> albums found

    Examples: Single digits
      | filename   | artist    | albumcount |
      | itunes1.xml | bhanu1 |          1 |
