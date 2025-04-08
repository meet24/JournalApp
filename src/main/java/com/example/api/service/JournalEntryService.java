package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.entity.JournalEntry;
import com.example.api.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getByID(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    public void deleteByID(ObjectId Id) {
        journalEntryRepository.deleteById(Id);
    }
}