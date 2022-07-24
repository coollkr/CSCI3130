package Group_15.Trello_Project.board.repository;

import Group_15.Trello_Project.board.entity.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardModel, Integer>
{

}

