
package org.luwrain.app.mastodon;

import com.github.scroogemcfawk.mastodon.client.MastodonStub;
import org.luwrain.controls.*;
import org.luwrain.app.base.*;

import org.luwrain.controls.WizardArea.Frame;
import org.luwrain.controls.WizardArea.WizardValues;

final class StartingLayout extends LayoutBase {
    final App app;
    final Data data;
    final WizardArea wizardArea;
    final Frame introFrame;

    StartingLayout(App app) {
        super(app);
        this.app = app;
        this.data = app.getData();
        wizardArea = new WizardArea(getControlContext());
        this.introFrame = wizardArea.newFrame()
                                    .addText(app.getStrings().wizardIntro())
//                                    .addInput(app.getStrings().wizardName(), "supercoolusername")
                                    .addInput(app.getStrings().wizardMail(), "your@email.com")
                                    .addInput(app.getStrings().wizardPassword(), "password1234")
                                    .addClickable(app.getStrings().wizardContinue(), this::onMailAddress);
        wizardArea.show(introFrame);
        setAreaLayout(wizardArea, null);
    }

    private boolean onMailAddress(WizardValues values) {
        final String
                email = values.getText(0).trim(),
                passwd = values.getText(1);
        try {
            final boolean debug = true;
            final boolean debugTimer = true;
            final var mastodon = new MastodonStub();
            mastodon.login(email, passwd);
            app.message("You are logged in as " + mastodon.getMe().getUsername());
        } catch (Exception ex) {
            app.crash(ex);
        }

        return true;
    }

}
