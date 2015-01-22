package pl.edu.icm.saos.search.indexing;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import pl.edu.icm.saos.persistence.model.CourtType;
import pl.edu.icm.saos.persistence.model.Judgment;

/**
 * Fills {@link SolrInputDocument} with fields from
 * {@link pl.edu.icm.saos.persistence.model.ConstitutionalTribunalJudgment ConstitutionalTribunalJudgment} 
 * 
 * @author madryk
 */
@Service
public class CtJudgmentIndexFieldsFiller extends JudgmentIndexFieldsFiller {

    
    //------------------------ LOGIC --------------------------
    
    @Override
    public boolean isApplicable(CourtType courtType) {
        return courtType == CourtType.CONSTITUTIONAL_TRIBUNAL;
    }

    @Override
    public void fillFields(SolrInputDocument doc, Judgment judgment) {
        super.fillFields(doc, judgment);
        
    }
    
    //------------------------ PRIVATE --------------------------
    
   
}
