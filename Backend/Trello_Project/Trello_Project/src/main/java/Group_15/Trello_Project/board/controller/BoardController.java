package Group_15.Trello_Project.board.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/saveBoard", consumes = "application/json", produces = "application/json")
    public BoardModel createBoard(@RequestBody BoardModel boardModel) {
        return boardService.createBoard(boardModel);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteBoard/{board_id}")
    public boolean deleteBoard(@PathVariable Integer board_id)
    {
        return boardService.deleteBoard(board_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllBoards")
    public List<BoardModel> getAllBoards()
    {
        return boardService.getAllBoards();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getBoard/{board_id}")
    public BoardModel getBoard(@PathVariable Integer board_id)
    {
        return boardService.getBoard(board_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/addTask/{board_id}")
    public boolean addTaskToBoard(@PathVariable Integer board_id, @RequestParam Integer task_id)
    {
        return boardService.addTaskToBoard(board_id, task_id);
    }
}


