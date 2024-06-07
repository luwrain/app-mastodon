package com.github.scroogemcfawk.mastodon

import com.github.scroogemcfawk.mastodon.client.Mastodon
import com.github.scroogemcfawk.util.IDebuggable

fun main() {
    val deb = object : IDebuggable {
        override val debug = true
        override val timer = true
    }
    deb.deb("Application started")
    try {
        val mastodon = Mastodon("techhub.social", true)

        val user = System.getenv("USER_1")!!
        val password = System.getenv("PASSWD")!!

        mastodon.login(user, password)

//        println(mastodon!!.postStatus(""))

        println("TEST")

//        for (t in mastodon.getPublicTimeline().part) {
//            println(t)
//        }

//        mastodon.scheduleStatusAfterDelay("Scheduled status! ⏳", "PT5M5S")

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
