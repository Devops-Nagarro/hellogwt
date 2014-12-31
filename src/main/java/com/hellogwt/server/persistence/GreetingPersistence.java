/**
 * Common interface to test Hibernate and MyBatis.
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
package com.hellogwt.server.persistence;

import com.hellogwt.shared.domain.Greeting;
import java.util.List;

/**
 * Common interface for Hibernate and MyBatis DAO/Mapper
 */
public interface GreetingPersistence {
    public Greeting getGreeting(String text);
    public void addGreeting(String author, String text);
    public void updateGreeting(String author, String text);
    public void deleteGreeting(String text);
    public List<Greeting> getGreetings();
}
