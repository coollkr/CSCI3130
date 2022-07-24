package Group_15.Trello_Project.board.service;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.repository.BoardRepository;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TaskService taskService;

    public BoardModel createBoard(BoardModel boardModel) {
        return boardRepository.save(boardModel);
    }

    public BoardModel findBoardById(Integer board_id)
    {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent())
        {
            boardModel = optionalBoardModel.get();
        }

        return boardModel;
    }

    public boolean deleteBoard(@PathVariable Integer board_id)
    {
        boardRepository.deleteById(board_id);
        return true;
    }

    public List<BoardModel> getAllBoards()
    {
        return boardRepository.findAll();
    }


    public BoardModel getBoard(@PathVariable Integer board_id)
    {
        BoardModel boardModel = null;
        Optional<BoardModel> board = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(board_id);

        if(optionalBoardModel.isPresent()) {

            boardModel = optionalBoardModel.get();
        }


        return boardModel;

    }

    public boolean addTaskToBoard(Integer board_id, Integer task_id)
    {
        BoardModel updatedBoard = null;
        Optional<BoardModel> board = null;
        try
        {
            board = boardRepository.findById(board_id);
            if(board.isPresent())
            {
                BoardModel boardModel = board.get();
                TaskModel taskModel = taskService.findTaskById(task_id);

                List<TaskModel> tasks = boardModel.getTasks();

                if(tasks==null)
                {
                    tasks = new ArrayList<>();
                }

                tasks.add(taskModel);
                boardModel.setTasks(tasks);

                updatedBoard = boardRepository.save(boardModel);
                return true;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }

}

