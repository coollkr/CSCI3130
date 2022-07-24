package Group_15.Trello_Project.user.entity;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;

import javax.persistence.*;
import java.util.List;
import java.util.List;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String securityAnswer;

    @ManyToMany(targetEntity = WorkspaceModel.class)
    @JoinColumn(name = "Workspace_Users")
    private List<WorkspaceModel> workspaces;

    @OneToMany(targetEntity = TaskModel.class)
    @JoinColumn(name = "user_tasks")
    private List<TaskModel> tasks;    

    public UserModel(String firstName, String lastName, String email, String password, String securityAnswer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.securityAnswer = securityAnswer;
    }

    public UserModel() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<WorkspaceModel> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorkspaceModel> workspaces) {
        this.workspaces = workspaces;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityQAnswer) {
        this.securityAnswer = securityQAnswer;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}

