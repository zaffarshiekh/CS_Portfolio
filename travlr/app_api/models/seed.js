// Bring in the DB connection and the Trip schema
const path = require('path');
const Mongoose = require(path.join(__dirname, 'db')); // Absolute path to db.js
const Trip = require(path.join(__dirname, 'travlr')); // Absolute path to travlr.js

// Read seed data from JSON file
var fs = require('fs');
var trips = JSON.parse(fs.readFileSync(path.join(__dirname, '..', '..', 'data', 'trips.json'), 'utf8')); // Adjust path to trips.json

// Delete any existing records, then insert seed data
const seedDB = async () => {
    await Trip.deleteMany({});
    await Trip.insertMany(trips);
};

// Close the MongoDB connection and exit
seedDB().then(async () => {
    await Mongoose.connection.close();
    process.exit();
});