package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        this.save(new User(counter.incrementAndGet(), "Вася", "mailone@mail.com", "one", Role.ROLE_ADMIN));
        this.save(new User(counter.incrementAndGet(), "Петя", "mailtwo@mail.com", "two", Role.ROLE_USER));
        this.save(new User(counter.incrementAndGet(), "Коля", "mailthree@mail.com", "three", Role.ROLE_USER));
    }
    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (repository.containsKey(id))
        {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        if (repository.containsKey(id))
        {
            return repository.get(id);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> list = new ArrayList<User>(repository.values());
        list.sort(Comparator.comparing(NamedEntity::getName));
        return list;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        for (Map.Entry<Integer, User> pair : repository.entrySet()){
            if (pair.getValue().getEmail().equals(email)){
                return pair.getValue();
            }
        }
        return null;
    }
}
