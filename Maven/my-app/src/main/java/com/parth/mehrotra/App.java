package com.parth.mehrotra;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main( String[] args ) throws ParseException, IOException {
		JavaParser.parse(new File("resources/Test.java"));
    }
}
