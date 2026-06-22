# Role-Based Academic Evaluation System

A robust, command-line software application built entirely in Java. This project implements fundamental Object-Oriented Programming (OOP) principles and efficient data structures to deliver a file-driven student quiz engine, a secure multi-role authentication layer, and a data-parsed analytics dashboard for instructors.

## 🛠️ Technical Highlights & Concepts Applied
* **Object-Oriented Data Modeling:** Utilized encapsulation and class encapsulation to represent user entities, ensuring a clean separation of concerns between different software states.
* **Efficient Data Structures:** Utilized Java `HashMap` and `HashSet` collections to achieve constant-time $O(1)$ average lookups for rapid credential validation and optimized, duplicate-free random question generation.
* **File I/O Data Ingestion:** Engineered a custom file parsing architecture to read raw tab-delimited text files (`\t`), dynamically extract test bank records, and track persistent student performance logs.
* **Exception & Input Handling:** Implemented defensive programming strategies, wrapping file operations in robust try-catch blocks and utilizing loop-driven state validation to normalize user answers.

## 🚀 Key Features

### 🔒 Authentication System
* Multi-role framework enforcing distinct access paths for Students (Quiz Mode) and Instructors (Administrative View).
* File-based credential validation protected by a strict 3-attempt lock-out condition.

### 📝 Student Quiz Engine
* Programmatically streams exam questions from active text files.
* Leverages mathematical randomization paired with sets to guarantee assessment integrity without duplicate questions.
* Evaluates performance instantly, generating comprehensive, timestamped individual student scorecards in real-time.

### 📊 Instructor Analytics Dashboard
* Provides real-time macro-performance metrics compiled directly from recorded student files.
* Programmatically calculates class statistics including lowest/highest scores, overall grade averages, and student pass rates.
* Allows seamless, live expansion of the active evaluation pool.

## 💻 Tech Stack
* **Language:** Java (JDK 11+)
* **Data Handling:** Java File I/O (Scanner, PrintWriter), Input Validation, Exception Handling
* **Data Structures:** HashMaps, HashSets, ArrayLists, Arrays

## 🏃 How To Run

To compile and run this application correctly with its package structure, choose one of the following methods depending on where you are inside your terminal:

### Method A: Running from the project's root folder
If your terminal window is standing in the directory **above** the `ds_project` folder:
```bash
# Compile the files inside the package folder
javac ds_project/*.java

# Run the program using the full package path
java ds_project.ProjectDemo
```
### Method B: Running from inside the package folder
If you have navigated inside the `ds_project` folder:
```bash
# Compile everything in your current directory
javac *.java

# Move up one directory so Java can locate the package structure
cd ..

# Run the program
java ds_project.ProjectDemo
