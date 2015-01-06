/**
 * GWTTestCase for asynchronous service.
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
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author Olivier Maury <Olivier.Maury@paca.inra.fr>
 */
public class GwtTestGreetingService extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "com.hellogwt.HelloGWT";
    }

    /**
     * this test will send a request to the server using the greetServer method
     * in GreetingService and verify the response.
     */
    public void testGreetingService() {
        /* create the service that we will test. */
        GreetingServiceAsync greetingService
                = GWT.create(GreetingService.class);
        ServiceDefTarget target = (ServiceDefTarget) greetingService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL()
                + "springGwtServices/greetingService");

        /* since RPC calls are asynchronous, we will need to wait
         for a response after this test method returns. This line
         tells the test runner to wait up to 10 seconds
         before timing out. */
        delayTestFinish(10000);

        /* send a request to the server. */
        greetingService.greet("GWT User",
                new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        /* The request resulted in an unexpected error. */
                        fail("Request failure: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        /* verify that the response is correct. */
                        assertTrue(result.startsWith("Hello, GWT User!"));

                        /* now that we have received a response, we need to
                         tell the test runner that the test is complete.
                         You must call finishTest() after an asynchronous test
                         finishes successfully, or the test will time out.*/
                        finishTest();
                    }
                });
    }
}
