
package org.luwrain.app.mastodon;

import java.io.*;

import com.github.scroogemcfawk.mastodon.client.IMastodon;
import com.github.scroogemcfawk.mastodon.client.MastodonStub;
import com.github.scroogemcfawk.mastodon.client.Timeline;
import org.luwrain.app.mastodon.layouts.LoginLayout;
import org.luwrain.app.mastodon.layouts.MenuLayout;
import org.luwrain.app.mastodon.layouts.TimelineLayout;
import org.luwrain.core.*;
import org.luwrain.app.base.*;

public final class App extends AppBase<Strings> {
    static final String
            LOG_COMPONENT = "notepad";

    private Data data = null;
    private Conversations conv = null;
    private MainLayout mainLayout = null;
    private StartingLayout startingLayout = null;

    public IMastodon client;

    public App() throws IOException {
        super(Strings.NAME, Strings.class, "luwrain.notepad");
    }

    @Override
    protected AreaLayout onAppInit() throws IOException {
        this.data = new Data(getLuwrain());
        this.conv = new Conversations(this);
        setAppName(getStrings().appName());
        return getTimelineScreen();
    }

    @Override
    public boolean onEscape() {
        closeApp();
        return true;
    }

    Conversations getConv() {
        return this.conv;
    }

    Data getData() {
        return data;
    }

    public AreaLayout getLoginScreen() {
        return new LoginLayout(this).getAreaLayout();
    }

    public AreaLayout getTimelineScreen() {
        this.client = new MastodonStub("techhub.social");
        client.login("a", "a");
        return new TimelineLayout(this, Timeline.HOME).getAreaLayout();
    }

    public void switchToLogin() {
        setAreaLayout(new LoginLayout(this));
    }

    public void switchToMenu() {
        setAreaLayout(new MenuLayout(this));
    }

    public void switchToTimeline(Timeline timeline) {
        setAreaLayout(new TimelineLayout(this, timeline));
    }
}
