package com.example.api.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.entity.JournalEntry;
import com.example.api.entity.UserEntry;
import com.example.api.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) throws Exception {
        try {
            UserEntry user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getByID(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    @Transactional
    public boolean deleteByID(ObjectId Id, String userName) {
        boolean removed = false;
        try {
            UserEntry user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
            if(removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(Id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error occured while deleting the entry");
        }
        return removed;
    }

    // public List<JournalEntry> findByUserName(String userName) {
        
    // }
}