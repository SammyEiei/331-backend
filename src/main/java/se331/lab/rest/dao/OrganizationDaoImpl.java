package se331.lab.rest.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organization;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizationDaoImpl implements OrganizationDao {
    List<Organization> organizationList;

    @PostConstruct
    public void init() {
        organizationList = new ArrayList<>();
        organizationList.add(Organization.builder()
                .id(101L)
                .name("Tech Corp")
                .address("123 Main St")
                .contact("555-1234")
                .email("info@techcorp.com")
                .website("https://www.techcorp.com")
                .build());

        organizationList.add(Organization.builder()
                .id(102L)
                .name("Eco Green")
                .address("456 Green Way")
                .contact("555-5678")
                .email("info@ecogreen.com")
                .website("https://www.ecogreen.com")
                .build());

        organizationList.add(Organization.builder()
                .id(103L)
                .name("Health Inc.")
                .address("789 Health Blvd")
                .contact("555-9876")
                .email("contact@healthinc.com")
                .website("https://www.healthinc.com")
                .build());
    }

    @Override
    public Integer getOrganizationSize() {
        return organizationList.size();
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizationList.size() : pageSize; // Use organizationList size if pageSize is null
        page = page == null ? 1 : page; // Default to page 1 if page is null

        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<>(organizationList.subList(firstIndex, firstIndex + pageSize), PageRequest.of(page, pageSize), organizationList.size());
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationList.stream().filter(organization -> organization.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organization save(Organization organization) {
        organization.setId(organizationList.get(organizationList.size() - 1).getId() + 1);
        organizationList.add(organization);
        return organization;
    }
}
