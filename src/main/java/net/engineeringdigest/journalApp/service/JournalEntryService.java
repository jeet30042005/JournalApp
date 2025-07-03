package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// yaha pe hum apna business logic likhte he
// controller sirf endpoints banayega and services ko call karega
// controller --> services --> repository
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserServices userServices;

    @Transactional
    public void SaveEntries(JournalEntry journalEntry, String userName) {
        try {
            User user = userServices.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }
    public void saveEntries(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userServices.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userServices.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
}
