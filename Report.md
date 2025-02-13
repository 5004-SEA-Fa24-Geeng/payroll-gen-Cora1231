# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for?
   * A CSV is a comma-separated values file, which allows data to be saved in a tabular format. CSVs look like a garden-variety spreadsheet but with a . csv extension. CSV files can be used with most any spreadsheet program, such as Microsoft Excel or Google Spreadsheets[^1].

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?
    * Using List<IEmoloyee> is more flexible. 

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?
    * has-a
4. Can you provide an example of a has-a relationship in your code (if one exists)?
   * In `HourlyEmployee`, it contains `ytdEarnings`, and `ytdTaxesPaid`. 

5. Can you provide an example of an is-a relationship in your code (if one exists)?
   * HourlyEmployee is an Employee

6. What is the difference between an interface and an abstract class?
   * An interface defines a set of method signatures that a class must implement, serving as a contract for behavior without providing any implementation details[^2]. An abstract class, on the other hand, can include both abstract methods and concrete implementations, offering a common foundation and shared functionality for related classes[^3].

7. What is the advantage of using an interface over an abstract class?
   * An interface allows a class to implement multiple contracts, offering greater flexibility compared to an abstract class

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 
   * it is not valid. List<Integer> numbers = new ArrayList<>();

9. Which class/method is described as the "driver" for your application? 
   * payrollGenerator and its main() method


10. How do you create a temporary folder for JUnit Testing? 
   * We first need to create our test class, and declare a TemporaryFolder field. Then we use the temporary folder in test methods. Finally, after each test, JUnit will automatically delete the temporary files and folders.


## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 
* In order to conduct a robust analysis of pay equity, I believe it is important to include demographic information such as gender and age, as well as role-specific details such as job level, department, and tenure, which allows for comparisons of similar positions across the organization. It is also important to record a detailed pay breakdown, including base salary, bonuses and pre-tax benefits, as these factors can have a significant impact on net pay. It is also important to be transparent about pay, with some studies showing that, on average, pay disclosure improves gender pay equality(Lyons & Zhang, 2022).Finally, I believe that women should be given more opportunities in the workplace. By empowering women economically through innovative business and technology, we can create significant spillover effects for broader social and economic development(Tang, 2020).






* [^1] What is a .CSV file and what does it mean for my ecommerce business?. BigCommerce. (n.d.). https://www.bigcommerce.com/glossary/what-csv-file-and-what-does-it-mean-my-ecommerce-business/
* [^2] GeeksforGeeks. (2025, February 3). Java interface. https://www.geeksforgeeks.org/interfaces-in-java/
* [^3] Abstract methods and classes. Abstract Methods and Classes (The JavaTM Tutorials > Learning the Java Language > Interfaces and Inheritance). (n.d.). https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
* [^4] Lyons, E., & Zhang, L. (2022, December 30). Salary transparency and gender pay inequality: Evidence from Canadian universities. SSRN. https://papers.ssrn.com/sol3/papers.cfm?abstract_id=4308197
* [^5] Tang, C. S. (2020). Innovative Technology and operations for alleviating poverty through Women’s Economic Empowerment. SSRN Electronic Journal. https://doi.org/10.2139/ssrn.3748862



