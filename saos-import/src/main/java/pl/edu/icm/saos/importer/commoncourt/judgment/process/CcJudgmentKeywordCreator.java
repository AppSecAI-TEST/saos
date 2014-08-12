package pl.edu.icm.saos.importer.commoncourt.judgment.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.icm.saos.persistence.model.CcJudgmentKeyword;
import pl.edu.icm.saos.persistence.repository.CcJudgmentKeywordRepository;

/**
 * @author Łukasz Dumiszewski
 */
@Service("ccJudgmentKeywordCreator")
class CcJudgmentKeywordCreator {

    
    private CcJudgmentKeywordRepository ccJudgmentKeywordRepository;
    
    
    public CcJudgmentKeyword getOrCreateCcJudgmentKeyword(String phrase) {
        CcJudgmentKeyword keyword = ccJudgmentKeywordRepository.findOneByPhrase(phrase);
        if (keyword == null) {
            keyword = new CcJudgmentKeyword();
            keyword.setPhrase(phrase);
            ccJudgmentKeywordRepository.save(keyword);
            ccJudgmentKeywordRepository.flush();
        }
        return keyword;
    }

    //------------------------ SETTERS --------------------------
    
    @Autowired
    public void setCcJudgmentKeywordRepository(CcJudgmentKeywordRepository ccJudgmentKeywordRepository) {
        this.ccJudgmentKeywordRepository = ccJudgmentKeywordRepository;
    }

    
    
    
    
}
