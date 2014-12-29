/**
 * GWT entry point.
 * 
 * Content of class HelloGWT does not contain UI creation and application logic code.
 * All this stuff is helloGWTWidget object's responsibility.
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
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * @author Alex Tretyakov
 */
public class HelloGWT implements EntryPoint {

    /**
     * Use our service GreetingService in application.
     *
     * We will invoke greet() method every time any symbol is entered into the
     * text field. If greet() completes successfully then label text will change
     * to String that method returns. Otherwise label text will tell us about
     * error.
     */
    @Override
    public void onModuleLoad() {
        HelloGWTWidget helloGWTWidget = GWT.create(HelloGWTWidget.class);

        RootLayoutPanel.get().add(helloGWTWidget);
    }
}
