package Group_15.Trello_Project.BoardTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.service.TaskService;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BoardService.class})
@ExtendWith(SpringExtension.class)
public class BoardServiceTests {

    @MockBean
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @MockBean
    private TaskService taskService;


    @Test
    public void createBoardTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        when(boardRepository.save(boardModel)).thenReturn(boardModel);

        BoardModel savedBoard = boardService.createBoard(boardModel);

        assertNotNull(savedBoard);
    }

    @Test
    public void findBoardByIdTest()
    {

        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        when(boardRepository.findById(any())).thenReturn(Optional.of(boardModel));
        BoardModel savedBoard = boardService.findBoardById(boardModel.getId());

        assertNotNull(savedBoard);

    }

    @Test
    public void deleteBoardTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        boardRepository.deleteById(boardModel.getId());

        boolean savedBoard = boardService.deleteBoard(boardModel.getId());

        assertTrue(true);
    }

    @Test
    public void getAllBoardsTest()
    {
        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        List<BoardModel> boardModelList = new ArrayList<>();
        boardModelList.add(boardModel);

        List<BoardModel> resultList = boardService.getAllBoards();

        assertNotNull(resultList);

    }

    @Test
    public void getBoardTest()
    {

        BoardModel boardModel = new BoardModel();

        boardModel.setBoard_name("This is Test Board Name");
        boardModel.setBoard_description("This is Test Board Description");

        when(boardRepository.findById(any())).thenReturn(Optional.of(boardModel));

        BoardModel savedBoard = boardService.getBoard(boardModel.getId());

        assertNotNull(savedBoard);

    }

    @Test
    public void addTaskToBoardTest()
    {

        BoardModel boardModel = new BoardModel("Test Board","Test Board Description");

        TaskModel taskModel = new TaskModel("Test Task Name", LocalDate.now(),"Done");

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(any())).thenReturn(Optional.of(boardModel));
        when(taskService.findTaskById(any())).thenReturn(taskModel);

        boolean addTaskToBoard = boardService.addTaskToBoard(boardModel.getId(), taskModel.getId());

        assertTrue(addTaskToBoard);
    }
}

