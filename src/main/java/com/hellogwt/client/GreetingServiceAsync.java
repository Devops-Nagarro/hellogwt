/**
 * The pair of Service GreetingService.
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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hellogwt.shared.domain.Greeting;
import java.util.List;

public interface GreetingServiceAsync {

    void getGreeting(String text, AsyncCallback<Greeting> async);

    void addGreeting(String author, String text, AsyncCallback<Void> async);

    void updateGreeting(String author, String text, AsyncCallback<Void> async);

    void deleteGreeting(String text, AsyncCallback<Void> async);

    void getGreetings(AsyncCallback<List<Greeting>> async);

    void greet(String name, AsyncCallback<String> callback);
}
