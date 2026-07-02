package com.example.Robomain.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

/**
 * Architecture guard tests — fail the build if layer rules are violated.
 * Run with: mvn test -Dtest=CleanArchitectureTest
 */
class CleanArchitectureTest {

    private static JavaClasses classes;

    @BeforeAll
    static void setup() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.example.Robomain");
    }

    @Test
    void layerDependenciesShouldBeRespected() {
        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Presentation").definedBy("com.example.Robomain.presentation..")
                .layer("Application").definedBy("com.example.Robomain.application..")
                .layer("Domain").definedBy("com.example.Robomain.domain..")
                .layer("Infrastructure").definedBy("com.example.Robomain.infrastructure..")
                .whereLayer("Presentation").mayNotBeAccessedByAnyLayer()
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Presentation", "Infrastructure")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure", "Presentation")
                .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer();

        rule.check(classes);
    }

    @Test
    void domainShouldNotDependOnOtherLayers() {
        noClasses()
                .that().resideInAPackage("com.example.Robomain.domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.example.Robomain.application..",
                        "com.example.Robomain.infrastructure..",
                        "com.example.Robomain.presentation..")
                .check(classes);
    }

    @Test
    void domainShouldNotDependOnSpringOrJpa() {
        noClasses()
                .that().resideInAPackage("com.example.Robomain.domain..")
                .and().haveSimpleNameNotEndingWith("Exception") // exceptions may extend RuntimeException
                .should().dependOnClassesThat().resideInAnyPackage(
                        "org.springframework..",
                        "jakarta.persistence..",
                        "javax.persistence..")
                .check(classes);
    }

    @Test
    void applicationShouldNotDependOnInfrastructureOrPresentation() {
        noClasses()
                .that().resideInAPackage("com.example.Robomain.application..")
                .and().haveSimpleNameNotContaining("Impl")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.example.Robomain.infrastructure..",
                        "com.example.Robomain.presentation..")
                .check(classes);
    }

    @Test
    void controllersShouldResideInPresentationLayer() {
        classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("com.example.Robomain.presentation.api.controller..")
                .check(classes);
    }

    @Test
    void domainRepositoryInterfacesShouldBeInDomainLayer() {
        classes()
                .that().haveSimpleNameStartingWith("I")
                .and().haveSimpleNameEndingWith("Repository")
                .and().areInterfaces()
                .should().resideInAPackage("com.example.Robomain.domain..")
                .check(classes);
    }

    @Test
    void jpaRepositoriesShouldBeInInfrastructureLayer() {
        classes()
                .that().haveSimpleNameEndingWith("JpaRepository")
                .should().resideInAPackage("com.example.Robomain.infrastructure.persistence.jpa..")
                .check(classes);
    }

    @Test
    void jpaEntitiesShouldBeInInfrastructureLayer() {
        classes()
                .that().areAnnotatedWith("jakarta.persistence.Entity")
                .should().resideInAPackage("com.example.Robomain.infrastructure.persistence.entity..")
                .check(classes);
    }

    @Test
    void domainModelsShouldNotHaveJpaAnnotations() {
        noClasses()
                .that().resideInAPackage("com.example.Robomain.domain..model..")
                .should().beAnnotatedWith("jakarta.persistence.Entity")
                .check(classes);
    }

    @Test
    void commandsShouldBeInApplicationLayer() {
        classes()
                .that().haveSimpleNameEndingWith("Command")
                .and().areNotInterfaces()
                .should().resideInAPackage("com.example.Robomain.application..")
                .check(classes);
    }

    @Test
    void queriesShouldBeInApplicationLayer() {
        classes()
                .that().haveSimpleNameEndingWith("Query")
                .and().areNotInterfaces()
                .should().resideInAPackage("com.example.Robomain.application..")
                .check(classes);
    }

    @Test
    void handlersShouldBeInApplicationLayer() {
        classes()
                .that().haveSimpleNameEndingWith("Handler")
                .should().resideInAPackage("com.example.Robomain.application..")
                .check(classes);
    }

    @Test
    void repositoryImplsShouldBeInInfrastructureLayer() {
        classes()
                .that().haveSimpleNameEndingWith("RepositoryImpl")
                .should().resideInAPackage("com.example.Robomain.infrastructure.persistence.repository..")
                .check(classes);
    }
}
