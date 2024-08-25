const express = require('express');
const router = express.Router();
const jwt = require('express-jwt');
const auth = jwt({
    secret: process.env.JWT_SECRET,
    userProperty: 'payload',
    algorithms: ['HS256']  // Add the algorithms option for express-jwt
});

const authController = require('../controllers/authentication');
const tripsController = require('../controllers/trips');

// Authentication Routes
router.post('/login', authController.login);
router.post('/register', authController.register);

// Trip Routes
router.route('/trips')
    .get(tripsController.tripsList)
    .post(auth, tripsController.tripsAddTrip);

router.route('/trips/:tripCode')
    .get(tripsController.tripsFindByCode)  // Corrected to tripsFindByCode
    .put(auth, tripsController.tripsUpdateTrip);

module.exports = router;

// Middleware function to authenticate our JWT
function authenticateJWT(req, res, next) {
    const authHeader = req.headers['authorization'];

    if (!authHeader) {
        console.log('Auth Header Required but NOT PRESENT!');
        return res.sendStatus(401);
    }

    const token = authHeader.split(' ')[1];
    
    if (!token) {
        console.log('Null Bearer Token');
        return res.sendStatus(401);
    }

    jwt.verify(token, process.env.JWT_SECRET, (err, verified) => {
        if (err) {
            return res.status(401).json('Token Validation Error!');
        }
        req.auth = verified; // Set the auth param to the decoded object
        next(); // Continue to the next middleware or route handler
    });
}
