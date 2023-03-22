package com.nikolaslouret.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolaslouret.todosimple.models.Task;
import com.nikolaslouret.todosimple.models.User;
import com.nikolaslouret.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo:" + Task.class.getName()));
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());

        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);

        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());

        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            new RuntimeException("Não é possível excluir o usuário porque há entidades relacionadas!");
        }
    }
    
    public List<Task> findAllByUserId(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }
}
