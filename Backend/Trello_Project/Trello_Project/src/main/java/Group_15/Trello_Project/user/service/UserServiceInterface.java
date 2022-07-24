package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface UserServiceInterface {
    public HashMap<String, String> signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException, EmailAlreadyRegisteredException;

    public HashMap<String, String> updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException;

    public HashMap<String, String> logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException;

    public UserModel findUserByID(Integer userId);

    public boolean addWorkspaceToUserByEmail(String email, WorkspaceModel workspaceModel);

    public boolean addWorkspaceToUserById(Integer id, WorkspaceModel workspaceModel);

    public List<WorkspaceModel> getAllWorkspaces(Integer id);

    public boolean addTaskToUser(TaskModel task, String email);

    public String getFullName(Integer user_id);

    public boolean isUserInWorkspace(String email, Integer workspace_id);

    public String getFullNameByEmail(String email);
}