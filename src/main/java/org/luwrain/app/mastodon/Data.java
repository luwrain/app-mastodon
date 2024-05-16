
package org.luwrain.app.mastodon;

import java.io.*;

import org.luwrain.core.*;

final class Data {
    static final String PATH = "/org/luwrain/app/mastodon";

    final Settings sett;

    Data(Luwrain luwrain) throws IOException {
        sett = RegistryProxy.create(luwrain.getRegistry(), PATH, Settings.class);
    }

    interface Settings {
        String getToken(String defaultValue);

        String setToken(String value);

    }
}
