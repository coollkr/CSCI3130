package Group_15.Trello_Project.TaskTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.repository.TaskRepository;
import Group_15.Trello_Project.task.service.TaskService;
import Group_15.Trello_Project.user.entity.UserModel;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.config.Task;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
@ContextConfiguration(classes = {TaskService.class})
@ExtendWith(SpringExtension.class)
public class TaskServiceTests {

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @MockBean
    private BoardService boardService;

    @MockBean
    private UserServiceImplementation userService;

    private TaskModel taskModel;
    private Calendar cal;
    private LocalDate date1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //create date
        cal = Calendar.getInstance();
        cal.set(2022, 07, 20);
//        date1 = cal.getTime();
        //create task model
        taskModel = new TaskModel("name1", date1, "To-Do");
        taskModel.setId(2);
    }

    @AfterEach
    public void cleanUp(){
        taskRepository.deleteAll();
    }

        //createTasks
    //successful create
    @Test
    public void createTasks_success(){
        //mock taskRepo.save
        Mockito.when(taskRepository.save(taskModel)).thenReturn(taskModel);
        TaskModel savedTask = taskService.createTask(taskModel, 1);
        //assert it returns a nonNull taskModel
        assertNotNull(savedTask);
    }



        //findByTaskId
    //successful, task id exists
    @Test
    public void findTaskById_successful(){
        Integer task_id = taskModel.getId();
        Optional<TaskModel> task = Optional.of(taskModel);
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(task);
        TaskModel returnedTask = taskService.findTaskById(task_id);
        assertNotNull(returnedTask);
    }

    //unsuccessful, task doesn't exist
    @Test
    public void findTaskById_Unsuccessful(){
        Integer task_id = taskModel.getId();
        Optional<TaskModel> task = Optional.empty();
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(task);
        TaskModel returnedTask = taskService.findTaskById(task_id);
        assertNull(returnedTask);
    }

        //getTaskWithStatus
    //successful
    @Test
    public void getTaskWithStatus_successful(){
        Integer board_id = 2;
        String status = "To-Do";
        BoardModel boardModel = new BoardModel("testBoard", "board for tests");
        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);
        List<TaskModel> returnedTasks = new ArrayList<>();
        assertNotNull(returnedTasks);
    }

    //unsuccessful, board ID doesn't exist
    @Test
    public void getTasksWithStatus_unsuccessful(){
        Integer board_id = 2;
        String status = "To-Do";
        BoardModel boardModel = null;
        //Optional<BoardModel> board = Optional.of(boardModel);
        //when(boardService.findBoardById(anyInt())).thenReturn(boardModel);
        //boardModel.setTasks(null);
        List<TaskModel> returnedTasks = new ArrayList<>();
        taskService.getTaskWithStatus(2, status);
        assertEquals(0, returnedTasks.size());
    }

        //getTaskWithDate
    //successful
    @Test
    public void getTaskWithDate_success(){
      Integer board_id = 2;
      String status = "To-Do";
      LocalDate date1 = taskModel.getDate();
      BoardModel tempBoard = new BoardModel();
      tempBoard.setId(board_id);
      //boardService.addTaskToBoard(2, taskModel.getId());
      //when(boardService.findBoardById(anyInt())).thenReturn(tempBoard);
      List<TaskModel> returnedTasks = taskService.getTaskWithDate(2, status, "Today");
      assertNotNull(returnedTasks);
  }

    //unsuccessful, board id doesn't exist
    @Test
    public void getTaskWithDate_failureBoardIdNonExistant(){
        BoardModel nullBoard = null;
        //when(boardService.findBoardById(anyInt())).thenReturn(nullBoard);
        List<TaskModel> returnedTasks = taskService.getTaskWithDate(2, "Today", "02/04/2022");
        assertEquals(0, returnedTasks.size());
    }

        //updateStatus
    //successful, task id is present
    @Test
    public void updateStatusWithDate_success(){
        String newStatus = "Doing";
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.ofNullable(taskModel));
        TaskModel returnedTask = taskService.updateStatus(taskModel.getId(), newStatus);
        assertEquals(newStatus, returnedTask.getStatus());
    }

    //unsuccessful, task id isn't present
    @Test
    public void updateStatusWithDate_failureBadTaskId(){
        TaskModel nullTask = null;
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.empty());
        TaskModel returnedTask = taskService.updateStatus(10, "Doing");
        assertNull(returnedTask);
    }


    
        //assignUserToTask
    //assignUserToTask_Successful
    @Test
    public void assignUserToTask_successful(){
        UserModel user = new UserModel("jane", "doe", "email", "password", "sec ans");
        user.setId(1);
        when(userService.isUserInWorkspace(anyString(), anyInt())).thenReturn(true);
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.of(taskModel));
        TaskModel returnedTask = taskService.assignUserToTask(taskModel.getId(), "email", 2);
        assertNotNull(returnedTask);
    }

    //assignUserToTask_failUserNotInWorkspace
    @Test
    public void assignUserToTask_failUserNotInWorkspace(){
        UserModel user = new UserModel("jane", "doe", "email", "password", "sec ans");
        user.setId(1);
        Mockito.when(taskRepository.findById(anyInt())).thenReturn(Optional.of(taskModel));
        TaskModel returnedTask = taskService.assignUserToTask(taskModel.getId(), "email", 2);
        assertNull(returnedTask);
    }


        //retrieveTaskAssignee
    //retrieveTaskAssignee_success
    @Test
    public void retrieveTaskAssignee_success(){
        UserModel user = new UserModel("jane", "doe", "email", "password", "sec ans");
        user.setId(1);
        Mockito.when(taskRepository.findUserByTask(anyInt())).thenReturn(Optional.of(1));
        when(userService.getFullName(anyInt())).thenReturn("jane doe");
        String name = taskService.retrieveTaskAssignee(2);
        assertEquals("jane doe", name);
    }

    //retrieveTaskAssignee_failureUserNotPresent
    @Test
    public void retrieveTaskAssignee_failureUserNotPresent(){
        Mockito.when(taskRepository.findUserByTask(anyInt())).thenReturn(Optional.empty());
        //when(userService.getFullName(anyInt())).thenReturn("jane doe");
        String name = taskService.retrieveTaskAssignee(2);
        assertEquals("", name);
    }

    //SearchTask
    @Test
    public void searchTaskTest()
    {

        BoardModel boardModel = new BoardModel("Test Board","Test Board Description");

        TaskModel taskModel = new TaskModel("Test Task Name",LocalDate.now(),"Done");

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);

        when(boardService.findBoardById(any())).thenReturn(boardModel);

        List<TaskModel> savedTaskList = taskService.searchTask(boardModel.getId(), "Test Task Name","Done");

        assertEquals(1,savedTaskList.size());
    }

    @Test
    public void getTaskWithTodayFilterTest()
    {

        BoardModel boardModel = new BoardModel("Test Board","Test Board Description");

        TaskModel taskModel = new TaskModel("Test Task Name",LocalDate.now(),"Done");

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);

        when(boardService.findBoardById(any())).thenReturn(boardModel);

        List<TaskModel> savedTaskList = taskService.getTaskWithDate(boardModel.getId(), "Done","Today");

        assertEquals(1,savedTaskList.size());
    }

    @Test
    public void getTaskWithWeekFilterTest()
    {

        BoardModel boardModel = new BoardModel("Test Board","Test Board Description");

        TaskModel taskModel = new TaskModel("Test Task Name",LocalDate.now(),"Done");

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);

        when(boardService.findBoardById(any())).thenReturn(boardModel);

        List<TaskModel> savedTaskList = taskService.getTaskWithDate(boardModel.getId(), "Done","Week");

        assertEquals(1,savedTaskList.size());
    }

    @Test
    public void getTaskWithOverdueFilterTest()
    {

        BoardModel boardModel = new BoardModel("Test Board","Test Board Description");

        TaskModel taskModel = new TaskModel("Test Task Name",LocalDate.now(),"Done");

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);

        when(boardService.findBoardById(any())).thenReturn(boardModel);

        List<TaskModel> savedTaskList = taskService.getTaskWithDate(boardModel.getId(), "Done","Overdue");

        assertEquals(0,savedTaskList.size());
    }

}
