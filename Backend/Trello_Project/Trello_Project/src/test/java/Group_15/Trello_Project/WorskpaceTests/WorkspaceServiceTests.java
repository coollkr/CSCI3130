package Group_15.Trello_Project.WorskpaceTests;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.repository.WorkspaceRepository;
import Group_15.Trello_Project.workspace.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WorkspaceService.class})
@ExtendWith(SpringExtension.class)
public class WorkspaceServiceTests {

    @MockBean
    private WorkspaceRepository workspaceRepository;


    @Autowired
    private WorkspaceService workspaceService;

    @MockBean
    private UserServiceImplementation userService;

    @MockBean
    private BoardService boardService;

    @Test
    public void createWorkspaceTest() {

        WorkspaceModel workspace = new WorkspaceModel();
        int mockUserId = 1;

        workspace.setWorkspace_name("New Test Workspace Name Created");
        workspace.setWorkspace_description("This is New Test Workspace Description Created");

        when(userService.addWorkspaceToUserById(any(),any())).thenReturn(true);
        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.createWorkspace(workspace,mockUserId);

        assertNotNull(savedWorkspace);
    }

    @Test
    public void addUserToWorkspaceTest(){
        WorkspaceModel workspace = new WorkspaceModel();

        workspace.setWorkspace_name("Test Workspace Add User Name");
        workspace.setWorkspace_description("This is Test Workspace Add User Description");

        String mockUserEmail = "testuser@gmail.com";

        when(userService.addWorkspaceToUserByEmail(any(),any())).thenReturn(true);
        Mockito.when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));

        WorkspaceModel savedWorkspace = workspaceService.addUserToWorkspace(workspace.getId(),mockUserEmail);

        assertNotNull(savedWorkspace);

    }

    @Test
    public void updateBoardTest() {
        WorkspaceModel workspace = new WorkspaceModel();
        BoardModel board = new BoardModel();

        workspace.setWorkspace_name("Test Workspace Name");
        workspace.setWorkspace_description("This is Test Workspace Description");

        board.setBoard_name("This is Board Name");
        board.setBoard_description("This is Board Description");

        when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));

        when(boardService.findBoardById(any())).thenReturn(board);

        Mockito.when(workspaceRepository.save(workspace)).thenReturn(workspace);

        WorkspaceModel savedWorkspace = workspaceService.updateBoard(workspace.getId(),board.getId());

        assertNotNull(savedWorkspace);
    }

    @Test
    public void getAllWorkspacesTest()
    {
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspace_name("Test Get All Workspace Name");
        workspace.setWorkspace_description("This is Test Get All Workspace Description");

        int mockUserId = 1;
        List<WorkspaceModel> userWorkspaces = new ArrayList<>();
        userWorkspaces.add(workspace);

        when(userService.getAllWorkspaces(any())).thenReturn(userWorkspaces);

        List<WorkspaceModel> resultList = workspaceService.getAllWorkspaces(mockUserId);

        assertNotNull(resultList);
    }

    @Test
    public void getWorkspaceTest()
    {
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspace_name("Test Get Workspace Name");
        workspace.setWorkspace_description("This is Test Get Workspace Description");

        workspaceRepository.save(workspace);
        when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));
        WorkspaceModel savedWorkspace = workspaceService.getWorkspace(workspace.getId());

        assertEquals(workspace, savedWorkspace);

    }

    @Test
    public void getWorkspaceBoardsTest()
    {
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspace_name("Test Get Workspace Boards Name");
        workspace.setWorkspace_description("This is Test Get Workspace Boards Description");

        BoardModel board = new BoardModel();
        board.setBoard_name("This is Board Name");
        board.setBoard_description("This is Board Description");

        List<BoardModel> boardList = new ArrayList<>();
        boardList.add(board);
        workspace.setBoards(boardList);

        Mockito.when(workspaceRepository.findById(any())).thenReturn(Optional.of(workspace));
        List<BoardModel> workspaceBoardList = workspaceService.getWorkspaceBoards(workspace.getId());
        assertEquals(1,workspaceBoardList.toArray().length);
    }

    @Test
    public void getWorkspaceBoardsEmptyExceptionTest()
    {
        WorkspaceModel workspace = new WorkspaceModel();
        workspace.setWorkspace_name("Test Get Workspace Boards Name");
        workspace.setWorkspace_description("This is Test Get Workspace Boards Description");

        BoardModel board = new BoardModel();
        board.setBoard_name("This is Board Name");
        board.setBoard_description("This is Board Description");

        List<BoardModel> boardList = new ArrayList<>();
        boardList.add(board);
        workspace.setBoards(boardList);

        Mockito.when(workspaceRepository.findById(any())).thenReturn(Optional.empty());
        List<BoardModel> workspaceBoardList = workspaceService.getWorkspaceBoards(workspace.getId());
    }

}

