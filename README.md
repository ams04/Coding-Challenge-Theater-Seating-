Note: Refer the ReadMeInText.txt file in the parent folder to view the same contents with proper indentations.

Solution to Theater Seating problem

1. How to run the solution:
-> Step 1: Run the "Main.java" file from the main package.
   Step 2: Enter the layout in the console and press enter twice.
          for example: 2 2 13 7
                        9 9 1 2
                        7 8 9 1
                        14 11 20 12
                        10 18
   Step 3: Enter the requests in the console and press enter.
          for example: Smith 2
                        Jones 5
                        Davis 6
                        Wilson 100
                        Johnson 3
                        Williams 4
                        Brown 8
                        Miller 12
   Step 4: Type "done" in the console and press enter.

2. Explanation of solution:

-> Step 1: The user enters the theater layout and the seat requests on the console. 
   Step 2: User's inputs are validated.
   Step 3: A Theater Layout pojo is initialized.
   Step 4: The List of Theater Request is initialized.
   Step 5: The list of requests is now iterated over using the below Algorithm:
            Step a. First the request is checked against current section(section is iterated for every request),
                    if it is an exact match then the section is assigned to the current request.
            Step b. If requested seats is smaller than the seats available in the current section:
                    Step i. A complement request is searched
                    Step ii. If found then both requests are assigned to current section
            Step c. If complement is not found, then assign the request to next Exact match of section available.
            Step d. An edge case is handled which further improves the algorithm.
   Step 6: The processed requests are printed on the console.
    

