package Group_15.Trello_Project.workspace.entity;

import Group_15.Trello_Project.board.entity.BoardModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class WorkspaceModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String workspace_name;

    private String workspace_description;

    @OneToMany(targetEntity = BoardModel.class)
    @JoinColumn(name = "board_mapping")
    private List<BoardModel> boards;

    public WorkspaceModel(String workspace_name, String workspace_description) {
        this.workspace_name = workspace_name;
        this.workspace_description = workspace_description;
    }

    public WorkspaceModel()
    {

    }

    public Integer getId() {
        return id;
    }

    public String getWorkspace_name() {
        return workspace_name;
    }

    public String getWorkspace_description() {
        return workspace_description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWorkspace_name(String workspace_name) {
        this.workspace_name = workspace_name;
    }

    public void setWorkspace_description(String workspace_description) {
        this.workspace_description = workspace_description;
    }

    public List<BoardModel> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardModel> boards) {
        this.boards = boards;
    }
}
