const express = require('express');
const app=express();
const handlers = require('./lib/handlers');

app.use(express.urlencoded({extended: true}));
app.use(handlers.logRequest);

app.post('/add_user',handlers.addUser);

app.get('/users',handlers.getUsers);
app.use(express.static('public'));


// app.get('*',handlers.getReqPipe);
// app.post('*',handlers.postReqPipe);

module.exports = app;