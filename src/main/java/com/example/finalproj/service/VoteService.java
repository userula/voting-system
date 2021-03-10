package com.example.finalproj.service;

import com.example.finalproj.repository.VoteRepository;
import com.example.finalproj.repository.dto.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getVotesByUserId(long id) {
        List<Vote> votes = voteRepository.findAllByUserUserId(id);
        for (Vote v : votes) {
            System.out.println(v.getAnswer().getQuestion().getQuestionText());
        }
        return votes;
    }

    public void save(Vote vote) {
        voteRepository.save(vote);
    }
}
