const mongoose = require('mongoose');

// Defining the trip schema
const tripSchema = new mongoose.Schema({
    code: { type: String, required: true, index: true },
    name: { type: String, required: true, index: true },
    length: { type: String, requred: true },
    start: { type: Date, requred: true },
    resort: { type: String, required: true },
    perPerson: { type: String, requried: true } ,
    image: { type: String, required: true },
    description: { type: String, required: true }
});

// mongoose.model('trips', tripSchema);
module.exports = mongoose.model("trips", tripSchema);