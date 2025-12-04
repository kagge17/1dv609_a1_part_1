| Test Name                                      | Correct | NoTrim | TooShort | VeryShort | WrongMsg | NoLenCheck | NoNumber | AlwaysTrue | WrongHash |
|-----------------------------------------------|---------|--------|----------|-----------|----------|-------------|----------|-------------|-----------|
| shouldTrimWhitespace                           |         | X      |          |           |          |             |          |             |           |
| shouldThrowExceptionForMissingNumber           |         |        |          |           |          |             | X        |             |           |
| shouldThrowExceptionFor11CharPassword          |         |        | X        | X         |          | X           |          |             |           |
| shouldThrowExceptionFor5CharPassword           |         |        |          | X         |          | X           |          |             |           |
| shouldThrowCorrectMessageForShortPassword      |         |        |          |           | X        | X           |          |             |           |
| getPasswordHashShouldReturnExpectedValue       |         |        |          |           |          |             |          |             | X         |
| isPasswordSameShouldReturnFalseForDifferentPasswords |   |        |          |           |          |             |          | X           |           |
| **Coverage**                                    | 100%   | 97%    | 100%     | 100%      | 100%     | 100%        | 100%     | 100%        | 100%      |
