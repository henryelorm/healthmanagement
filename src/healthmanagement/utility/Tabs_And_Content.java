/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Elorm
 */
public class Tabs_And_Content {

    public Tabs_And_Content() {

    }

    public Tabs_And_Content(TabPane tabpane, Tab tab, boolean closeable, String tabText, String tabId, String url, String style) {

        tab = new Tab(tabText);
        
        tab.setId(tabId);
        tab.setStyle(style);
        tab.setClosable(closeable);
        RedundancyCheck.mainTabpane(tabpane, tab, tabText);
        Templates templates = new Templates();
        templates.searchP(tab, url);
    }

}
