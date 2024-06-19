package com.acciojob.BookMyShowMAY.Repositories;
import com.acciojob.BookMyShowMAY.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
