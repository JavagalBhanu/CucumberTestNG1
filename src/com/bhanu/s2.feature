Feature: S2
  This feature checks that we're able to load and walk an iTunes "XML" library.

  Scenario Outline: Loading
    Given the iTunes file <filename>
    When searching for the artist <artist>


    Examples: Single digits
      | filename   | artist    | albumcount |
      | itunes1.xml | bhanu2 |          2 |