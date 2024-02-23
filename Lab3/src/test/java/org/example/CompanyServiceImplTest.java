package org.example;

import org.junit.jupiter.api.*;

import java.util.List;

/*
  @author   Dtest
  @project   Lab3
  @class  CompanyServiceImplTest
  @version  1.0.0 
  @since 23.02.2024 - 09.25
*/
class CompanyServiceImplTest {

    private final Company main = new Company(null, 2);
    private final Company book = new Company(main, 3);
    private final Company manager = new Company(main, 4);
    private final Company developer = new Company(manager, 8);
    private final Company design = new Company(manager, 6);
    private final Company lawer = new Company(null, 1);

    private final List<Company> list = List.of(main, book, manager, developer, design);

    private final ICompanyService companyService = new CompanyServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    // якщо вхідний об'єкт Company є null,
    // то результат методу getTopLevelParent також повинен бути null.
    @Test
    void whenCompanyIsNullThenNull() {
        Company result = companyService.getTopLevelParent(null);
        Assertions.assertNull(result);
    }

    // якщо у Company відсутній батьківський елемент,
    // то сама компанія є верхньою рівневою компанією.
    @Test
    void whenCompanyHasNoParentItIsOnTop() {
        Company result = companyService.getTopLevelParent(main);
        Assertions.assertEquals(main, result);
    }


    // якщо у Company відсутній батьківський та дочірні елементи,
    // то сама компанія є верхньою рівневою компанією.
    @Test
    void whenCompanyIsSingleItIsOnTop() {
        Company result = companyService.getTopLevelParent(lawer);
        Assertions.assertEquals(lawer, result);
    }


    // якщо у Company є один рівень підпорядкування до верхньої компанії,
    // то верхня компанія повинна бути знайдена.
    @Test
    void whenCompanyHasOneStepToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(book);
        Assertions.assertEquals(main, result);
    }


    // якщо у Company є два рівні підпорядкування до верхньої компанії,
    // то верхня компанія повинна бути знайдена.
    @Test
    void whenCompanyHasTwoStepsToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(developer);
        Assertions.assertEquals(main, result);
    }

    // якщо дочірня компанія є null, то кількість
    // працівників для неї та її дочірніх компаній дорівнює нулю.
    @Test
    void whenChildIsNullThenEmployeeCountIsZero() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(null, list);
        Assertions.assertEquals(0, result);
    }


    // якщо у Company відсутні дочірні елементи, то кількість працівників
    // для неї та її дочірніх компаній дорівнює кількості працівників самої компанії.
    @Test
    void whenCompanyIsLeafThenEmployeeCountIsItself() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(developer, list);
        Assertions.assertEquals(8, result);
    }


    // якщо Company не знаходиться в списку компаній,то кількість
    // працівників для неї та її дочірніх компаній дорівнює нулю.
    @Test
    void whenCompanyNotInListThenEmployeeCountIsZero() {
        Company newCompany = new Company(null, 5);
        long result = companyService.getEmployeeCountForCompanyAndChildren(newCompany, list);
        Assertions.assertEquals(0, result);
    }

    @Test
    void whenCompanyNotInListThenEmployeeCountIsZer() {
        Company newCompany = new Company(null, 5);
        long result = companyService.getEmployeeCountForCompanyAndChildren(newCompany, list);
        Assertions.assertEquals(0, result);
    }


    // для верхньої компанії батьківський елемент є null.
    @Test
    void whenCompanyIsTopLevelThenParentIsNull() {
        Company result = companyService.getTopLevelParent(main);
        Assertions.assertNull(result.getParent());
    }


    // для верхньої компанії кількість працівників
    // включає в себе працівників всіх дочірніх компаній.
    @Test
    void whenCompanyIsTopLevelThenEmployeeCountIncludesChildren() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(main, list);
        Assertions.assertEquals(23, result); // Сума працівників: 2 + 3 + 4 + 8 + 6
    }

    // якщо у Company відсутні дочірні елементи,то кількість працівників
    // для неї та її дочірніх компаній дорівнює кількості працівників самої компанії.
    @Test
    void whenCompanyHasNoChildrenThenEmployeeCountIsItself() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(developer, list);
        Assertions.assertEquals(8, result);
    }

    @Test
    void whenCompanyHasChildsThenColculate() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(manager, list);
        Assertions.assertEquals(18, result);
    }


    // для одиночної компанії батьківський елемент є null.
    @Test
    void whenCompanyIsSingleItIsTopLevelParent() {
        Company result = companyService.getTopLevelParent(lawer);
        Assertions.assertNull(result.getParent());
    }
}