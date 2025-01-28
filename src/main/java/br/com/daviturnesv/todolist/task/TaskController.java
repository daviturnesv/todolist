package br.com.daviturnesv.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var dataAtual = LocalDateTime.now();
        if(dataAtual.isAfter(taskModel.getStartsAt()) || dataAtual.isAfter(taskModel.getEndsAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A Data de Inicio / Data de Termino deve ser maior que a Data Atual");
        }

        if(taskModel.getStartsAt().isAfter(taskModel.getEndsAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A Data de Inicio deve ser anterior a Data de Termino");
        }
        var tarefa = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tarefas = this.taskRepository.findByIdUser((UUID)idUser);
        return tarefas;
    }

    @PutMapping("/{id}")
    public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id,  HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        taskModel.setId(id);
        return this.taskRepository.save(taskModel);
    }

}

