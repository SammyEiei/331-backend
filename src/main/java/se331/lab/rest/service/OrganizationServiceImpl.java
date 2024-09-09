package se331.lab.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.OrganizationDao;
import se331.lab.rest.entity.Organization;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    final OrganizationDao organizationDao;

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        return organizationDao.getOrganizations(pageSize, page);
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationDao.getOrganization(id);
    }

    @Override
    public Organization save(Organization organization) {
        return organizationDao.save(organization);
    }
}
