package com.github.scroogemcfawk.mastodon.client

import social.bigbone.api.Link
import social.bigbone.api.Pageable
import social.bigbone.api.entity.Account
import social.bigbone.api.entity.Status
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class MastodonStub(val instance: String = "techhub.social") : IMastodon {
    private var seenRules = false
    var login = false
    var curTimeline = Timeline.HOME

    var homeTimelinePage = 0

    private fun getPublicStatuses(): List<Status> {
        return listOf(
            Status("0", "status#0", content = "Status 0 content.", account = Account("0", username = "Stub Account #0", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("1", "status#1", content = "Status 1 content.", account = Account("1", username = "Stub Account #1", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("2", "status#2", content = "Status 2 content.", account = Account("2", username = "Stub Account #2", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("3", "status#3", content = "Status 3 content.", account = Account("3", username = "Stub Account #3", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("4", "status#4", content = "Status 4 content.", account = Account("4", username = "Stub Account #4", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("5", "status#5", content = "Status 5 content.", account = Account("5", username = "Stub Account #5", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("6", "status#6", content = "Status 6 content.", account = Account("6", username = "Stub Account #6", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("7", "status#7", content = "Status 7 content.", account = Account("7", username = "Stub Account #7", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("8", "status#8", content = "Status 8 content.", account = Account("8", username = "Stub Account #8", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("9", "status#9", content = "Status 9 content.", account = Account("9", username = "Stub Account #9", acct = "@username${Random(System.currentTimeMillis()).nextInt() % 100}@domain")),
            Status("10", "status#10", content = "Status 10 content.", account = Account("10", username = "Stub Account #10", acct = "@username${Random(1).nextInt() % 100}@domain")),
        )
    }

    private fun getHomeStatuses(): List<Status> {
        return listOf(
            Status("100", "status#100", content = "User Status 0 content.", account = getUserAccount()),
            Status("101", "status#101", content = "User Status 1 content.", account = getUserAccount()),
            Status("102", "status#102", content = "User Status 2 content.", account = getUserAccount()),
            Status("103", "status#103", content = "User Status 3 content.", account = getUserAccount()),
            Status("104", "status#104", content = "User Status 4 content.", account = getUserAccount()),
            Status("105", "status#105", content = "User Status 5 content.", account = getUserAccount()),
            Status("106", "status#106", content = "User Status 6 content.", account = getUserAccount()),
            Status("107", "status#107", content = "User Status 7 content.", account = getUserAccount()),
            Status("108", "status#108", content = "User Status 8 content.", account = getUserAccount()),
            Status("109", "status#109", content = "User Status 9 content.", account = getUserAccount()),
            Status("110", "status#110", content = "User Status 10 content.", account = getUserAccount()),
        )
    }

    override fun getRules(): String {
        seenRules = true
        return "1. Don't\n2. If you want you can don't\n3. Check 1 and 2 items."
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        agreement: Boolean,
        locale: String,
        autologin: Boolean,
        reason: String?
    ) {
        if (!seenRules) {
            throw Exception("User didn't see the rules yet.")
        }
    }

    override fun login(username: String, password: String) {
        login = true
        println("Logging in...")
    }

    override fun logout() {
        println("Logging out...")
    }

    override fun getHomeTimeline(): Pageable<Status> {
        val l = Random.nextInt(11)
        val r = Random.nextInt(11)
        return Pageable(
            getHomeStatuses().subList(min(l,r), max(l,r)),
            Link("header", "nextpath", "prevpath", "maxid", "sinceid", "minid")
        )
    }

    override fun getPublicTimeline(): Pageable<Status> {
        val l = Random.nextInt(11)
        val r = Random.nextInt(11)
        return Pageable(
            getPublicStatuses().subList(min(l,r), max(l,r)),
            Link("header", "nextpath", "prevpath", "maxid", "sinceid", "minid")
        )
    }

    override fun searchUser(query: String): List<Account> {
        TODO("Not yet implemented")
    }

    override fun getUserByUsername(username: String, hostname: String?): Account? {
        TODO("Not yet implemented")
    }

    override fun getMe(): Account {
        return getUserAccount()
    }

    private fun getUserAccount() = Account(id = "999", username = "STUB USER ACCOUNT", acct = "@username@domain (you)")

    override fun postStatus(text: String) {
        TODO("Not yet implemented")
    }

    override fun scheduleStatusAfterDelay(text: String, delayPattern: String) {
        TODO("Not yet implemented")
    }

}

enum class Timeline {
    HOME,
    PUBLIC
}
