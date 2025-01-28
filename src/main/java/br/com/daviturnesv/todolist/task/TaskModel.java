package br.com.daviturnesv.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

    /**
     * Caracteristicas de um Tarefa:
     * 
     * ID
     * Usuario (ID_USUARIO)
     * Descriçao
     * Titulo
     * Data de inicio
     * Data de termino
     * Prioridade
     * 
     */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 40)
    private String title;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 40){
            throw new Exception("O campo 'title' deve conter no maximo 40 caracteres");
        }
        this.title = title;
    }

}
