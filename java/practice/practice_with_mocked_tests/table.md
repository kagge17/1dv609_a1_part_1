| Test Name                                         | Correct | AllowDayUpTo30 | AllowMonth0 | FormatAlwaysTrue | FormatAlwaysFalse | MessyLuhn | WrongLength |
|---------------------------------------------------|:-------:|:--------------:|:-----------:|:----------------:|:-----------------:|:---------:|:-----------:|
| isValidDayShouldReturnTrueForValidDay             |         | X              |             |                  |                   |           |             |
| isValidMonthShouldReturnFalseForInvalidMonth      |         |                | X           |                  |                   |           |             |
| isCorrectFormatShouldReturnFalseForInvalidFormat  |         |                |             | X                |                   |           |             |
| isCorrectFormatShouldReturnTrueForValidFormat     |         |                |             |                  | X                 |           |             |
| luhnIsCorrectShouldReturnTrueForValidLuhn         |         |                |             |                  |                   | X         |             |
| isCorrectLengthShouldReturnFalseForMoreThanElevenChars |     |                |             |                  |                   |           | X           |
| Coverage                                     | 100%| 100%       | 100%    | 100%         | 100%          | 100%  | 100%    |


| Test Name                                      | Correct | NoLenCheck | NoLuhn | NoTrim | WrongYear |
|------------------------------------------------|:-------:|:----------:|:------:|:------:|:---------:|
| shouldCreateValidSSNWhenAllChecksPass          |         | X          | X      |        | X         |
| constructorShouldTrimInputBeforeValidation     |         |            |        | X      |           |
| constructorShouldThrowExceptionForInvalidLength|         | X          |        |        |           |
| constructorShouldThrowExceptionForInvalidLuhn  |         |            | X      |        |           |
| getYearShouldReturnExpectedValue               |         |            |        |        | X         |
| Coverage | 75% | 80% | 70% | 73% | 75% |
