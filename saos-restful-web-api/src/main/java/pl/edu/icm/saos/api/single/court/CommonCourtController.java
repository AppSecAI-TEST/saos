package pl.edu.icm.saos.api.single.court;

import static pl.edu.icm.saos.api.services.exceptions.HttpServletRequestVerifyUtils.checkAcceptHeader;
import static pl.edu.icm.saos.api.services.exceptions.HttpServletRequestVerifyUtils.checkRequestMethod;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.icm.saos.api.services.exceptions.ControllersEntityExceptionHandler;
import pl.edu.icm.saos.api.services.exceptions.ElementDoesNotExistException;
import pl.edu.icm.saos.persistence.model.CommonCourt;
import pl.edu.icm.saos.persistence.repository.CommonCourtRepository;

/**
 * Provides functionality for constructing view for single court.
 * @author pavtel
 */
@Controller
@RequestMapping("/api/commonCourts/{courtId}")
public class CommonCourtController extends ControllersEntityExceptionHandler {

    private CommonCourtRepository courtRepository;

    private SingleCourtSuccessRepresentationBuilder singleCourtSuccessRepresentationBuilder;


    //------------------------ LOGIC --------------------------

    @RequestMapping(value = "")
    @ResponseBody
    public ResponseEntity<Object> showCourt(
            @RequestHeader(value = "Accept", required = false) String acceptHeader,
            @PathVariable("courtId") long courtId,
            HttpServletRequest request
            
    ) throws ElementDoesNotExistException {

        checkRequestMethod(request, HttpMethod.GET);
        checkAcceptHeader(acceptHeader, MediaType.APPLICATION_JSON);


        CommonCourt court = courtRepository.findOneAndInitialize(courtId);
        if(court == null){
            throw new ElementDoesNotExistException("Court", courtId);
        }

        Object representation = singleCourtSuccessRepresentationBuilder.build(court);

        return new ResponseEntity<>(representation, HttpStatus.OK);
    }


    //------------------------ SETTERS --------------------------

    @Autowired
    public void setCourtRepository(CommonCourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Autowired
    public void setSingleCourtSuccessRepresentationBuilder(SingleCourtSuccessRepresentationBuilder singleCourtSuccessRepresentationBuilder) {
        this.singleCourtSuccessRepresentationBuilder = singleCourtSuccessRepresentationBuilder;
    }
}
