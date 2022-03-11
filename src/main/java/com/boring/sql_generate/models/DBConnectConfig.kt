package com.boring.sql_generate.models

import com.boring.sql_generate.config.DBTypeEnum

data class DBConnectConfig(val dbType: DBTypeEnum, val userName: String, val passWord: String, val url: String) {
}
