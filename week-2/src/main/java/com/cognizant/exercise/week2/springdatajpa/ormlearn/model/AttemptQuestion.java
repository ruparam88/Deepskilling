package com.cognizant.exercise.week2.springdatajpa.ormlearn.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aq_qt_id")
    private Question question;

    @OneToOne(mappedBy = "attemptQuestion", fetch = FetchType.LAZY)
    private AttemptOption attemptOption;

    public AttemptQuestion() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AttemptOption getAttemptOption() {
        return attemptOption;
    }

    public void setAttemptOption(AttemptOption attemptOption) {
        this.attemptOption = attemptOption;
    }
}
