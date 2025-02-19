/*******************************************************************************
 * (c) Copyright IBM Corporation 2024.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.it;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.http.HttpStatus;

public class EndpointIT {
    private static String URL;

    @BeforeAll
    public static void init() {
        URL = "http://localhost:9080/";
    }

    //@Test
    public void testServlet() throws Exception {
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet httpget = new HttpGet(URL);
            httpclient.execute(httpget, response -> {
                assertEquals(HttpStatus.SC_OK, response.getCode(), "HTTP GET failed");
                String responseText = EntityUtils.toString(response.getEntity());
                assertTrue(responseText.contains("Hello World"), "Unexpected response body");
                return null;
            });
        }
    }
}
