package Group_15.Trello_Project.workspace.repository;

import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceModel, Integer>
{

}
