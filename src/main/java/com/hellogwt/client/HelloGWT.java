/**
 * GWT entry point.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hellogwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Alex Tretyakov
 */
public class HelloGWT implements EntryPoint {
    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private TextBox nameTextBox = new TextBox();
    private Label greetingLabel = new Label("Hello, GWT!");

    /**
     * Use our service GreetingService in application.
     * 
     * We will invoke greet() method every time any symbol is entered into the text field.
     * If greet() completes successfully then label text will change to String that method returns.
     * Otherwise label text will tell us about error.
     */
    public void onModuleLoad() {
        RootPanel.get().add(nameTextBox);
        RootPanel.get().add(greetingLabel);

        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                greetingLabel.setText("ERROR!");
            }

            public void onSuccess(String result) {
                greetingLabel.setText(result);
            }
        };

        nameTextBox.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                greetingService.greet(nameTextBox.getText(), callback);
            }
        });
    }
}
