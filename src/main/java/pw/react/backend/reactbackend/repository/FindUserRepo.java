package pw.react.backend.reactbackend.repository;

import pw.react.backend.reactbackend.entity.User;

public interface FindUserRepo {

    User find(User usr);

}