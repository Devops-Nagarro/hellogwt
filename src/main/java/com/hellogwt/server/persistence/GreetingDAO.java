/**
 * Hibernate class to handle Greeting.
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

import org.springframework.stereotype.Repository;
import com.hellogwt.shared.domain.Greeting;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GreetingDAO implements GreetingPersistence {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Override
    @Transactional(readOnly=true)
    public Greeting getGreeting(String text) {
        if (getCurrentSession() == null) {
            throw new NullPointerException("getCurrentSession() is null !");
        }
        List<Greeting> greetingList = null;
        try {
            Query q = getCurrentSession().createQuery("from Greeting as greeting where greeting.text=:text");
            q.setParameter("text", text);
            greetingList = (List<Greeting>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return greetingList.size() > 0 ? greetingList.get(0) : null;
    }

    @Override
    @Transactional(readOnly=false)
    public void addGreeting(String author, String text) {
        Greeting greeting = new Greeting();
        greeting.setAuthor(author);
        greeting.setText(text);
        hibernateTemplate.save(greeting);
    }

    @Override
    @Transactional(readOnly=false)
    public void updateGreeting(String author, String text) {
        Greeting greeting = getGreeting(text);
        if (greeting != null) {
            greeting.setAuthor(author);
            hibernateTemplate.save(greeting);
        }
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteGreeting(String text) {
        Greeting greeting = getGreeting(text);
        if (greeting != null) {
            getCurrentSession().delete(greeting);
        }
    }

    @Override
    @Transactional(readOnly=true)
    public List<Greeting> getGreetings() {
        return (List<Greeting>) getCurrentSession()
                .createCriteria(Greeting.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }
    
    private Session getCurrentSession() {
        if (hibernateTemplate.getSessionFactory() == null) {
            throw new NullPointerException("getSessionFactory() is null !");
        }
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
}
