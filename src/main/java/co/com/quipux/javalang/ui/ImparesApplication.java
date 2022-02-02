/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.com.quipux.javalang.ui;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;

public class ImparesApplication {

    public static void main(String[] args) throws Exception {
	Scanner in = new Scanner(System.in);
	try {
	    System.out.println("Ingrese el dígito: ");
	    int entero = in.nextInt();
	    System.out
		    .println(entero % 2 == 0
			    ? (entero <= 6 && entero >= 2 ? "No Quipux"
				    : (entero <= 20 && entero >= 6 ? "Quipux" : (entero > 20 ? "No quipux" : "")))
			    : "Quipux");
	} catch (Exception e) {

	}
    }

}
