package info.potapov.library.user.dao;

import info.potapov.library.user.User;

public interface UserRepository {

    User findByUserName(String username);

}
