package org.luwrain.app.mastodon.layouts;

import com.github.scroogemcfawk.mastodon.client.MastodonStub;
import com.github.scroogemcfawk.mastodon.client.Timeline;
import org.luwrain.app.base.LayoutBase;
import org.luwrain.app.mastodon.App;
import org.luwrain.controls.WizardArea;
import org.luwrain.controls.WizardArea.Frame;

public class TimelineLayout extends LayoutBase {


    final App app;
    final WizardArea wizardArea;

//    Timeline timeline;
    private Frame frame;
    public TimelineLayout(App app, Timeline timeline) {
        super(app);
        this.app = app;
        this.wizardArea = new WizardArea(getControlContext());
        frame = getMainFrame();
//        this.timeline = timeline;
        wizardArea.show(frame);
        setAreaLayout(wizardArea, null);
    }

    private Frame getMainFrame() {
        fillContent();
        return frame;
    }

    private boolean onNext(WizardArea.WizardValues values) {
        fillContent();
        return true;
    }

    private void fillContent() {
        frame = wizardArea.newFrame();
        var stub = (MastodonStub)app.client;

        frame.addText(stub.getCurTimeline().name());
        frame.addClickable("Prev", this::onNext);
        frame.addClickable("Next", this::onNext);
        var content = stub.getCurTimeline() == Timeline.HOME ? app.client.getHomeTimeline().getPart() : app.client.getPublicTimeline().getPart();
        for (var e : content) {
            frame.addText("id: " + e.getId() + ", author: " + e.getAccount().getAcct() + ", uri: " + e.getUri());
            frame.addText(e.getContent());
        }
        frame.addClickable("Menu", this::onMenu);
        wizardArea.show(frame);
    }

    private boolean onMenu(WizardArea.WizardValues values) {
        app.switchToMenu();
        return true;
    }
}
