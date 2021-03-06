package pl.edu.icm.saos.search.analysis.solr.request;

import org.springframework.stereotype.Service;

import pl.edu.icm.saos.search.analysis.request.JudgmentSeriesCriteria;
import pl.edu.icm.saos.search.search.model.JudgmentCriteria;

/**
 * 
 * @author Łukasz Dumiszewski
 */
@Service("judgmentSeriesCriteriaConverter")
public class JudgmentSeriesCriteriaConverter {

    
   /**
    * Converts {@link JudgmentSeriesCriteria} into {@link JudgmentCriteria}  
    */
    public JudgmentCriteria convert(JudgmentSeriesCriteria judgmentSeriesCriteria) {
        
        JudgmentCriteria judgmentCriteria = new JudgmentCriteria();
        
        judgmentCriteria.setAll(judgmentSeriesCriteria.getPhrase());
        
        judgmentCriteria.setJudgmentDateFrom(judgmentSeriesCriteria.getStartJudgmentDate());
        
        judgmentCriteria.setJudgmentDateTo(judgmentSeriesCriteria.getEndJudgmentDate());
        
        convertCourtCriteria(judgmentSeriesCriteria, judgmentCriteria);
        
        return judgmentCriteria;
        
    }
    
    
    
    
    //------------------------ PRIVATE --------------------------

    
    private void convertCourtCriteria(JudgmentSeriesCriteria judgmentSeriesCriteria, JudgmentCriteria judgmentCriteria) {
        
        judgmentCriteria.setCourtType(judgmentSeriesCriteria.getCourtType());
        
        
        if (!judgmentSeriesCriteria.isCcIncludeDependentCourtJudgments()) {
            
            judgmentCriteria.setCcCourtId(judgmentSeriesCriteria.getCcCourtId());
            
        } else {
            
            judgmentCriteria.setCcDirectOrSuperiorCourtId(judgmentSeriesCriteria.getCcCourtId());
            
        }
        
        
        
        judgmentCriteria.setCcCourtDivisionId(judgmentSeriesCriteria.getCcCourtDivisionId());
        
        judgmentCriteria.setScCourtChamberId(judgmentSeriesCriteria.getScCourtChamberId());
        
        judgmentCriteria.setScCourtChamberDivisionId(judgmentSeriesCriteria.getScCourtChamberDivisionId());
    }
        
}
