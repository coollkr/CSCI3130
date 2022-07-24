package Group_15.Trello_Project.task.repository;

import javax.transaction.Transactional;

import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
    /*Syntax to write native query came from following website:
     *https://www.baeldung.com/spring-data-jpa-query
     *Accessed on: July 17th*/
    @Query(value = "SELECT user_tasks FROM task_model WHERE id = :id", nativeQuery = true)
    public Optional<Integer> findUserByTask(@Param(value = "id") Integer id);

}
    
    
    

