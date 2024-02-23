package org.example;
/*
  @author   Dtest
  @project   Lab3
  @class  CompanyServiceImpl
  @version  1.0.0 
  @since 23.02.2024 - 09.23
*/

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    @Override
    public Company getTopLevelParent(Company child) {
        if (child == null) {
            return null;
        }

        Company currentCompany = child;
        while (currentCompany.getParent() != null) {
            currentCompany = currentCompany.getParent();
        }

        return currentCompany;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        if (company == null || !companies.contains(company)) {
            return 0;
        }

        long totalEmployees = company.getEmployeesCount();

        for (Company child : companies) {
            if (child.getParent() == company) {
                totalEmployees += getEmployeeCountForCompanyAndChildren(child, companies);
            }
        }

        return totalEmployees;
    }
}
