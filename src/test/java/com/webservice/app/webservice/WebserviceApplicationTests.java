package com.webservice.app.webservice;

import com.webservice.app.webservice.model.JwtUser;
import com.webservice.app.webservice.model.User;
import com.webservice.app.webservice.repository.AdminRepository;
import com.webservice.app.webservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebserviceApplicationTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Autowired
    private MockMvc mock;

    @Autowired
    UserRepository repository;

    @Autowired
    AdminRepository adminRepository;


    private static String token;

    @Before
    public void setUp() throws Exception {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername("valeanstefan");
        jwtUser.setRole("administrator");
        adminRepository.save(jwtUser);

        MvcResult result = mock.perform(post("/token").contentType(MediaType.APPLICATION_JSON).content(jwtUser.toString()).accept(MediaType.APPLICATION_JSON)).andReturn();
        token = result.getResponse().getContentAsString();
    }


    @Test
    public void findAllRetrieveJsonError() throws Exception {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Jwt Token is missing");

        User user = new User("Stefan", "Valean", 23);
        repository.save(user);
        mock.perform(get("/rest/users/all"));

    }

    @Test
    public void findAllRetrieveJson() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        mock.perform(get("/rest/users/all")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

}
