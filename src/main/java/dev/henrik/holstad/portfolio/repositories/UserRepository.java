package dev.henrik.holstad.portfolio.repositories;


import dev.henrik.holstad.portfolio.dao.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
