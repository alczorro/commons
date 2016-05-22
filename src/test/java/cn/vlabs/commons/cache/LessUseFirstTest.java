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
package cn.vlabs.commons.cache;

import junit.framework.TestCase;


public class LessUseFirstTest extends TestCase{
	public void setUp() throws Exception {
		cache= new LessUseFirst<String, String>(10);
	}

	public void tearDown() throws Exception {
		cache =null;
	}
	

	public void testMaxSize(){
		for (int i=0;i<20;i++){
			cache.add(Integer.toString(i), Integer.toString(i));
		}
		assertEquals(10, cache.size());
	}

	public void testMiss(){
		for (int i=0;i<11;i++){
			cache.add(Integer.toString(i), Integer.toString(i));
		}
		assertNull(cache.get(Integer.toString(0)));
	}
	public void testZero(){
		assertNull(cache.get(Integer.toString(0)));
	}
	public void testHit(){
		for (int i=0;i<10;i++){
			cache.add(Integer.toString(i), Integer.toString(i));
		}
		assertEquals(Integer.toString(8), cache.get(Integer.toString(8)));
	}
	private Cache<String, String> cache;
}
