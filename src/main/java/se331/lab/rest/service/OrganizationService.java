package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Organization;

public interface OrganizationService {
    Page<Organization> getOrganizations(Integer pageSize, Integer page);
    Organization getOrganization(Long id);
    Organization save(Organization organization);
}
