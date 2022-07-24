package Group_15.Trello_Project.task.controller;

import Group_15.Trello_Project.board.entity.BoardModel;
import Group_15.Trello_Project.board.service.BoardService;
import Group_15.Trello_Project.task.entity.TaskModel;
import Group_15.Trello_Project.task.service.TaskService;
import Group_15.Trello_Project.workspace.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/saveTask/{board_id}", consumes = "application/json", produces = "application/json")
    public TaskModel createTask(@RequestBody TaskModel taskModel, @PathVariable Integer board_id) {
        return taskService.createTask(taskModel, board_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTaskWithStatus/{board_id}")
    public List<TaskModel> getTaskWithStatus(@PathVariable Integer board_id, @RequestParam String status)
    {
        return taskService.getTaskWithStatus(board_id, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTaskWithDate/{board_id}")
    public List<TaskModel> getTaskWithDate(@PathVariable Integer board_id, @RequestParam String status, @RequestParam String dateFilter)
    {
        return taskService.getTaskWithDate(board_id, status, dateFilter);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/updateStatus/{task_id}")
    public TaskModel updateStatus(@PathVariable Integer task_id, @RequestParam String status) {

        return taskService.updateStatus(task_id, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/addUserToTask/{task_id}")
    public TaskModel addUserToTask(@PathVariable Integer task_id, @RequestParam String email, @RequestParam Integer workspace_id){
        return taskService.assignUserToTask(task_id, email, workspace_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/searchTask/{board_id}")
    public List<TaskModel> searchTask(@PathVariable Integer board_id, @RequestParam String searchValue, @RequestParam String status){
        return taskService.searchTask(board_id, searchValue, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getTaskUser/{task_id}")
    public String getTaskUser(@PathVariable Integer task_id){
        return taskService.retrieveTaskAssignee(task_id);
    }
}
