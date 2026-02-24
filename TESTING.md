# Quick_Calc Testing

## Testing Strategy

The test suite for the Quick_Calc application was organized in a way that would account for the separation between the application's two parts, which are the user interface and the arithmetic logic required to handle the calculations. Initially, however, the two mentioned parts weren't separated in a way appropriate for testing, due to the arithmetic logic being located inside of a private method embedded into an anonymous `ActionListener`. Thus, before writing the test, the said logic was moved into a separate `CalculatorLogic` class, so as to allow for meaningful testing by making the calculation logic independently testable.

A number of aspects of the application were evaluated during the testing process. Firstly, all of the four arithmetic operations, accounting for positive integers, negative integers, as well as zero, were tested. Further, three edge cases were evaluated. The said edge cases were the division by zero, operations with 18-digit operands located at the upper limit of the input length, as well as an unrecognized operator. When it comes to integration tests, complete user interaction workflows were simulated to verify that the UI layer correctly sends the inputs to the `CalculatorLogic` class and writes the obtained calculation results into the result field, as well as make sure that the "Clear" button correctly clears all of the fields.

There were, however, also some things that weren't tested in neither the module tests nor the integration tests. Firstly, the application's performance and response time weren't tested, as it was deemed that for a simple single-threaded desktop calculator, such benchmarking wouldn't add any practical value. Additionally, input validation edge cases, such as the presence of empty fields or non-numeric characters, weren't included in automated evaluation, since the responsibility of handling such cases is delegated to the UI side of the application, and a number of safeguards has been written to prevent errors in such edge cases before the testing phase.

---

## Testing Concepts

### The Testing Pyramid

The testing suite employs the testing pyramid concept and mirrors the testing pyramid's proportions. More specifically, the wider base of the pyramid is made up of 15 unit tests that evaluate the arithmetic logic, contained in `CalculatorLogic` directly. Additionally, these tests do not involve any UI initialization and produce very exact error messages useful for identifying which exact operator doesn't work as intended, or which edge case isn't handled properly. The middle layer of the pyramid consists of 4 integration tests that utilize a hidden `Quick_Calc` frame. These tests are slower than the module ones, require a display to be performed correctly, while also giving more generalized and broad failure information. These tests, however, do perform their intended purpose of confirming the existence of a proper connection between the UI and background arithmetic logic. Higher levels of the testing pyramid were intentionally not implemented for a number of reasons. Firstly, their implementation was not required. And secondly, automating the real windowing system would most likely be unreasonably expensive for an application of such a small scale.

### Black-Box and White-Box Testing

In the unit tests, the arithmetic logic of the `CalculatorLogic` class is treated as a black-box. More specifically, the unit tests care not about how exactly the arithmetic logic is actually implemented, but rather about what exact kind of output is produced by the said logic. This particular characteristic can then be said to be a benefit of the implemented unit tests, as they wouldn't be affected by possible internal changes in the logic.

Implementing the integration tests required white-box awareness. More specifically, in order to write them, it was necessary to know the sequence of input processing in `Quick_Calc`, as well as the function of the "Clear" button. The tests themselves, however, would still be classified as black-box ones, due to the fact that they only evaluate the final value of the `resultField` and do not care about any intermediate internal values.

### Functional vs Non-Functional Testing

All of the 19 written tests are functional tests. This is due to the fact that they evaluate whether the application performs as it is supposed to and produces expected outputs. More specifically, the unit tests verify that the operators perform in the intended way, while the integration tests check the correctness of the implementation for the user-facing functions. 

Non-functional tests then were intentionally excluded. More specifically, tests aiming at evaluating the performance of the application or its accessibility were excluded since such tests would be excessive for a simple calculator application. 

### Regression Testing

The test suite is written in a way that could support potential future regression testing. This is, firstly, due to the fact that all the tests can be run using a single command, thus allowing for rapid testing whenever new features are introduced. Additionally, due to the way in which the UI and arithmetic logic are separated into `CalculatorLogic` and `Quick_Calc`, the tests also have high potential of remaining relevant even after the introduction of large changes.

---

## Test Results Summary

| # | Test Name | Type | Status |
|---|---|---|---|
| 1 | `add_twoPositiveIntegers` | Unit | Pass |
| 2 | `add_positiveAndNegative` | Unit | Pass |
| 3 | `add_twoNegativeIntegers` | Unit | Pass |
| 4 | `subtract_positiveResult` | Unit | Pass |
| 5 | `subtract_negativeResult` | Unit | Pass |
| 6 | `subtract_negativeFromPositive` | Unit | Pass |
| 7 | `multiply_twoPositiveIntegers` | Unit | Pass |
| 8 | `multiply_twoNegativeIntegers` | Unit | Pass |
| 9 | `multiply_byZero` | Unit |  Pass |
| 10 | `divide_evenDivision` | Unit | Pass |
| 11 | `divide_decimalResult` | Unit | Pass |
| 12 | `divide_negativeByPositive` | Unit | Pass |
| 13 | `divideByZero_throwsArithmeticException` | Unit | Pass |
| 14 | `add_veryLargeNumbers` | Unit |  Pass |
| 15 | `unknownOperator_throwsIllegalArgumentException` | Unit | Pass |
| 16 | `integration_fullAddition` | Integration | Pass |
| 17 | `integration_clearResetsAllFields` | Integration | Pass |
| 18 | `integration_negativeNumberMultiplication` | Integration | Pass |
| 18 | `integration_fullDivision` | Integration | Pass |