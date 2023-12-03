#include <iostream> // Include a library that lets us talk to the computer's input and output devices.
#include <fstream>  // Include a library that lets us read from and write to files.
#include <map>      // Include a library that lets us store pairs of items in a way that we can find them using a key.
#include <limits>   // Include a library that provides us information about different variable types.

// We tell our program to use the standard namespace, which is like using a standard set of tools.
using namespace std;

// Define a blueprint for a "ItemTracker" that will keep track of how many times each item appears.
class ItemTracker {
private:
    map<string, int> itemFrequency; // This is like a secret diary that keeps a tally of each item's count.

public:
    // A function that reads a list of items from a file and remembers how many times each item appears.
    void readDataFromFile(const string& filename) {
        ifstream inputFile(filename); // Imagine opening a book to read from it.
        if (!inputFile.is_open()) { // If the book won't open, we say there's an error.
            cout << "Error: Unable to open input file." << endl;
            return; // We stop the function because we can't do anything without the book open.
        }

        string item; // This is like a sticky note where we'll write down each item we read.
        while (inputFile >> item) { // While we can still read items from the book...
            itemFrequency[item]++; // We add a tally mark next to the item's name in our secret diary.
        }

        inputFile.close(); // When we're done, we close the book.
    }

    // A function that tells us how many times a particular item was mentioned in our list.
    void displayItemFrequency(const string& itemName) {
        cout << "Frequency of " << itemName << ": " << itemFrequency[itemName] << endl;
    }

    // A function that shows us the tally marks for every item we have in our secret diary.
    void displayAllItemFrequency() {
        for (const auto& entry : itemFrequency) { // For each entry in our secret diary...
            cout << entry.first << " " << entry.second << endl; // We say the item's name and show the tally marks.
        }
    }

    // A function that draws a picture showing us the tally marks as stars for each item.
    void displayItemHistogram() {
        for (const auto& entry : itemFrequency) { // For each entry in our secret diary...
            cout << entry.first << " "; // We say the item's name,
            for (int i = 0; i < entry.second; i++) { // and for each tally mark...
                cout << "*"; // we draw a star.
            }
            cout << endl; // Then we start a new line for the next item.
        }
    }

    // A function that writes the tally marks into a backup book, just in case we lose our secret diary.
    void writeDataToFile(const string& filename) {
        ofstream outputFile(filename); // Imagine opening another book to write into.
        if (!outputFile.is_open()) { // If the book won't open, we say there's an error.
            cout << "Error: Unable to open output file." << endl;
            return; // We stop the function because we can't write without the book open.
        }

        for (const auto& entry : itemFrequency) { // For each entry in our secret diary...
            outputFile << entry.first << " " << entry.second << endl; // We write down the item's name and its tally marks into the backup book.
        }

        outputFile.close(); // When we're done, we close the backup book.
    }
};

// A function that makes sure the computer understands a number was given when asked.
int getValidatedInteger() {
    int input; // Here's where we'll store the number.
    while (!(cin >> input)) { // If the computer doesn't understand the number...
        cout << "Invalid input. Please enter an integer: "; // We ask for the number again.
        cin.clear(); // We clear any confusion the computer had.
        cin.ignore(numeric_limits<streamsize>::max(), '\n'); // We ignore any extra stuff that was not part of the number.
    }
    cin.ignore(numeric_limits<streamsize>::max(), '\n'); // We also ignore anything else on the same line to be ready for the next question.
    return input; // We then give back the number we got.
}

// The main function is like the manager that tells everyone else what to do.
int main() {
    ItemTracker tracker; // We create an ItemTracker to keep track of our items.

    // We tell the ItemTracker to read the items from our list.
    tracker.readDataFromFile("D:\\Programming Languages\\Grocery-Tracking\\CS210_Project_Three_Input_File.txt");

    int choice; // This is like a switch that lets us choose what to do next.
    do {
        // We show a list of things we can do.
        cout << "Menu Options:" << endl;
        cout << "1. Check frequency of a specific item" << endl;
        cout << "2. Print frequency of all items" << endl;
        cout << "3. Print item histogram" << endl;
        cout << "4. Exit" << endl;
        cout << "Enter your choice: ";

        choice = getValidatedInteger(); // We use our number-checker to get a valid choice.

        switch (choice) { // Depending on the choice...
        case 1: { // If we chose 1...
            string itemName; // We prepare a sticky note for the item's name.
            cout << "Enter item name: "; // We ask for the item's name.
            cin >> itemName; // We write the item's name on the sticky note.
            tracker.displayItemFrequency(itemName); // We ask the ItemTracker to tell us the tally marks for that item.
            break; // We're done with this choice.
        }
        case 2: { // If we chose 2...
            tracker.displayAllItemFrequency(); // We ask the ItemTracker to show us all the tally marks.
            break; // We're done with this choice.
        }
        case 3: { // If we chose 3...
            tracker.displayItemHistogram(); // We ask the ItemTracker to draw us the picture with the stars.
            break; // We're done with this choice.
        }
        case 4: { // If we chose 4...
            tracker.writeDataToFile("frequency.dat"); // We ask the ItemTracker to write the tally marks into the backup book.
            cout << "Exiting program." << endl; // We say goodbye.
            break; // We're done with this choice.
        }
        default: // If we chose something that wasn't on the list...
            cout << "Invalid choice. Please select an option between 1 and 4." << endl; // We ask to pick again from the list.
        }
    } while (choice != 4); // We keep doing this until we pick 4, which is our way of saying we're finished.

    return 0; // We tell the computer that we're done and everything went well.
}
