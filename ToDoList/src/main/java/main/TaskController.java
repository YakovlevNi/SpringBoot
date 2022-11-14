package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    private List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping("/tasks/")
    public int add(@RequestBody Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/tasks/{id}/")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
        //     Task task = Storage.getTask(id);
        //    if (task == null) {
        //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        //    }
        //    return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}/")
    public void delete(@PathVariable int id) {
        taskRepository.deleteById(id);
        // Storage.deleteTask(id);
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@RequestBody Task task, @PathVariable int id, @RequestParam("name") String name) {
        Optional<Task> upgradeTask = taskRepository.findById(id);
        if (upgradeTask.isPresent()) {
            taskRepository.save(task);
        }

        // Storage.upgradeTask(id, task);
    }

}
