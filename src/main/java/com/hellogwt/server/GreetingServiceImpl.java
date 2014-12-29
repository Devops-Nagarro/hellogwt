/**
 * Server part GreetingService.
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
package com.hellogwt.server;

import com.hellogwt.client.GreetingService;
import com.hellogwt.server.persistence.GreetingMapper;
import com.hellogwt.shared.domain.Greeting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A class that implements GreetingService interface in com.hellogwt.server
 * package. Don't forget to annotate it with @Service. Class GreetingServiceImpl
 * implements method greet(). In our case the method takes one String as an
 * argument, builds another String and returns it. Implements all declared CRUD
 * methods in service GreetingServiceImpl using created mapper.
 */
@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private GreetingMapper greetingMapper;

    @Override
    public Greeting getGreeting(String text) {
        return greetingMapper.getGreeting(text);
    }

    @Override
    public void addGreeting(String author, String text) {
        greetingMapper.addGreeting(author, text);
    }

    @Override
    public void updateGreeting(String author, String text) {
        greetingMapper.updateGreeting(author, text);
    }

    @Override
    public void deleteGreeting(String text) {
        greetingMapper.deleteGreeting(text);
    }

    @Override
    public List<Greeting> getGreetings() {
        return greetingMapper.getGreetings();
    }

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
