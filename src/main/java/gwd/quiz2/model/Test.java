package gwd.quiz2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void addQuestion(Question question) {
        question.setTest(this);
        questionList.add(question);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    @OneToMany(mappedBy ="test", cascade = CascadeType.ALL) //tu test bo jest to nawiązanie, nazwa klucza obcego po drugiej stronie
    private List<Question> questionList = new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Test(String name) {
        this.name = name;
    }

    public Test() {    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
