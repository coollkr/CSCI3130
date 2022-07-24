package Group_15.Trello_Project.user.repository;

import Group_15.Trello_Project.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Query(value = "from UserModel where email = :email")
    public Optional<UserModel> findByEmail(@Param(value = "email") String email);
}