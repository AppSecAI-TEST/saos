package pl.edu.icm.saos.api.courts.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.icm.saos.api.parameters.RequestParameters;
import pl.edu.icm.saos.api.search.ApiSearchService;
import pl.edu.icm.saos.api.search.ElementsSearchResults;
import pl.edu.icm.saos.persistence.model.CommonCourt;

import java.util.Arrays;

/**
 * @author pavtel
 */
@Service
public class CourtsSearchService implements ApiSearchService<CommonCourt> {

    @Override
    public ElementsSearchResults<CommonCourt> performSearch(RequestParameters requestParameters) {
        //TODO real implementation
        CommonCourt court = new CommonCourt();
        court.setCode("000432");

        return new ElementsSearchResults<>(requestParameters, Arrays.asList(court));
    }
}
