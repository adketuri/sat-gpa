sat-gpa
=======

Calculates the longest sequence of student's SAT scores/GPA with increasing SAT scores and decreasing GPA

To run, grab the jar and execute via command-line:
java -jar SatGpa.jar <file> [-d]

file: A file containing student data. See below for file specifications.
-d: An optional switch. When present, debug information is display.

File Structure
--------------
SatGpa requires each student's score to be listed as a separate line.
Each line should consist of the SAT score followed by the GPA for that student, space separated.

Example file contents:

```
1000 3.0
1300 1.1
1300 1.0
900 3.5
900 1.0
1200 2.3
1900 1.0
1400 4.0
800 4.0
1250 3.0
```