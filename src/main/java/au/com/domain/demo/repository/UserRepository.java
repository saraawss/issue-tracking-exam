package au.com.domain.demo.repository;

import org.springframework.data.repository.CrudRepository;

import au.com.domain.demo.entity.User;

public interface UserRepository extends CrudRepository<User,Long> {

}
