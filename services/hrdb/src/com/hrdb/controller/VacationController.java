/*Copyright (c) 2016-2017 vcstest4.com All Rights Reserved.
 This software is the confidential and proprietary information of vcstest4.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcstest4.com*/
package com.hrdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.hrdb.Vacation;
import com.hrdb.service.VacationService;


/**
 * Controller object for domain model class Vacation.
 * @see Vacation
 */
@RestController("hrdb.VacationController")
@Api(value = "VacationController", description = "Exposes APIs to work with Vacation resource.")
@RequestMapping("/hrdb/Vacation")
public class VacationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VacationController.class);

    @Autowired
	@Qualifier("hrdb.VacationService")
	private VacationService vacationService;

	@ApiOperation(value = "Creates a new Vacation instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Vacation createVacation(@RequestBody Vacation vacation) {
		LOGGER.debug("Create Vacation with information: {}" , vacation);

		vacation = vacationService.create(vacation);
		LOGGER.debug("Created Vacation with information: {}" , vacation);

	    return vacation;
	}


    @ApiOperation(value = "Returns the Vacation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vacation getVacation(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Vacation with id: {}" , id);

        Vacation foundVacation = vacationService.getById(id);
        LOGGER.debug("Vacation details with id: {}" , foundVacation);

        return foundVacation;
    }

    @ApiOperation(value = "Updates the Vacation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vacation editVacation(@PathVariable("id") Integer id, @RequestBody Vacation vacation) throws EntityNotFoundException {
        LOGGER.debug("Editing Vacation with id: {}" , vacation.getId());

        vacation.setId(id);
        vacation = vacationService.update(vacation);
        LOGGER.debug("Vacation details with id: {}" , vacation);

        return vacation;
    }

    @ApiOperation(value = "Deletes the Vacation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVacation(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Vacation with id: {}" , id);

        Vacation deletedVacation = vacationService.delete(id);

        return deletedVacation != null;
    }

    /**
     * @deprecated Use {@link #findVacations(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Vacation instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vacation> searchVacationsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Vacations list");
        return vacationService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vacation instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vacation> findVacations(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vacations list");
        return vacationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vacation instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vacation> filterVacations(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vacations list");
        return vacationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVacations(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return vacationService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Vacation instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countVacations( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Vacations");
		return vacationService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VacationService instance
	 */
	protected void setVacationService(VacationService service) {
		this.vacationService = service;
	}

}

