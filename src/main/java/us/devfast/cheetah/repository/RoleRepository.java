package us.devfast.cheetah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.devfast.cheetah.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findRoleEntitiesByName(String name);

    RoleEntity findRoleEntityById(Integer integer);
}
