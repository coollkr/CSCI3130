package Group_15.Trello_Project.board.entity;

import Group_15.Trello_Project.task.entity.TaskModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class BoardModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String board_name;

    private String board_description;

    @OneToMany(targetEntity = TaskModel.class)
    @JoinColumn(name="task_mapping")
    private List<TaskModel> tasks;

    public BoardModel(String board_name, String board_description) {
        this.board_name = board_name;
        this.board_description = board_description;
    }

    public BoardModel()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getBoard_description() {
        return board_description;
    }

    public void setBoard_description(String board_description) {
        this.board_description = board_description;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}
