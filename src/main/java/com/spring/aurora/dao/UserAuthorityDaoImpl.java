package com.spring.aurora.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.aurora.model.UserAuthority;

//@Repository
public class UserAuthorityDaoImpl implements UserAuthorityDao {

    //private static final Logger logger = LoggerFactory.getLogger(UserAuthorityDaoImpl.class);

    // @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAuthority insert(UserAuthority authority) {
        return (UserAuthority) this.sessionFactory.getCurrentSession().save(authority);
    }

    @Override
    public void update(UserAuthority authority) {
        this.sessionFactory.getCurrentSession().update(authority);
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        UserAuthority auth = session.get(UserAuthority.class, id);
        session.delete(auth);
        session.flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserAuthority> findAllByUsername(String username) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();
    }

}
