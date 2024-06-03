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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
}
