package com.interviewmaster.Service;

import com.interviewmaster.Dao.CategoryRepository;
import com.interviewmaster.Dao.PreparationRepo;
import com.interviewmaster.Model.Category;
import com.interviewmaster.Model.Preparation;
import com.interviewmaster.Payload.CategoryDto;
import com.interviewmaster.Payload.PreparationDto;
import com.interviewmaster.Service.Impl.PreparationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class)  // To Enable All Mockito Annotations
public class PreparationServiceImplTest {

    AutoCloseable autoCloseable;

    @Mock
    private PreparationRepo prepRepo;

    @Mock
    private CategoryRepository catRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    PreparationServiceImpl preparationServ;

    private List<Preparation> allQuestions;

    private Preparation preparation1;
    private Preparation preparation2;

    private PreparationDto preparationDto1;

    private PreparationDto preparationDto2;

    private Category category;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this); // This is to enable all the Mockito annotations

        allQuestions = new ArrayList<>();

        category = new Category(1, "Java", "All Java Questions", null);

        preparation1 = new Preparation(1, "What is JPA",
                "<ul><li><b>JPA (Java Persistence API)</b> is a interface which used to simplifies the interaction between java and database.</li> " +
                        "<li>It allows the java developers to work with database without writing complex SQL queries.</li> <li>JPA support multiple " +
                        "operations like <u>fetching, adding, updating and deleting</u> the data from the database</li> <li>So we can say that JPA is a " +
                        "bridge that connects the Java Code and Database.</li></ul>", "Not Attempted", category);

        preparationDto1 = new PreparationDto(1, "What is JPA",
                "<ul><li><b>JPA (Java Persistence API)</b> is a interface which used to simplifies the interaction between java and database.</li> " +
                        "<li>It allows the java developers to work with database without writing complex SQL queries.</li> <li>JPA support multiple " +
                        "operations like <u>fetching, adding, updating and deleting</u> the data from the database</li> <li>So we can say that JPA is a " +
                        "bridge that connects the Java Code and Database.</li></ul>", "Not Attempted", category);

        preparation2 = new Preparation(2, "What is the difference between LIST and SET", "<table> <thead> <tr> " +
                "<th style=\"background-color: rgb(243, 243, 243); text-align: center;\">LIST</th> <th style=\"background-color: rgb(243, 243, 243); " +
                "text-align: center;\">SET</th> </tr> </thead> <tbody> <tr> <td>The list implementation allows us to add the same or duplicate elements.</td> " +
                "<td>The set implementation doesn't allow us to add the same or duplicate elements.</td> </tr> <tr> <td>In List insertion order is maintained.</td> " +
                "<td>In Set insertion order is not maintained.</td> </tr> <tr> <td>List allows us to add any number of null values.</td> <td>Set allows us to add at " +
                "least one null value in it.</td> </tr> <tr> <td>The List implementation classes are LinkedList and ArrayList.</td> <td>The Set implementation classes " +
                "are TreeSet, HashSet and LinkedHashSet.</td> </tr> <tr> <td>We can get the element of a specified index from the list using the get() method.</td> " +
                "<td>We cannot find the element from the Set based on the index because it doesn't provide any get() method.</td> </tr> </tbody> </table> ",
                "Not Attempted", category);

        preparationDto2 = new PreparationDto(2, "What is the difference between LIST and SET", "<table> <thead> <tr> " +
                "<th style=\"background-color: rgb(243, 243, 243); text-align: center;\">LIST</th> <th style=\"background-color: rgb(243, 243, 243); " +
                "text-align: center;\">SET</th> </tr> </thead> <tbody> <tr> <td>The list implementation allows us to add the same or duplicate elements.</td> " +
                "<td>The set implementation doesn't allow us to add the same or duplicate elements.</td> </tr> <tr> <td>In List insertion order is maintained.</td> " +
                "<td>In Set insertion order is not maintained.</td> </tr> <tr> <td>List allows us to add any number of null values.</td> <td>Set allows us to add at " +
                "least one null value in it.</td> </tr> <tr> <td>The List implementation classes are LinkedList and ArrayList.</td> <td>The Set implementation classes " +
                "are TreeSet, HashSet and LinkedHashSet.</td> </tr> <tr> <td>We can get the element of a specified index from the list using the get() method.</td> " +
                "<td>We cannot find the element from the Set based on the index because it doesn't provide any get() method.</td> </tr> </tbody> </table> ",
                "Not Attempted", category);

        allQuestions.add(preparation1);
        allQuestions.add(preparation2);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
        Mockito.reset(prepRepo);
    }


    @Test
    public void testGetAllQuestions() {
        when(prepRepo.findAll()).thenReturn(this.allQuestions);
        when(modelMapper.map(any(Preparation.class), eq(PreparationDto.class))).thenReturn(preparationDto1);


        assertThat(this.preparationServ.getAllQuestions().get(0).getId()).isEqualTo(this.allQuestions.get(0).getId());
        assertThat(this.preparationServ.getAllQuestions().get(0).getQuestion()).isEqualTo(this.allQuestions.get(0).getQuestion());
        assertThat(this.preparationServ.getAllQuestions().size()).isEqualTo(this.allQuestions.size());
    }

    @Test
    public void testGetQuestionById() {
        when(this.prepRepo.findById(preparation1.getId())).thenReturn(Optional.of(preparation1));
        when(modelMapper.map(any(Preparation.class), eq(PreparationDto.class))).thenReturn(preparationDto1);

        assertThat(this.preparationServ.getQuestionById(1).getQuestion()).isEqualTo(this.allQuestions.get(0).getQuestion());
        assertThat(this.preparationServ.getQuestionById(1).getId()).isEqualTo(this.allQuestions.get(0).getId());
    }

    @Test
    public void testAddNewQuestion() {
        when(this.catRepo.findById(preparation1.getId())).thenReturn(Optional.of(category));
        when(this.modelMapper.map(any(PreparationDto.class), eq(Preparation.class))).thenReturn(preparation1);
        when(this.modelMapper.map(any(Preparation.class), eq(PreparationDto.class))).thenReturn(preparationDto1);
        when(this.prepRepo.save(preparation1)).thenReturn(preparation1);

        assertThat(this.preparationServ.addNewQuestion(preparationDto1, 1).getQuestion()).isEqualTo(preparation1.getQuestion());
        assertThat(this.preparationServ.addNewQuestion(preparationDto1, 1).getId()).isEqualTo(preparation1.getId());
    }

    @Test
    public void testUpdateQuestion() {
        when(this.prepRepo.findById(preparation1.getId())).thenReturn(Optional.of(preparation1));
        when(this.prepRepo.save(preparation1)).thenReturn(preparation1);
        when(this.modelMapper.map(preparation1, PreparationDto.class)).thenReturn(preparationDto2);

        assertThat(this.preparationServ.updateQuestion(1, preparationDto2).getQuestion()).isEqualTo(preparationDto2.getQuestion());
        assertThat(this.preparationServ.updateQuestion(1, preparationDto2).getId()).isEqualTo(preparationDto2.getId());
    }

    @Test
    public void testDeleteQuestion() {
        when(this.prepRepo.findById(preparation1.getId())).thenReturn(Optional.of(preparation1));

        doNothing().when(this.prepRepo).delete(any(Preparation.class));
        this.preparationServ.deleteQuestion(1);
    }

    @Test
    public void testGetAllQuestionsByCategory() {
        when(this.catRepo.findById(1)).thenReturn(Optional.of(category));
        when(this.prepRepo.findByCategory(category)).thenReturn(allQuestions);
        when(this.modelMapper.map(preparation1, PreparationDto.class)).thenReturn(preparationDto1);

        assertThat(this.preparationServ.getAllQuestionsByCategory(1).get(0).getQuestion()).isEqualTo(preparation1.getQuestion());
        assertThat(this.preparationServ.getAllQuestionsByCategory(1).get(0).getAnswer()).isEqualTo(allQuestions.get(0).getAnswer());
        assertThat(this.preparationServ.getAllQuestionsByCategory(1).size()).isEqualTo(allQuestions.size());
    }

    @Test
    public void testGetRandomQuestionById() {
        when(this.prepRepo.count()).thenReturn(1L);
        when(this.prepRepo.findById(1)).thenReturn(Optional.of(preparation1));
        when(this.modelMapper.map(preparation1, PreparationDto.class)).thenReturn(preparationDto1);

        assertThat(this.preparationServ.getRandomQuestionById().getId()).isEqualTo(preparation1.getId());
        assertThat(this.preparationServ.getRandomQuestionById().getQuestion()).isEqualTo(preparation1.getQuestion());
        assertThat(this.preparationServ.getRandomQuestionById().getCategory()).isEqualTo(preparation1.getCategory());
        assertThat(this.preparationServ.getRandomQuestionById().getAnswer()).isEqualTo(preparation1.getAnswer());
    }
}
