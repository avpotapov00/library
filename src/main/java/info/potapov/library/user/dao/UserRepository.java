package info.potapov.library.user.dao;

import info.potapov.library.reader.entity.Reader;
import info.potapov.library.user.User;

public interface UserRepository {

    User findByUserName(String username);

}
