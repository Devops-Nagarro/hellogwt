/**
 * UI creation and application logic code to EntryPoint.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.hellogwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.hellogwt.client.i18n.ClientMessages;
import com.hellogwt.shared.domain.Greeting;
import java.util.List;

public class HelloGWTWidget extends Composite {

    interface HelloGWTWidgetUiBinder extends UiBinder<SplitLayoutPanel, HelloGWTWidget> {
    }

    private static HelloGWTWidgetUiBinder uiBinder = GWT.create(HelloGWTWidgetUiBinder.class);

    private GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    
    private ClientMessages clientMsg = GWT.create(ClientMessages.class);

    final AsyncCallback<String> textbox_callback = new AsyncCallback<String>() {
        @Override
        public void onFailure(Throwable caught) {
            greetingLabel.setText(clientMsg.error());
        }

        @Override
        public void onSuccess(String result) {
            greetingLabel.setText(result);
        }
    };

    private AsyncCallback<Void> btn_callback = new AsyncCallback<Void>() {
        @Override
        public void onFailure(Throwable caught) {
            Window.alert(clientMsg.editError());
        }

        @Override
        public void onSuccess(Void result) {
            refreshGreetingsTable();
        }
    };

    /*
     * Annotated variables with @UiField for every UI element.
     * UI elements are in corresponding file HelloGWTWidget.ui.xml.
     */
    @UiField
    TextBox nameTextBox;
    @UiField
    Label greetingLabel;
    @UiField
    TextBox authorTextBox;
    @UiField
    TextBox textTextBox;
    @UiField
    Button addButton;
    @UiField
    Button updateButton;
    @UiField
    Button deleteButton;
    @UiField
    FlexTable greetingsFlexTable;
    @UiField
    SpanElement statusMessage;
    @UiField
    SpanElement persistenceClassName;

    public HelloGWTWidget() {
        initWidget(uiBinder.createAndBindUi(this));
        greetingService.persistenceClassName(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                statusMessage.setInnerHTML(clientMsg.error() + caught.getLocalizedMessage()); 
            }
            @Override
            public void onSuccess(String result) {
                persistenceClassName.setInnerHTML(result);
            }
        });
        refreshGreetingsTable();
    }

    /**
     * Every handler's logic in corresponding method annotated with @UiHandler
     * setting appropriate UI element. This approach helps to escape from
     * anonymous classes declarations and improves code insight.
     *
     * @param clickEvent event object that should be handled
     */
    @UiHandler("nameTextBox")
    void handleNameTextBoxKeyUp(KeyUpEvent keyUpEvent) {
        greetingService.greet(nameTextBox.getText(), textbox_callback);
    }
    
    @UiHandler("addButton")
    void handleAddButtonClick(ClickEvent clickEvent) {
        if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
            greetingService.getGreeting(textTextBox.getText(), new AsyncCallback<Greeting>() {
                @Override
                public void onFailure(Throwable caught) {
                    statusMessage.setInnerText("ERROR: Cannot find greeting!\n" + caught.getLocalizedMessage());
                }

                @Override
                public void onSuccess(Greeting result) {
                    if (result == null) {
                        greetingService.addGreeting(authorTextBox.getText(), textTextBox.getText(), btn_callback);
                    } else {
                        statusMessage.setInnerText(clientMsg.greetingAlreadyExists());
                    }
                }
            });
        } else {
            Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
        }
    }

    @UiHandler("updateButton")
    void handleUpdateButtonClick(ClickEvent clickEvent) {
        if (!authorTextBox.getText().isEmpty() && !textTextBox.getText().isEmpty()) {
            greetingService.updateGreeting(authorTextBox.getText(), textTextBox.getText(), btn_callback);
        } else {
            Window.alert("\"Author\" and \"Text\" fields cannot be empty!");
        }
    }

    @UiHandler("deleteButton")
    void handleDeleteButtonClick(ClickEvent clickEvent) {
        greetingService.deleteGreeting(textTextBox.getText(), btn_callback);
    }

    private void refreshGreetingsTable() {
        greetingService.getGreetings(new AsyncCallback<List<Greeting>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("ERROR: Cannot load greetings!\n" + throwable.getLocalizedMessage());
            }

            @Override
            public void onSuccess(List<Greeting> greetings) {
                fillGreetingsTable(greetings);
            }
        });
    }

    private void fillGreetingsTable(List<Greeting> greetings) {
        greetingsFlexTable.removeAllRows();

        greetingsFlexTable.setText(0, 0, clientMsg.author());
        greetingsFlexTable.setText(0, 1, clientMsg.text());

        for (Greeting greeting : greetings) {
            int row = greetingsFlexTable.getRowCount();

            greetingsFlexTable.setText(row, 0, greeting.getAuthor());
            greetingsFlexTable.setText(row, 1, greeting.getText());
        }
    }
}
