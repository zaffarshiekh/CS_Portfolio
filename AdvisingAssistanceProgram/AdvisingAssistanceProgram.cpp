#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <limits>

using namespace std;

struct Course {
    string courseNumber;
    string courseTitle;
    string prerequisites;
};

// Predefined course data
vector<Course> predefinedCourses = {
    {"MATH201", "Discrete Mathematics", ""},
    {"CSCI300", "Introduction to Algorithms", "CSCI200, MATH201"},
    {"CSCI350", "Operating Systems", "CSCI300"},
    {"CSCI101", "Introduction to Programming in C++", "CSCI100"},
    {"CSCI100", "Introduction to Computer Science", ""},
    {"CSCI301", "Advanced Programming in C++", "CSCI101"},
    {"CSCI400", "Large Software Development", "CSCI301, CSCI350"},
    {"CSCI200", "Data Structures", "CSCI101"}
};

void loadCourseData(vector<Course>& courses);
void printCourseList(const vector<Course>& courses);
void printCourseInfo(const vector<Course>& courses, const string& courseNumber);
void displayMenu();
int getUserChoice();
void processUserInput(vector<Course>& courses);
void clearCin();

int main() {
    vector<Course> courses = predefinedCourses; // Use predefined courses for this example
    processUserInput(courses);
    return 0;
}

void loadCourseData(vector<Course>& courses) {
    cout << "Data loaded successfully." << endl;
}

void printCourseList(const vector<Course>& courses) {
    vector<Course> sortedCourses = courses;
    // Sort courses by title alphanumerically
    sort(sortedCourses.begin(), sortedCourses.end(), [](const Course& a, const Course& b) {
        return a.courseTitle < b.courseTitle;
        });

    for (const auto& course : sortedCourses) {
        cout << course.courseNumber << ": " << course.courseTitle << endl;
    }
}

void printCourseInfo(const vector<Course>& courses, const string& courseNumber) {
    auto it = find_if(courses.begin(), courses.end(), [&](const Course& course) {
        return course.courseNumber == courseNumber;
        });

    if (it != courses.end()) {
        cout << "Course Title: " << it->courseTitle << "\nPrerequisites: " << it->prerequisites << endl;
    }
    else {
        cout << "Course not found. Please enter a valid course number." << endl;
    }
}

void displayMenu() {
    cout << "\nMenu Options:\n"
        << "1. Load Data Structure (Currently preloaded)\n"
        << "2. Print Course List\n"
        << "3. Print Course\n"
        << "4. Exit\n"
        << "Enter your choice (1-4): ";
}

int getUserChoice() {
    int choice = 0;
    while (!(cin >> choice)) {
        clearCin(); // Clears the input buffer and resets any error flags
        cout << "Invalid input. Please enter a number between 1 and 4: ";
    }
    return choice;
}

void processUserInput(vector<Course>& courses) {
    int choice = 0;

    do {
        displayMenu(); // Display the menu options
        choice = getUserChoice(); // Get user choice with validation

        switch (choice) {
        case 1:
            loadCourseData(courses);
            break;
        case 2:
            printCourseList(courses);
            break;
        case 3: {
            clearCin(); // Ensure the input buffer is clean before taking new input
            cout << "Enter the course number (e.g., CSCI101): ";
            string courseNumber;
            getline(cin, courseNumber); // Use getline to allow course numbers with spaces
            printCourseInfo(courses, courseNumber);
            break;
        }
        case 4:
            cout << "Exiting program." << endl;
            break;
        default:
            cout << "Invalid choice. Please enter a number between 1 and 4." << endl;
            break;
        }
    } while (choice != 4);
}

void clearCin() {
    cin.clear(); // Reset any error flags
    cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Discard the buffer
}
