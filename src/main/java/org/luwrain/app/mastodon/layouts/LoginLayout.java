package org.luwrain.app.mastodon.layouts;

import com.github.scroogemcfawk.mastodon.client.MastodonStub;
import com.github.scroogemcfawk.mastodon.client.Timeline;
import org.luwrain.app.mastodon.App;
import org.luwrain.controls.WizardArea;

import org.luwrain.controls.WizardArea.Frame;
import org.luwrain.app.base.*;

import static org.luwrain.controls.WizardArea.*;

public final class LoginLayout extends LayoutBase {
    final App app;
    final WizardArea wizardArea;
    private Frame frame;

    private Frame mainFrame;
    private Frame registerFrame;
    private Frame loginFrame;

    public LoginLayout(App app) {
        super(app);
        this.app = app;
        this.wizardArea = new WizardArea(getControlContext());
        frame = getMainFrame();
        wizardArea.show(frame);
        setAreaLayout(wizardArea, null);
    }

    private Frame getMainFrame() {
        Frame localFrame =  wizardArea.newFrame();
        localFrame.addText("Mastodon");
        localFrame.addClickable("Register", this::onToRegister);
        localFrame.addClickable("Login", this::onToLogin);
        localFrame.addClickable("Quit", this::onQuit);
        return localFrame;
    }

    private Frame getRegisterInstanceFrame() {
        Frame localFrame =  wizardArea.newFrame();
        localFrame.addText("Mastodon");
        localFrame.addInput("Instance name:", "");
        localFrame.addClickable("Continue", this::onContinueRegisterInstance);
        return localFrame;
    }

    private Frame getRegisterFormFrame() {
        Frame localFrame =  wizardArea.newFrame();
        localFrame.addText("Mastodon");
        localFrame.addText(app.client.getRules());
        localFrame.addClickable("Continue", this::onRegister);
        return localFrame;
    }

    private Frame getLoginInstanceFrame() {
        Frame localFrame =  wizardArea.newFrame();
        localFrame.addText("Mastodon");
        localFrame.addInput("Instance name:", "");
        localFrame.addClickable("Continue", this::onContinueLoginInstance);
        return localFrame;
    }

    private Frame getLoginFormFrame() {
        Frame localFrame =  wizardArea.newFrame();
        localFrame.addText("Mastodon");
        localFrame.addInput("Email:", "");
        localFrame.addPasswd("Password:");
        localFrame.addClickable("Continue", this::onLogin);
        return localFrame;
    }

    private boolean onContinueRegisterInstance(WizardValues values) {
        app.client = new MastodonStub("techhub.social");
        wizardArea.show(getRegisterFormFrame());
        return true;
    }

    private boolean onContinueLoginInstance(WizardValues values) {
        app.client = new MastodonStub("techhub.social");
        wizardArea.show(getLoginFormFrame());
        return true;
    }

    private boolean onRegister(WizardValues values) {
        app.message("Check your email");
        wizardArea.show(getMainFrame());
        return true;
    }

    private boolean onLogin(WizardValues values) {
        app.client.login("a", "a");
        app.message("Logged in");
        app.switchToTimeline(Timeline.HOME);
        return true;
    }

    private boolean onToRegister(WizardValues values) {
        app.message("You are registering");
        wizardArea.show(getRegisterInstanceFrame());
        return true;
    }

    private boolean onToLogin(WizardValues values) {
        app.message("You are logging in");
        wizardArea.show(getLoginInstanceFrame());
        return true;
    }

    private boolean onQuit(WizardValues values) {
        app.closeApp();
        return true;
    }
}
