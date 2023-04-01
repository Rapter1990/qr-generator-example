package com.example.qrgeneratorexample.base;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public abstract class BaseControllerTest {
}
