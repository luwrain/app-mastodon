//package org.luwrain.app.mastodon.layouts
//
//import com.github.scroogemcfawk.mastodon.api.MastodonStub
//import org.luwrain.app.base.LayoutBase
//import org.luwrain.app.mastodon.App
//import org.luwrain.controls.WizardArea
//import org.luwrain.controls.WizardArea.Frame
//import org.luwrain.controls.WizardArea.WizardValues
//
//class MenuLayout(val app: App) : LayoutBase(app) {
//    var wizardArea: WizardArea
//    private var frame: Frame
//
//    init {
//        this.wizardArea = WizardArea(getControlContext())
//        frame = getMainFrame()
//        wizardArea.show(frame)
//        setAreaLayout(wizardArea, null)
//    }
//
//    fun getMainFrame(): Frame {
//        val local = wizardArea.newFrame()
//        val cli = (app as App).client
//        val stub = (cli as MastodonStub)
//        if (stub.login) {
//            setLogout(local)
//        }
//        frame = local
//        wizardArea.show(frame)
//        return local
//    }
//
//    fun setLogout(f: Frame) {
//        f.addClickable("Logout", this::onLogout)
//    }
//
//    private fun onLogout(values: WizardValues): Boolean {
//        val myApp = app as App
//        val cli = myApp.client
//        cli.logout()
//        myApp.setAreaLayout(LoginRegisterLayout(myApp))
//        return true
//    }
//}
