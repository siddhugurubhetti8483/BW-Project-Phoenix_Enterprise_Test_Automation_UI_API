package com.phoenix.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
  features = "src/test/resources/features",
  glue = "com.phoenix.bdd.steps",
  plugin = {"pretty", "summary"},
  monochrome = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests { }
