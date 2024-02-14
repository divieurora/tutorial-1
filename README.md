## Reflection Tutorial 1
Lidwina Eurora FN - 2206083615 - Advance Programming A - VRO

### Reflection 1
Implementing Edit Product Feature and Delete Product Feature is a new challenge for me. Although I have worked on Django projects before, I find Spring Boot needs some adaptation in working with it. However, the tutorial provided for the Create Product features is a lifesaver, helping me in implementing both new features by making slight modifications and adjustments. Still, I feel the need to dive deeper into Spring Boot and not to rely on the tutorials.

I have also implemented some clean code principles such as:
1. Meaningful Names, I made all the variables and functions name simple and meaningful.
2. Function, all function I created in my code are also fit in the screen and serve the respective function according to the function name.
3. Comment, as I have mentioned earlier, I used meaningful names and functions to minimize the needs of using comments.
4. Object and Data Structures, I implemented OOP, Interface Implementations, and also Getter Setter for Private Modifier.

For Error Handling and Secure Coding, I have not applied these principles yet because I don't really know how to write the code that functions well. I will continue to learn about it so that I can improve my code and implementing all the clean code principles.

### Reflection 2
1. After writing unit tests and all the tests has passed, I feel genuinely happy and more confident about the code I have written. In my opinion, I think the number of unit tests needed can vary depends on the complexity of the code and behaviours. Code coverage can  be used as metric to measures percentage of code passed the unit tests, but even though code coverage achieved 100%, it does not guarantee that the code has no bugs or errors because code coverage means that all the code already had a test for the functionality of it.
2. I personally think that the number of items in the product list can be combined with the create product feature. And if there's a new class similar to the prior function with the same setup procedures and instance variables, it will reduced the code efficiency, cleanliness, and quality. My suggestion for possible improvements is extract the logic into a one test class to reduced duplication and ensure the test cover both of the functionality.


## Reflection Tutorial 2
### Code Quality Issues I Fixed
I experienced some trouble in fixing code quality issues. However, I have one unit test I changed to make it pass the test. But I have not yet add some test case to increase my code coverage.

### CI/CD Implementations
I have already implemented CI/CD in my project. I have created workflows that automatically runs after triggered. I am  using SonarCloud for this and as for CD, I am using Koyeb.