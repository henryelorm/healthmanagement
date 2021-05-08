/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthmanagement.utility;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author Elorm
 */
public class HtmlEditorListener {

    private final BooleanProperty editedProperty;

    private String htmlRef;

    public HtmlEditorListener(final HTMLEditor editor) {
        editedProperty = new SimpleBooleanProperty();
        editedProperty.addListener((ov, o, n) -> htmlRef = n ? null : editor.getHtmlText());
        editedProperty.set(false);

        editor.setOnMouseClicked(e -> checkEdition(editor.getHtmlText()));
        editor.addEventFilter(KeyEvent.KEY_TYPED, e -> checkEdition(editor.getHtmlText()));
    }

    public BooleanProperty editedProperty() {
        return editedProperty;
    }

    private void checkEdition(final String html) {
        if (editedProperty.get()) {
            return;
        }
        editedProperty.set(htmlRef != null
                && html.length() != htmlRef.length()
                || !html.equals(htmlRef));
    }

}
