package pl.edu.icm.saos.api.courts;

import org.springframework.test.web.servlet.ResultActions;
import pl.edu.icm.saos.api.utils.FieldsDefinition;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static pl.edu.icm.saos.api.utils.Constansts.*;
import static pl.edu.icm.saos.api.utils.Constansts.DIVISIONS_PATH;

/**
 * @author pavtel
 */
public class CourtsRepresentationVerifier {

    public static void verifyBasicFields(ResultActions actions, String pathPrefix) throws Exception{
        actions
                .andExpect(jsonPath(pathPrefix+".href").value(endsWith(COURT_PATH)))
                .andExpect(jsonPath(pathPrefix+".code").value(FieldsDefinition.JC.COURT_CODE))
                .andExpect(jsonPath(pathPrefix+".name").value(FieldsDefinition.JC.COURT_NAME))
                .andExpect(jsonPath(pathPrefix+".type").value(FieldsDefinition.JC.COURT_TYPE.name()))

                .andExpect(jsonPath(pathPrefix+".parentCourt.href").value(endsWith(PARENT_COURT_PATH)))
                .andExpect(jsonPath(pathPrefix+".parentCourt.name").value(endsWith(FieldsDefinition.JC.COURT_PARENT_NAME)))

                .andExpect(jsonPath(pathPrefix+".divisions").isArray())
                .andExpect(jsonPath(pathPrefix+".divisions.[0].href").value(endsWith(DIVISIONS_PATH + "/" + FieldsDefinition.JC.DIVISION_ID)))
                .andExpect(jsonPath(pathPrefix+".divisions.[0].name").value(FieldsDefinition.JC.DIVISION_NAME))

                .andExpect(jsonPath(pathPrefix+".divisions.[1].href").value(endsWith(DIVISIONS_PATH + "/" + FieldsDefinition.JC.SECOND_DIVISION_ID)))
                .andExpect(jsonPath(pathPrefix+".divisions.[1].name").value(FieldsDefinition.JC.SECOND_DIVISION_NAME))
        ;
    }
}
