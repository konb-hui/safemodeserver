const BASE_URL = 'http://localhost:8001'

function get(url) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            contentType: 'application/json',
            type: 'GET',
            url: BASE_URL + url,
            success: function(res) {
                resolve(res)
            },
            error: function(res) {
                reject(res)
            }
        })
    })
}

function post(url,data) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            contentType: 'application/json',
            type: 'POST',
            url: BASE_URL + url,
            data: JSON.stringify(data),
            success: function(res) {
                resolve(res)
            },
            error: function(res) {
                reject(res)
            }
        })
    })
}
