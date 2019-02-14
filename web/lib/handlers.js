const request = require('request');


const activeServer = {
    "host": "dbhandler",
    "port":8080
};
const log = function (webService, url, error, statusCode) {

    console.log('------------>', `API is : http://${webService}${url}`);
    console.log('------------>', `request is : ${url}`);
    console.log('req is :', url);
    console.log('service is :', webService);
    console.log('error:', error);
    console.log('statusCode:', statusCode);
};


const logRequest = (req, res, next) => {
    console.log(`${req.method} ${req.url}`);
    next();
};

const getUsers = function (req, res) {
    let host = activeServer['host'];
    let port = activeServer['port'];
    request(`http://${host}:${port}${req.url}`, (error, response, body) => {
        let statusCode = response && response.statusCode;
        log(host, req.url, error, statusCode);
        // console.log(body);
        res.send(body);
    })
}

const addUser = function (req, res) {
    let host = activeServer['host'];
    let port = activeServer['port'];
    request.post(`http://${host}:${port}${req.url}`, {
        form: req.body
    }, (error, response, body) => {
        let statusCode = response && response.statusCode;
        log(host, req.url, error, statusCode);
        res.send(body);
    });
};

module.exports = {
    logRequest,
    getUsers,
    addUser
};