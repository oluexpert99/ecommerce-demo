package com.tinybeans.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * User: ayoade_farooq@yahoo.com
 * Date: 09/06/2021
 * Time: 09:10
 */
@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
//@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp(
            WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation) {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .apply(
                                documentationConfiguration(restDocumentation)
                                        .uris()
                                        .withScheme("https")
                                        .withHost("abc.com")
                                        .withPort(443)
                                        .and()
                                        .operationPreprocessors()
                                        .withRequestDefaults(prettyPrint())
                                        .withResponseDefaults(prettyPrint()))
                        .build();
    }

    @Test
    public void testIndexShouldRedirectToProductsPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"));

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
        //
    }
    @Test
    public void testGetProductsPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
        //
    }

    @Test
    public void testGetProductCheckoutPage() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/products/{id}/checkout",1))
                .andExpect(status().isOk())
                .andExpect(view().name("product-checkout"));

        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();
        //
    }

}
