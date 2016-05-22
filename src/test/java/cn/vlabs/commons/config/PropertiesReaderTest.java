/*
 * Copyright (c) 2008-2016 Computer Network Information Center (CNIC), Chinese Academy of Sciences.
 * 
 * This file is part of Duckling project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 */
package cn.vlabs.commons.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertiesReaderTest {
	PropertiesReader prop;
	@Before
	public void setUp() throws Exception {
		prop = new PropertiesReader();
		FileInputStream in = new FileInputStream("src/test/java/cn/vlabs/commons/config/clbconfig.properties");
		prop.load(in);
		in.close();
	}

	@After
	public void tearDown() throws Exception {
		prop = null;
	}

	@Test
	public void testGetInt() {
	    // fail("Not yet implemented");
	}

	@Test
	public void testGetBoolean() {
	    // fail("Not yet implemented");
	}

	@Test
	public void testGetProperty() throws IOException {
		assertEquals("localhost:3306", prop.getProperty("database.ip"));
		assertEquals("http://localhost/umt/sso/ssoreq.jsp",prop.getProperty("signon.server.url"));
		assertEquals("jdbc:mysql://localhost:3306/testdata?useUnicode=true&characterEncoding=UTF-8",prop.getProperty("Database.jdbcurl"));
		prop.storeToXML(System.out, "ABC");
	}

}
