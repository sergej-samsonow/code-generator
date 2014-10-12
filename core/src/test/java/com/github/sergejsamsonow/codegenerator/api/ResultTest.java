package com.github.sergejsamsonow.codegenerator.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class ResultTest {

    @Test
    public void testResult() throws Exception {
        Result result = new Result("subpath", "code");
        assertThat(result.getSubPath(), equalTo("subpath"));
        assertThat(result.getCode(), equalTo("code"));
    }

}
