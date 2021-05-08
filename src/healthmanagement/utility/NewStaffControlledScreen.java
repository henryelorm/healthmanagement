package healthmanagement.utility;

import healthmanagement.controllers.NewStaffScreensController;

/**
 *
 * @author Angie
 */
public interface NewStaffControlledScreen {

    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(NewStaffScreensController screenPage);
}
