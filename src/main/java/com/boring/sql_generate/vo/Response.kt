package com.boring.sql_generate.vo

class Response {
    var code: Short = 200
    var desc: String = "成功"
    var rpData: Any? = null

    constructor(rpData: Any) {
        this.rpData = rpData
    }
    constructor(rpData: Any, code: Short) {
        this.rpData = rpData
        this.code = code
    }
}