/**
 * Mapper that will allow to perform main operations with our greetings in com.hellogwt.server.persistence package.
 * 
 * We could declare all operations in XML, but we will do it using annotations.
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
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GreetingMapper {

    @Select("SELECT * FROM greetings WHERE text = #{text}")
    Greeting getGreeting(@Param("text") String text);

    @Select("INSERT INTO greetings (author, text) VALUES (#{author}, #{text})")
    void addGreeting(@Param("author") String author, @Param("text") String text);

    @Select("UPDATE greetings SET author = #{author} WHERE text = #{text}")
    void updateGreeting(@Param("author") String author, @Param("text") String text);

    @Select("DELETE FROM greetings WHERE text = #{text}")
    void deleteGreeting(@Param("text") String text);

    @Select("SELECT * FROM greetings")
    List<Greeting> getGreetings();
}

