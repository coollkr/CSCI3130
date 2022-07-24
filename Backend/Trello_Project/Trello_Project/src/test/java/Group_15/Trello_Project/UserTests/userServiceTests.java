package Group_15.Trello_Project.UserTests;

import Group_15.Trello_Project.*;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.repository.UserRepository;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


/*
    CITATION: Mockito.org
    Date Accessed:June 25th
    https://site.mockito.org/
 */

@ExtendWith(MockitoExtension.class)
public class userServiceTests {
    @Mock
    @Autowired
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImplementation userService = new UserServiceImplementation();
    //private UserModel userModel;  DEMO says we don't need it
    private Calendar cal;
    private LocalDate date1;
    private HashMap<String, String> map = new HashMap<>();
    @BeforeEach
    public void setUp() throws EmailAlreadyRegisteredException {
        //unsure what to put here, or if it's even needed
        MockitoAnnotations.initMocks(this);
        cal = Calendar.getInstance();
        cal.set(2022, 07, 20);
    }

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
        map.clear();
    }



        //SIGNUP
    //check saved user is not null after successful signup
    @Test
    public void testSignUp_successfulSignUpIDIsCorrect() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        when(userRepository.save(userModel)).thenReturn(userModel);
        map = userService.signUpUser(userModel);

        assertEquals(map.get("result"), "1");
    }

    //Check EmailAlreadyRegisteredException is thrown when user signs up with email already in db
    @Test
    public void testSignUp_throwsEmailAlreadyRegisteredException() throws EmailAlreadyRegisteredException{
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        map = userService.signUpUser(userModel);
        assertEquals("Email Already Registered", map.get("status"));
    }

    //check -1 is returned when signup uses email already in DB
    @Test
    public void testSignUp_emailAlreadyPresentInDB() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        map = userService.signUpUser(userModel);
        assertEquals("-1", map.get("result"));
    }



        //LOGIN
    //successful
    @Test
    public void testLogin_successfulLogin() throws EmailAlreadyRegisteredException, IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email@provider", "secret");
        assertEquals("1", map.get("result"));
    }

    //check EmailNotRegisteredException is thrown when logging in user with wrong email
    @Test
    public void testLogin_throwsEmailNotRegisteredException() throws EmailNotRegisteredException, IncorrectPasswordException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("student@dal", "yellow");
        assertEquals("Email Not Registered", map.get("status"));
        //assertThrows(EmailNotRegisteredException.class, ()->userService.logInUser("student@dal", "password1"));
    }

    //check -1 is returned when input email is not in database
    @Test
    public void testLogin_incorrectEmail() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("jane", "doe", "email@provider", "secret", "yellow");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("student@dal", "yellow");
        assertEquals("-1", map.get("result"));
    }

    //check IncorrectPasswordException is thrown when logging in a user with an incorrect pw
    @Test
    public void testLogin_throwsIncorrectPasswordException() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email1", "wrongPassword");
        assertEquals("Incorrect Password", map.get("status"));
        //assertThrows(IncorrectPasswordException.class, ()->userService.logInUser("email1", "password2"));
    }

    //check -1 is returned when user logs in with correct email, but incorrect password
    @Test
    public void testLogin_incorrectPassword() throws IncorrectPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.logInUser("email1", "wrongPassword");
        assertEquals("-1", map.get("result"));
    }




        //FORGOT PW
    //check that userModel.pw == new password after update pw is called
    @Test
    public void testForgotPW_successfulPasswordUpdate() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "newPassword");
        assertEquals("newPassword", userModel.getPassword());
    }

    //check that EmailNotRegisteredException is thrown when user tries to reset pw with an unregistered email
    @Test
    public void testForgotPW_throwsEmailNotRegisteredException() throws EmailNotRegisteredException, NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email2", "answer1", "password1");
        assertEquals("Email Not Registered", map.get("status") );
        //assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword("email2", "answer1", "password1"));
    }

    //check that false is returned when user tries to reset pw with an email not registered
    @Test
    public void testForgotPW_emailNotInDB() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email2", "answer1", "password1");
        assertEquals( "Email Not Registered", map.get("status"));
    }

    //check IncorrectSecurityAnswerException is thrown when user tries to reset pw with wrong security Q answer
    @Test
    public void testUpdatePW_throwsIncorrectSecurityAnswerException() throws IncorrectSecurityAnswerException, EmailAlreadyRegisteredException, NewPasswordSameAsOldPasswordException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "wrongAnswer", "newPassword");
        assertEquals("Incorrect Security Answer", map.get("status"));
        //assertThrows(IncorrectSecurityAnswerException.class, ()->userService.updatePassword("email1", "answer2", "password1"));
    }

    //check that when user uses incorrect security answer to reset pw it returns false
    @Test
    public void testForgotPW_wrongSecurityAnswer() throws NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "wrongAnswer", "newPassword");
        assertEquals("false", map.get("result"));
    }

    //check NewPasswordSameAsOldPasswordException is thrown when suer resets pw and new PW is same as old
    @Test
    public void testForgotPW_throwsNewPasswordSameAsOldPasswordException() throws NewPasswordSameAsOldPasswordException, EmailAlreadyRegisteredException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "password1");
        assertEquals("New Password Same As Old Password", map.get("status"));
        //assertThrows(NewPasswordSameAsOldPasswordException.class, ()->userService.updatePassword("email1", "answer1", "password1"));
    }

    //check that false is returned when new pw == old pw
    @Test
    public void testForgotPW_newPasswordSameAsOld() throws EmailAlreadyRegisteredException, NewPasswordSameAsOldPasswordException, IncorrectSecurityAnswerException, EmailNotRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        map = userService.updatePassword("email1", "answer1", "password1");
        assertEquals("false", map.get("result"));
    }


        //find by user ID
    // 1 - userModel is returned when user is present
    @Test
    public void testFindByUserId_userIsPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        UserModel returnedUserModel = userService.findUserByID(1);
        assertNotNull(returnedUserModel);
    }

    //2 - null / empty userModel is returned when user isn't present
    @Test
    public void testFindById_userNotPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        UserModel returnedUserModel = userService.findUserByID(1);
        assertNull(returnedUserModel);
        //not sure if an empty object is null...
    }


        //addWorkspaceToUserById
    //successful
    @Test
    public void testAddUserToWorkspaceById_successful() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserById(1, workspace);
        assertTrue(result);
    }

    //fails-user not in database
    @Test
    public void testAddUserToWorkspaceById_userNotPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserById(1, workspace);
        assertFalse(result);
    }

    //fails-user already in workspace
    @Test
    public void testAddUserToWorkspaceById_workspaceIsNull() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserById(1, workspace);
        result = userService.addWorkspaceToUserById(1, workspace);
        assertFalse(result);
    }


        //addWorkspaceToUserByEmail
    // check it is successful
    @Test
    public void testAddUserToWorkspaceByEmail_successfulAdd() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserByEmail("email1", workspace);
        assertTrue(result);
    }

    // check addUserToWorkspaceByEmail fails - user isn't present
    @Test
    public void testAddUserToWorkspaceByEmail_userNotPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserByEmail("email1", workspace);
        assertFalse(result);
    }

    // check addUserToWorkspaceByEmail fails - user already has workspace in list, returns false
    @Test
    public void testAddUserToWorkspaceByEmail_workspaceIsNull() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("test", "test");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail( anyString() )).thenReturn(user);
        boolean result = userService.addWorkspaceToUserByEmail("email1", workspace);
        result = userService.addWorkspaceToUserByEmail("email1", workspace);
        assertFalse(result);
    }


        //getAllWorkspaces
    //check it is successful
    @Test
    public void testGetAllWorkspaces_Success() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("name", "description");
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUserById(1, workspace);
        List<WorkspaceModel> workspaces = userService.getAllWorkspaces(1);
        assertNotEquals(null, workspaces);
    }

    // check get all workspaces fails - user isn't present returns null
    @Test
    public void testGetAllWorkspaces_failsUserNotPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        WorkspaceModel workspace = new WorkspaceModel("name", "description");
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        userService.addWorkspaceToUserById(1, workspace);
        List<WorkspaceModel> workspaces = userService.getAllWorkspaces(1);
        assertEquals(null, workspaces);
    }

    //check getAllWorkspaces returns null when no workspaces are present
    @Test
    public void testGetAllWorkspaces_failsNoWorkspacesPresent() throws EmailAlreadyRegisteredException {
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById( anyInt() )).thenReturn(user);
        List<WorkspaceModel> workspaces = userService.getAllWorkspaces(1);
        assertEquals(null, workspaces);
    }


        //addTaskToUser
    //addTaskToUser_Successful
    @Test
    public void addTaskToUser_Successful() throws EmailAlreadyRegisteredException{
        TaskModel taskModel = new TaskModel("name1", date1, "To-Do");
        taskModel.setId(2);
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        List<TaskModel> tasks = new ArrayList<>();
        userModel.setTasks(tasks);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        boolean outcome = userService.addTaskToUser(taskModel, "email1");
        assertTrue(outcome);
    }
    
    //addTaskToUser_fail-userNotPresent
    @Test
    public void addTaskToUser_fail_UserNotPresent() throws EmailAlreadyRegisteredException{
        TaskModel taskModel = new TaskModel("name1", date1, "To-Do");
        taskModel.setId(2);
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        boolean outcome = userService.addTaskToUser(taskModel, "email1");
        assertFalse(outcome);
    }


        //getFullName
    //getFullName_success
    @Test
    public void getFullName_success() throws EmailAlreadyRegisteredException{
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findById(anyInt())).thenReturn(user);
        String returnedFullName = userService.getFullName(1);
        assertEquals("fName1 lName", returnedFullName);
        
    }

    //getFullName_fail_UserNotPresent
    @Test
    public void getFullName_fail_UserNotPresent() throws EmailAlreadyRegisteredException{
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findById(anyInt())).thenReturn(user);
        String returnedFullName = userService.getFullName(1);
        assertEquals("", returnedFullName);
        
    }


        //isUserInWorkspace
    //isUserInWorkspace_success
    @Test
    public void isUserInWorkspace_success() throws EmailAlreadyRegisteredException{
        WorkspaceModel workspaceModel = new WorkspaceModel("testWorkspaceName", "testWorkspaceDesc");
        workspaceModel.setId(1);
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        List<WorkspaceModel> workspaces = new ArrayList<>();
        workspaces.add(workspaceModel);
        userModel.setWorkspaces(workspaces);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        boolean result = userService.isUserInWorkspace("email1", 1);
        assertTrue(result);
    }
    
    //isUserInWorkspace_fail_userNotPresent
    @Test
    public void isUserInWorkspace_fail_userNotPresent() throws EmailAlreadyRegisteredException{
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        boolean result = userService.isUserInWorkspace("email1", 1);
        assertFalse(result);
    }

    
        //getFullNameByEmail
    //getFullNameByEmail_success
    @Test
    public void getFullNameByEmail_success() throws EmailAlreadyRegisteredException{
        UserModel userModel = new UserModel("fName1", "lName", "email1", "password1", "answer1");
        userModel.setId(1);
        userService.signUpUser(userModel);
        Optional<UserModel> user = Optional.of(userModel);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        String returnedFullName = userService.getFullNameByEmail("email1");
        assertEquals("fName1 lName", returnedFullName);
        
    }

    //getFullNameByEmail_fail_userNotPresent
    @Test
    public void getFullNameByEmail_fail_userNotPresent() throws EmailAlreadyRegisteredException{
        Optional<UserModel> user = Optional.empty();
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user);
        String returnedFullName = userService.getFullNameByEmail("email1");
        assertEquals("", returnedFullName);
        
    }
}
