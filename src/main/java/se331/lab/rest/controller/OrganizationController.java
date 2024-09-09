package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.Organization;
import se331.lab.rest.service.OrganizationService;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
@Controller
public class OrganizationController {

    final OrganizationService organizationService;

    // GET method to retrieve a paginated list of organizations
    @GetMapping("/organizations")
    public ResponseEntity<?> getOrganizationLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {

        // Set default values if null
        perPage = (perPage == null) ? 10 : perPage; // default to 10 items per page
        page = (page == null) ? 1 : page; // default to page 1

        Page<Organization> pageOutput = organizationService.getOrganizations(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }


    // GET method to retrieve a single organization by ID
    @GetMapping("organizations/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable("id") Long id) {
        Organization output = organizationService.getOrganization(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Organization not found");
        }
    }

    // POST method to create a new organization
    @PostMapping("/organizations")
    public ResponseEntity<?> addOrganization(@RequestBody Organization organization) {
        Organization savedOrganization = organizationService.save(organization);
        return ResponseEntity.ok(savedOrganization);
    }
}
