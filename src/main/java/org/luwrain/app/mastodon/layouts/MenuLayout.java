package org.luwrain.app.mastodon.layouts;

import com.github.scroogemcfawk.mastodon.client.MastodonStub;
import com.github.scroogemcfawk.mastodon.client.Timeline;
import org.luwrain.app.base.LayoutBase;
import org.luwrain.app.mastodon.App;
import org.luwrain.controls.WizardArea;
import org.luwrain.controls.WizardArea.Frame;
import org.luwrain.controls.WizardArea.WizardValues;

public class MenuLayout extends LayoutBase {

    App app;
    WizardArea wizardArea;
    Frame frame;

    public MenuLayout(App app) {
        super(app);
        this.app = app;
        this.wizardArea = new WizardArea(getControlContext());
        frame = getMainFrame();
        wizardArea.show(frame);
        setAreaLayout(wizardArea, null);
    }

    public Frame getMainFrame() {
        var local = wizardArea.newFrame();
        var stub = (MastodonStub) app.client;
        if ((stub).getLogin()) {
            setLogout(local);
        }
        setSwitchTimeline(local);
        frame = local;
        wizardArea.show(frame);
        return local;
    }

    void setLogout(Frame f) {
        f.addClickable("Logout", this::onLogout);
    }

    void setSwitchTimeline(Frame f) {
        var stub = (MastodonStub) app.client;
        switch ((stub).getCurTimeline()) {
            case HOME -> f.addClickable("Switch to: Public", this::onPublic);
            case PUBLIC -> f.addClickable("Switch to: Home", this::onHome);
        }
    }


    private Boolean onLogout(WizardValues values) {
        app.client.logout();
//        app.setAreaLayout(new LoginLayout(app));
        app.switchToLogin();
        return true;
    }

    private boolean onPublic(WizardValues values) {
        MastodonStub stub = (MastodonStub) app.client;
        stub.setCurTimeline(Timeline.PUBLIC);
        app.switchToTimeline(Timeline.PUBLIC);
        return true;
    }

    private boolean onHome(WizardValues values) {
        MastodonStub stub = (MastodonStub) app.client;
        stub.setCurTimeline(Timeline.HOME);
        app.switchToTimeline(Timeline.HOME);
        return true;
    }
}
