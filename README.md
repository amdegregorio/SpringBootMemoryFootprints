# SpringBootMemoryFootprints
An example application used to evaluate changes in memory used by Spring Boot across several version.

For more information see [Spring Boot Versions: A Memory Usage Comparison](https://amydegregorio.com/2019/07/02/spring-boot-versions-a-memory-usage-comparison/).


## To Run

This application expects a local MySQL database named `memdemo`.  There is a SQL script for creating the table.

This application can run across five versions of Spring Boot.  Each version is in a comment in `build.gradle`.  

To run against the 1.x versions you can use the wrapper (Gradle 3.5):

```
> gradlew bootRun
```

To run against the 2.x versions, a higher version of Gradle is required.  It's tested against Gradle 5.5.

## Additional Notes

There are commented areas of code throughout the application that reflect differences between 1.x and 2.x versions of Spring Boot.  It's not sufficient to just change the Spring Boot version in the `build.gradle` file.

#### License

This project is licensed under the Apache License version 2.0.  
See the LICENSE file or go to [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0) for more information. 