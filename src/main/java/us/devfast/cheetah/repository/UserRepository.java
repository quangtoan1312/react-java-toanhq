package us.devfast.cheetah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.devfast.cheetah.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserEntityByUserName(String userName);

    UserEntity findUserEntityByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
