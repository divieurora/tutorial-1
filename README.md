### Hello! 👋🏻
Lidwina Eurora FN - 2206083615 - Advance Programming A - VRO
<hr>

#### Deployment Link 👩‍💻👇🏻
[Koyeb - ADV Shop](https://module-2-tutorial-adpro.koyeb.app)

#### Reflection 🗒️👇🏻
<details>
    <summary> Module 1</summary>

### Reflection Tutorial 1

#### Reflection 1
Implementing Edit Product Feature and Delete Product Feature is a new challenge for me. Although I have worked on Django projects before, I find Spring Boot needs some adaptation in working with it. However, the tutorial provided for the Create Product features is a lifesaver, helping me in implementing both new features by making slight modifications and adjustments. Still, I feel the need to dive deeper into Spring Boot and not to rely on the tutorials.

I have also implemented some clean code principles such as:
1. Meaningful Names, I made all the variables and functions name simple and meaningful.
2. Function, all function I created in my code are also fit in the screen and serve the respective function according to the function name.
3. Comment, as I have mentioned earlier, I used meaningful names and functions to minimize the needs of using comments.
4. Object and Data Structures, I implemented OOP, Interface Implementations, and also Getter Setter for Private Modifier.

For Error Handling and Secure Coding, I have not applied these principles yet because I don't really know how to write the code that functions well. I will continue to learn about it so that I can improve my code and implementing all the clean code principles.

#### Reflection 2
1. After writing unit tests and all the tests has passed, I feel genuinely happy and more confident about the code I have written. In my opinion, I think the number of unit tests needed can vary depends on the complexity of the code and behaviours. Code coverage can  be used as metric to measures percentage of code passed the unit tests, but even though code coverage achieved 100%, it does not guarantee that the code has no bugs or errors because code coverage means that all the code already had a test for the functionality of it.
2. I personally think that the number of items in the product list can be combined with the create product feature. And if there's a new class similar to the prior function with the same setup procedures and instance variables, it will reduced the code efficiency, cleanliness, and quality. My suggestion for possible improvements is extract the logic into a one test class to reduced duplication and ensure the test cover both of the functionality.
</details>
<details>
    <summary>Module 2</summary>

### Reflection Tutorial 2
#### Code Quality Issues I Fixed
I experienced some trouble in fixing code quality issues. However, I have one unit test I changed to make it pass the test. But I have not yet add some test case to increase my code coverage.

#### CI/CD Implementations
I have already implemented CI/CD in my project. I have created workflows that automatically runs after triggered. I am  using SonarCloud for this and as for CD, I am using Koyeb.
</details>

<details>
    <summary>Module 3</summary>

### Reflection Tutorial 3

#### SOLID Principles

1. SRP (Single Responsibility Principle)

   My code has already implemented SRP, where CarController and ProductController are separated into two different files. It is used to ensure that each file has its own responsibility encapsulated into the file. I also removed the inheritance relationship between CarController and ProductController because CarController shouldn't be managing mapping to create products. In a separate file, I also have HomeController for the HomePage.


2. OCP (Open-Closed Principle)

   My code has already implemented this principle, particularly in the controller section without any further modifications. 


3. LSP (Liskov Subtitution Principle)

   My code has already implemented this principle, for CarServiceImpl and ProductServiceImpl each implementing CarService and ProductService interfaces by overriding methods.


4. ISP (Interface Segregation Principle)

   My code has already implemented ISP, where CarService is implemented by CarServiceImpl and ProductService is implemented by ProductServiceImpl. The two interfaces are not combined because they have specific behaviors for car service and product service respectively.


5. DIP (Dependency Inversion Principle)

   My code has already implemented DIP, where ProductController and CarController, where references to services are maintained in the form of interfaces (ProductService and CarService) rather than concrete classes. It is ensure that high-level modules should rely on abstraction.


#### Advantages of applying SOLID

- Make the code maintainable, scalable, and flexible.  By sticking to SOLID principles, like SRP and OCP, we ensure that our code is easy to update and grow. Each part of the code has a clear job, so we can add new features without messing up what's already there. DIP keeps things flexible by making sure parts of our code aren't too tightly connected, so we can switch them out if needed.
- Ensure clean and understandable code for collaboration and bug fixing. SOLID principles help us write code that's easy to understand and work with. With clear responsibilities for each piece of code (thanks to SRP and ISP), it's simple to figure out what's going on and fix any bugs that pop up. This makes it easier for teams to work together and keeps the codebase tidy and manageable.


#### Disadvantages of not applying SOLID

- No one knows the code except the coders. When SOLID principles are not followed, the code may become cryptic and difficult for anyone other than the original developers to comprehend. Without clear responsibilities and structure, it's like trying to decipher a secret code, making collaboration and knowledge sharing among team members challenging.
- Not flexible, more complex, and may hard to find & fix bugs. Without SOLID principles, the code tends to lack flexibility and becomes unnecessarily complex. This complexity can make it harder to adapt the code to changing requirements or to troubleshoot and fix bugs efficiently. It's like trying to untangle a mess of wires – the more tangled they are, the harder it is to find and fix the problem.


</details>