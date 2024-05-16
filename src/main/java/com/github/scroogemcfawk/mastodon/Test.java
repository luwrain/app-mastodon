package com.github.scroogemcfawk.mastodon;

import social.bigbone.MastodonClient;

public class Test {
    public static void main(String[] args) {
        var client = new MastodonClient.Builder("techhub.social").build();
    }
}
