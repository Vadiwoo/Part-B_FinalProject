/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;

import java.util.Random;




/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }



   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   System.out.println(urlVal.isValid("https://///www.google.com"));
	   System.out.println(urlVal.isValid("http://www.google.com"));
	   System.out.println(urlVal.isValid("http://www.google.com:80"));
	   System.out.println(urlVal.isValid("https://piazza.com/class/j4bo6gz138r46l?cid=158"));
	   System.out.println(urlVal.isValid("h3t://256.256.256.256:65535/$23?action=edit&mode=up"));
	   System.out.println(urlVal.isValid("http://www.google.com:80/$23"));

     //test for valid and invalid ASCII characters
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   assertFalse(urlVal.isValid("http://www.amäzon.com"));
     assertTrue(urlVal.isValid("https://www.facebook.com"));
	   assertFalse(urlVal.isValid("https://www.fäcebook.com"));
	   assertTrue(urlVal.isValid("http://oregonstate.edu/"));
	   assertFalse(urlVal.isValid("http://orëgonstate.edu/"));

	   //test for valid and invalid schemes
	   assertTrue(urlVal.isValid("https://github.com/"));
	   assertTrue(urlVal.isValid("http://github.com/"));
	   //assertFalse(urlVal.isValid("htstps://github.com/")); //bug here - test fails, uncomment to verify
	   //assertFalse(urlVal.isValid("ttps://github.com/")); //bug here - test fails, uncomment to verify

	   //test for valid and invalid domain names
	   assertTrue(urlVal.isValid("http://www.public.navy.mil/spawar/Pages/default.aspx"));
	   assertTrue(urlVal.isValid("https://www.nasa.gov/"));
	   //assertTrue(urlVal.isValid("https://www.bbc.co.uk/")); //bug here - test fails, uncomment to verify
	   assertTrue(urlVal.isValid("http://www.scaphon.org/"));
	   //assertTrue(urlVal.isValid("www.nzherald.co.nz/")); //bug here - test fails, uncomment to verify
	   //assertTrue(urlVal.isValid("www.spiegel.de/international/")); //bug here - test fails, uncomment to verify
	   //assertTrue(urlVal.isValid("http://localhost:400/")); //bug here - test fails, uncomment to verify

   }

   /*****************************Testing Scheme. *****************************/
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

	   String baseURL = "www.amazon.com";

	   String testUrl = "https://" +baseURL;
	   System.out.println("Testing Valid Scheme : ");
	   boolean result = urlVal.isValid(testUrl);

	   System.out.println("Expected Result : TRUE    Actual Result : "+result);


	   String[] inValidScheme = {"htsp://", "hts://", "ht://",  "h://"};

	   System.out.println("Testing Invalid Scheme : ");
	   result = false; //these tests should return false
	   for (int i = 0; i <= inValidScheme.length ; i++)
	   {
		    testUrl = inValidScheme[i] +baseURL;
		   result = (urlVal.isValid(testUrl));
		   if(result)
		   System.out.println("Expected Result : FALSE    Actual Result : "+result);
	   }

   }

   /*****************************Testing Authority *****************************/
   public void testYourSecondPartition(){







   }
   /*****************************Testing Authority *****************************/
   public void testYourThirdPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

	   String baseURL = "https://www.amazon.com";

	   String testUrl =  baseURL + ":80";
	   System.out.println("Testing Valid Scheme : ");
	   boolean result = urlVal.isValid(testUrl);

	   System.out.println("Expected Result : TRUE    Actual Result : "+result);

	   Random rand = new Random();



	   System.out.println("Testing Invalid Scheme : ");
	   //result = false; //these tests should return false
	   for (int i = 0; i < 1000 ; i++)
	   {
		   int n = rand.nextInt(65535);
		   System.out.println(n);
		    testUrl = baseURL + ":" + n;
		   result = (urlVal.isValid(testUrl));
		   if(!result)
		   System.out.println(n + "is invalid port");


	   }

   }
   /*****************************Testing Path *****************************/
   public void testYourFourthPartition(){







   }

   /*****************************Testing Query *****************************/
   public void testYourFifthPartition(){
     UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

	   String baseURL = "http://www.google.com/";

	   String testUrl = baseURL;
	   System.out.println("Testing the base URL for the Valid Query test: ");
	   boolean result = urlVal.isValid(testUrl);

	   System.out.println("Expected Result : TRUE    Actual Result : "+result);


	   String[] validQuery = {"search?q=junit&oq=junit&gs_l", "search?q=eclipse+ide&oq=eclipse+ide&gs_l=psy-ab.3", "search?q=how+to+write+bug+reports&oq=how+to+write+bug+reports&gs_l=psy-ab.3..0j0i22i30k1l3.81686.92729.0.93106.44.34.10.0.0.0.167.3411.22j12.34.0....0...1.1.64.psy-ab..0.44.3455...35i39k1j0i131k1j0i67k1j0i20k1.KUpHDCRFO00"};

	   System.out.println("Testing Valid Query : ");
	   result = true; //these tests should return true
	   for (int i = 0; i <= validQuery.length ; i++)
	   {
		    testUrl += validQuery[i];
		   result = (urlVal.isValid(testUrl));
		   if(result)
		   System.out.println("Expected Result : TRUE    Actual Result : "+result);
	   }

   }




   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {

	   }
   }

   public void testAnyOtherUnitTest()
   {

   }



}
