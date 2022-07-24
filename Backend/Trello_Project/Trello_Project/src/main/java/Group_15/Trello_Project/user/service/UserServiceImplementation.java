package Group_15.Trello_Project.user.service;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    public HashMap<String, String> signUpUser(UserModel userModel) throws EmailAlreadyRegisteredException {
        try {
            Optional<UserModel> user = userRepository.findByEmail(userModel.getEmail());
            if (user.isPresent()) {
                throw new EmailAlreadyRegisteredException();
            }
        } catch (EmailAlreadyRegisteredException emailExists) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("result", "-1");
            map.put("status", "Email Already Registered");
            return map;
        }
        userRepository.save(userModel);
        HashMap<String, String> map = new HashMap<String, String>();
        String result = userModel.getId().toString();
        map.put("result", result);
        map.put("status", "successful signup");
        return map;
    }

    public HashMap<String, String> logInUser(String email, String password) throws IncorrectPasswordException, EmailNotRegisteredException {
        Optional<UserModel> user;
        try {
            user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                UserModel userModel = user.get();
                if (password.equals(userModel.getPassword())) {
                    String result = userModel.getId().toString();
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("result", result);
                    map.put("status", "successful login");
                    return map;
                } else {
                    throw new IncorrectPasswordException();
                }
            } else {
                throw new EmailNotRegisteredException();
            }
        } catch (EmailNotRegisteredException badEmail) {
            HashMap<String, String> map = new HashMap<String, String>();
            Integer error = -1;
            String result = error.toString();
            map.put("result", result);
            map.put("status", "Email Not Registered");
            return map;
        } catch (IncorrectPasswordException badPW) {
            HashMap<String, String> map = new HashMap<String, String>();
            Integer error = -1;
            String result = error.toString();
            map.put("result", result);
            map.put("status", "Incorrect Password");
            return map;
        }
    }

    public HashMap<String, String> updatePassword(String email, String securityAnswer, String newPw) throws EmailNotRegisteredException, IncorrectSecurityAnswerException, NewPasswordSameAsOldPasswordException {
        Optional<UserModel> user;
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                UserModel userModel = user.get();

                if (securityAnswer.equals(userModel.getSecurityAnswer())) {

                    if (!newPw.equals(userModel.getPassword())) {
                        userModel.setPassword(newPw);
                        userRepository.save(userModel);
                        map.put("result", userModel.getId().toString());
                        map.put("status", "successful update Password");
                        return map;
                    } else {
                        throw new NewPasswordSameAsOldPasswordException();
                    }
                } else {
                    throw new IncorrectSecurityAnswerException();
                }
            } else {
                throw new EmailNotRegisteredException();
            }
        } catch (EmailNotRegisteredException badEmail) {
            map.put("result", "false");
            map.put("status", "Email Not Registered");
            return map;
            //Not sure what else to do here
        } catch (IncorrectSecurityAnswerException badAnswer) {
            map.put("result", "false");
            map.put("status", "Incorrect Security Answer");
            return map;
            //rlly not sure what else to do
        } catch (NewPasswordSameAsOldPasswordException oldPwNewPwSame) {
            map.put("result", "false");
            map.put("status", "New Password Same As Old Password");
            return map;
        }
    }

    public UserModel findUserByID(Integer userId) {
        UserModel userModel = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if (optionalUserModel.isPresent()) {
            userModel = optionalUserModel.get();
        }
        return userModel;
    }


    public boolean addWorkspaceToUserById(Integer id, WorkspaceModel workspaceModel) {
        //check if user exists, assume workspace has already been error checked
        UserModel userModel;
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            userModel = user.get();
            List<WorkspaceModel> workspaces = userModel.getWorkspaces();
            if (workspaces == null) {
                workspaces = new ArrayList<WorkspaceModel>();
            }

            //check to make sure user isn't already in database
            if (!workspaces.contains(workspaceModel)) {
                workspaces.add(workspaceModel);
                userModel.setWorkspaces(workspaces);
                userRepository.save(userModel);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        //if they do then add workspace to user list
    }

    public boolean addWorkspaceToUserByEmail(String email, WorkspaceModel workspaceModel) {
        //check if user exists, assume workspace has already been error checked
        UserModel userModel;
        Optional<UserModel> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userModel = user.get();
            List<WorkspaceModel> workspaces = userModel.getWorkspaces();
            if (workspaces == null) {
                workspaces = new ArrayList<WorkspaceModel>();
            }

            //check to make sure user isn't already in database
            if (!workspaces.contains(workspaceModel)) {
                workspaces.add(workspaceModel);
                userModel.setWorkspaces(workspaces);
                userRepository.save(userModel);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        //if they do then add workspace to user list
    }


    public List<WorkspaceModel> getAllWorkspaces(Integer id) {
        //check if user exists..
        UserModel userModel = null;
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            userModel = user.get();
            return userModel.getWorkspaces();
        } else {
            return null;
        }
        //then send List<WorkspaceModel>
    }

    public boolean addTaskToUser(TaskModel task, String email){
        UserModel userModel;
        Optional<UserModel> user = userRepository.findByEmail(email);
        boolean success = false;
        if(user.isPresent()){
            userModel = user.get();
            List<TaskModel> tasks = userModel.getTasks();

            if(tasks.isEmpty()){
                 tasks = new ArrayList<TaskModel>();
             }

            if(!tasks.contains(task)){
                tasks.add(task);
                userModel.setTasks(tasks);
                userRepository.save(userModel);
                success = true;
            }
        }
        return success;
    }

    public String getFullName(Integer user_id){
        Optional<UserModel> user = userRepository.findById(user_id);
        if(user.isPresent()){
            UserModel userModel = user.get();
            return ""+userModel.getFirstName()+" "+userModel.getLastName();
        }
        return "";
    }

    public boolean isUserInWorkspace(String email, Integer workspace_id){
        Optional<UserModel> user = userRepository.findByEmail(email);
        Integer id = -1;
        if(user.isPresent()){
            UserModel userModel = user.get();
            List<WorkspaceModel> workspaces = userModel.getWorkspaces();
            if( workspaces == null){
                return false;
            }else{
                for(int i = 0; i <workspaces.size(); i++){
                    id = workspaces.get(i).getId();
                    if(id == workspace_id){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public String getFullNameByEmail(String email){
        Optional<UserModel> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            UserModel userModel = user.get();
            return ""+userModel.getFirstName()+" "+userModel.getLastName();
        }
        return "";
    }

}