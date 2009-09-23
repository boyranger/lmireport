package it.businesslogic.ireport.plugin.templatemanager;
public class ReportTemplateItemAction extends TemplateItemAction {

    public ReportTemplateItemAction()
    {
        setDisplayName("ƒ£∞Â‘§¿¿");
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/plugin/templatemanager/new_report.png")));
        setDescription("÷–Œƒ≤‚ ‘");
        putProperty(PROP_SHOW_TEMPLATES, Boolean.TRUE);
        putProperty(PROP_SHOW_FINISH_BUTTON, Boolean.FALSE);
        putProperty(PROP_SHOW_LAUNCH_REPORT_WIZARD_BUTTON, Boolean.TRUE);
        putProperty(PROP_SHOW_OPEN_TEMPLATE_BUTTON, Boolean.TRUE);
    }


    @Override
    public void performAction(TemplatesFrame frame, int buttonIndex) {
        if (frame.getSelectedTemplateDescriptor() == null) return;
        //frame.runTemplateWizard(frame.getSelectedTemplateDescriptor(), buttonIndex == BUTTON_LAUNCH_REPORT_WIZARD);
    }

}