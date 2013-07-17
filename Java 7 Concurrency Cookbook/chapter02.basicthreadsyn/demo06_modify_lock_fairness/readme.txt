
ReentrantLock(fair)为true模式：
---------------------------------------------------------------
Thread 0: Going to print a job
Thread 6: Going to print a job
Thread 5: Going to print a job
Thread 4: Going to print a job
Thread 3: Going to print a job
Thread 2: Going to print a job
Thread 1: Going to print a job
Thread 0: PrintQueue: Printing a Job during 6 seconds
Thread 7: Going to print a job
Thread 8: Going to print a job
Thread 9: Going to print a job
Thread 6: PrintQueue: Printing a Job during 3 seconds
Thread 5: PrintQueue: Printing a Job during 4 seconds
Thread 4: PrintQueue: Printing a Job during 8 seconds
Thread 3: PrintQueue: Printing a Job during 3 seconds
Thread 2: PrintQueue: Printing a Job during 8 seconds
Thread 1: PrintQueue: Printing a Job during 9 seconds
Thread 7: PrintQueue: Printing a Job during 2 seconds
Thread 8: PrintQueue: Printing a Job during 2 seconds
Thread 9: PrintQueue: Printing a Job during 4 seconds
Thread 0: PrintQueue: Printing a Job during 9 seconds
Thread 0: The document has been printed
Thread 6: PrintQueue: Printing a Job during 4 seconds
Thread 6: The document has been printed
Thread 5: PrintQueue: Printing a Job during 3 seconds
Thread 5: The document has been printed
Thread 4: PrintQueue: Printing a Job during 0 seconds
Thread 4: The document has been printed
Thread 3: PrintQueue: Printing a Job during 3 seconds
Thread 2: PrintQueue: Printing a Job during 5 seconds
Thread 3: The document has been printed
Thread 1: PrintQueue: Printing a Job during 5 seconds
Thread 2: The document has been printed
Thread 1: The document has been printed
Thread 7: PrintQueue: Printing a Job during 3 seconds
Thread 7: The document has been printed
Thread 8: PrintQueue: Printing a Job during 2 seconds
Thread 9: PrintQueue: Printing a Job during 4 seconds
Thread 8: The document has been printed
Thread 9: The document has been printed
---------------------------------------------------------------


ReentrantLock(fair)为false模式：
---------------------------------------------------------------
Thread 0: Going to print a job
Thread 4: Going to print a job
Thread 3: Going to print a job
Thread 2: Going to print a job
Thread 1: Going to print a job
Thread 0: PrintQueue: Printing a Job during 4 seconds
Thread 5: Going to print a job
Thread 6: Going to print a job
Thread 7: Going to print a job
Thread 8: Going to print a job
Thread 9: Going to print a job
Thread 0: PrintQueue: Printing a Job during 7 seconds
Thread 0: The document has been printed
Thread 4: PrintQueue: Printing a Job during 7 seconds
Thread 3: PrintQueue: Printing a Job during 5 seconds
Thread 2: PrintQueue: Printing a Job during 0 seconds
Thread 2: PrintQueue: Printing a Job during 2 seconds
Thread 1: PrintQueue: Printing a Job during 6 seconds
Thread 2: The document has been printed
Thread 1: PrintQueue: Printing a Job during 2 seconds
Thread 5: PrintQueue: Printing a Job during 2 seconds
Thread 1: The document has been printed
Thread 5: PrintQueue: Printing a Job during 0 seconds
Thread 6: PrintQueue: Printing a Job during 5 seconds
Thread 5: The document has been printed
Thread 6: PrintQueue: Printing a Job during 3 seconds
Thread 6: The document has been printed
Thread 7: PrintQueue: Printing a Job during 6 seconds
Thread 8: PrintQueue: Printing a Job during 7 seconds
Thread 9: PrintQueue: Printing a Job during 7 seconds
Thread 9: PrintQueue: Printing a Job during 1 seconds
Thread 9: The document has been printed
Thread 4: PrintQueue: Printing a Job during 4 seconds
Thread 4: The document has been printed
Thread 3: PrintQueue: Printing a Job during 9 seconds
Thread 7: PrintQueue: Printing a Job during 5 seconds
Thread 3: The document has been printed
Thread 7: The document has been printed
Thread 8: PrintQueue: Printing a Job during 8 seconds
Thread 8: The document has been printed
---------------------------------------------------------------








