package com.neil.publisher

import android.content.Context
import com.google.firebase.firestore.auth.User
import com.neil.publisher.data.Author

object UserManager {

    private val mainApplication = MainApplication.instance
    private const val USER_TOKEN = "userToken"

    val sharedPreferences = mainApplication.applicationContext.getSharedPreferences(USER_TOKEN, Context.MODE_PRIVATE)

    var userToken: String?
        get() {
            return sharedPreferences.getString(USER_TOKEN, null)
        }
        set(value) {
            // value就是等號後面的value
            sharedPreferences.edit().putString(USER_TOKEN, value).apply()
        }

    val user = Author("tsaichenghan999@gmail.com", "Neil2000", "哈哈是我啦")
}