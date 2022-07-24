package Group_15.Trello_Project.workspace.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.user.service.UserServiceImplementation;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import Group_15.Trello_Project.workspace.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserServiceImplementation userService;

    public WorkspaceModel createWorkspace(WorkspaceModel workspaceModel, Integer user_id)
    {

        WorkspaceModel workspace = workspaceRepository.save(workspaceModel);

        boolean success = userService.addWorkspaceToUserById(user_id, workspace);

        return workspace;

    }

    public WorkspaceModel addUserToWorkspace(Integer workspace_id, String email)
    {

        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspace_id);
        WorkspaceModel updatedWorkspaceModel =  null;
        boolean success = false;

        if(optionalWorkspaceModel.isPresent())
        {
            updatedWorkspaceModel = optionalWorkspaceModel.get();
            success = userService.addWorkspaceToUserByEmail(email, updatedWorkspaceModel);
        }

        if(success) {
            return updatedWorkspaceModel;
        }

        return null;

    }
    public WorkspaceModel updateBoard(Integer workspace_id, Integer board_id)
    {
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = null;
        try
        {
            workspace = workspaceRepository.findById(workspace_id);
            if(workspace.isPresent())
            {
                WorkspaceModel workspaceModel = workspace.get();
                BoardModel boardModel = boardService.findBoardById(board_id);

                List<BoardModel> boards = workspaceModel.getBoards();

                if(boards==null)
                {
                    boards = new ArrayList<>();
                }

                boards.add(boardModel);
                workspaceModel.setBoards(boards);

                updatedWorkspace = workspaceRepository.save(workspaceModel);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return updatedWorkspace;
    }

    public List<WorkspaceModel> getAllWorkspaces(Integer user_id)
    {
        List<WorkspaceModel> userWorkspaces = userService.getAllWorkspaces(user_id);
        return userWorkspaces;
    }

    public WorkspaceModel getWorkspace(@PathVariable Integer workspace_id)
    {
        WorkspaceModel workspaceModel = null;
        Optional<WorkspaceModel> optionalWorkspaceModel = null;

        try {
            optionalWorkspaceModel = workspaceRepository.findById(workspace_id);
            if (optionalWorkspaceModel.isPresent()) {
                workspaceModel = optionalWorkspaceModel.get();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return workspaceModel;

    }

    public List<BoardModel> getWorkspaceBoards(Integer workspace_id) {

        Optional<WorkspaceModel> workspace = null;
        List<BoardModel> boards = null;

        try {
            workspace = workspaceRepository.findById(workspace_id);
            if (workspace.isPresent()) {
                WorkspaceModel workspaceModel = workspace.get();
                boards = workspaceModel.getBoards();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return boards;
    }

}
