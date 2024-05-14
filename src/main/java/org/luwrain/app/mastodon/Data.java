
package org.luwrain.app.mastodon;

import java.io.*;

import com.github.scroogemcfawk.mastodon.api.*;

final class Data
{
    	static final String PATH = "/org/luwrain/app/mastodon";

    final ApplicationClient client;

    Data() throws IOException
    {
	client = new ApplicationClient(new Configuration());
    }
    
    interface Settings
    {

}
}

