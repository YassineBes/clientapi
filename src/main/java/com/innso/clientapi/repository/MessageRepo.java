package com.innso.clientapi.repository;

import com.innso.clientapi.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message,Long> {
}
