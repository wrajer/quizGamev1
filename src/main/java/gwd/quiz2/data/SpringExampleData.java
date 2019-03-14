package gwd.quiz2.data;

import gwd.quiz2.model.Question;
import gwd.quiz2.model.Test;
import gwd.quiz2.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringExampleData implements CommandLineRunner {//implementacja oznacza że bedzie an to ładowac przy starcie, to jest taki main

    @Autowired // może być bez Autowired ale wtedy sami tworzymy obiekt i dodatkowo metody save itp
    private TestRepository testRepository;

    @Override
    public void run(String... args) throws Exception { //... kropi oznacza że arguentoem moge być tablica, ponoć łatwiejszy zapis
        System.out.println("Start z Data Kris ");


        Test test = new Test ("Test z wiedzy Java");
        test.addQuestion(new Question("Czy Java ma Klasy?", true));
        test.addQuestion(new Question("Czy Java ma interfejsy?", true));
        test.addQuestion(new Question("Czy Java ma klasę integer?", false));
        test.addQuestion(new Question("Czy Java ma destruktor?", false));
        testRepository.save(test); //przekazanie do bazy danych poczatkowych

        test.addQuestion(new Question("Czy Java ma klasę integer?", false));
        test.addQuestion(new Question("Czy Java ma destruktor?", false));
        testRepository.save(test); //przekazanie do bazy danych poczatkowych

        Test testAniamal = new Test ("Test z wiedzy o zwierzętach");
        testAniamal.addQuestion(new Question("Czy miś ma uszy?", true));
        testAniamal.addQuestion(new Question("Czy list jest kotowatym?", false));
        testAniamal.addQuestion(new Question("Czy miś je granit?", false));
        testRepository.save(testAniamal); //przekazanie do bazy danych poczatkowych

        Test testMath = new Test ("Test umiejętności matematyczych");
        testMath.addQuestion(new Question("Czy 5! to 125?", false));
        testMath.addQuestion(new Question("Czy 154872 jest podzielne na 9?", true));
        testRepository.save(testMath); //przekazanie do bazy danych poczatkowych

    }




}
